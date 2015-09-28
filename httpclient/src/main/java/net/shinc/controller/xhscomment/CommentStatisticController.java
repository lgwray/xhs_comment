package net.shinc.controller.xhscomment;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.IRestMessage;
import net.shinc.service.xhscomment.CommentStatisticService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	/**
	 * 获得新华社与本地的总评论数、及要闻评论数、及占比
	 * @return
	 */
	@RequestMapping(value = "/getPercent")
	@ResponseBody
	public IRestMessage getPercent(){
		IRestMessage msg = getRestMessage();
		try {
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
}
