package net.shinc.controller.xhscomment;

import javax.validation.Valid;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.common.ShincUtil;
import net.shinc.orm.mybatis.bean.common.AdminUser;
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
	
	private Integer days = 1;
	
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
			Integer id = AdminUser.getCurrentUser().getId();
			record.setUserId(id);
			Integer num = asService.addAutoSendArticle(record,days);
			if(num > 0){
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(num);
			} 
			Boolean isEnable = asService.isEnableAutoSendArticle(Integer.parseInt(record.getArticleId()),Integer.parseInt(record.getMatchNewsId()));
			msg.setState(String.valueOf(isEnable));
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
}
