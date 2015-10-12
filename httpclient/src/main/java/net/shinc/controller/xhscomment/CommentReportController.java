package net.shinc.controller.xhscomment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.xhscomment.BaseCommentService;
import net.shinc.service.xhscomment.CommentReportService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @ClassName CommentReportController 
 * @Description 每日报表
 * @author guoshijie 
 * @date 2015年10月10日 下午12:17:56  
 */
@Controller
@RequestMapping(value = "/report")
public class CommentReportController extends AbstractBaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CommentReportService crService;
	
	@Autowired
	private BaseCommentService baseCommentService;
	
	@RequestMapping(value = "/getCommentReport")
	@ResponseBody
	public IRestMessage getCommentReport(@RequestParam(value="date",required=true) String date){
		IRestMessage msg = getRestMessage();
		try {
			List list = crService.getReportByDate(date);
			if(!CollectionUtils.isEmpty(list)) {
				Map today = baseCommentService.getTotalCommentsNumsByDate(date);
				
				Map map = new HashMap();
				map.put("list", list);
				
				if(!CollectionUtils.isEmpty(today)) {
					map.put("total", today.get("sum"));
				}else{
					map.put("total", "未知");
				}
				
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(map);
				msg.setDate(date);
				msg.setDetail(String.valueOf(list.size()));
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error("查询失败==>" + ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
}
