package net.shinc.controller.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.xhscomment.CountService;

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
 * @ClassName CountController 
 * @Description 统计
 * @author guoshijie 
 * @date 2015年9月17日 下午12:05:09  
 */
@Controller
@RequestMapping(value = "/count")
public class CountController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CountService countService;
	
	/**
	 * 获取总数和要闻的百分比统计
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/getTotalPercent")
	@ResponseBody
	public IRestMessage getTotalPercent(@RequestParam(value="date",required=false) String date) {
		IRestMessage msg = getRestMessageWithoutUser();
		try {
			List<Map> list = countService.getTotalPercent(date);
			if(!CollectionUtils.isEmpty(list)) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
}
