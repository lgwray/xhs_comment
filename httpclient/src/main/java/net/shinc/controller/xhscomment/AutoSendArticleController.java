package net.shinc.controller.xhscomment;

import javax.validation.Valid;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.IRestMessage;
import net.shinc.common.ShincUtil;
import net.shinc.orm.mybatis.bean.xhscomment.AutoSendArticle;
import net.shinc.service.xhscomment.AutoSendArticleService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @ClassName AutoSendArticleController 
 * @Description 自动推送
 * @author guoshijie 
 * @date 2015年9月24日 下午8:29:37  
 */
@Controller
@RequestMapping(value = "/autoSend")
public class AutoSendArticleController extends AbstractBaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AutoSendArticleService asService;
	
	/**
	 * 自动推送
	 * @param cid
	 * @param ctype
	 * @return
	 */
	@RequestMapping(value = "/autoSendMatchNews")
	@ResponseBody
	public IRestMessage autoSendMatchNews(@Valid AutoSendArticle record, BindingResult bindingResult){
		IRestMessage msg = getRestMessage();
		if(bindingResult.hasErrors()) {
			msg.setDetail(ShincUtil.getErrorFields(bindingResult));
			return msg;
		}
		try {
			asService.addAutoSendArticle(record);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
}
