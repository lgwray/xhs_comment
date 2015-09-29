package net.shinc.controller.xhscomment;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.xhscomment.CommentStatisticService;
import net.shinc.utils.DateUtils;

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
 * @ClassName CommentStatisticController 
 * @Description 评论统计
 * @author guoshijie 
 * @date 2015年9月25日 下午7:45:13  
 */
@Controller
@RequestMapping(value = "/calculate")
public class CommentStatisticController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommentStatisticService csService;
	private String pattern = "yyyy-MM-dd";
	
	/**
	 * 获得新华社与本地的总评论数、及要闻评论数、及占比
	 * @return
	 */
	@RequestMapping(value = "/getPercent")
	@ResponseBody
	public IRestMessage getPercent(@RequestParam(value="date",required=false) String date){
		IRestMessage msg = getRestMessage();
		try {
			List<Map<String, Object>> list = csService.getPercentByDays(date);
			if(!CollectionUtils.isEmpty(list)) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
				msg.setDetail(String.valueOf(list.size()));
				msg.setDate(DateUtils.dateToString(new Date(), pattern));
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
}
