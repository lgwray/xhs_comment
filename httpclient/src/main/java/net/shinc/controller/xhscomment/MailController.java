package net.shinc.controller.xhscomment;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.common.MailService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @ClassName MailController 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年10月14日 下午10:57:53  
 */
@Controller
@RequestMapping(value = "/mail")
public class MailController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MailService mailService;
	
	private String charset = "UTF-8";
	
	/**
	 * 发送每日占比统计结果 
	 * @return
	 */
	@RequestMapping(value = "/sendEMail")
	@ResponseBody
	public IRestMessage sendEMail() {
		IRestMessage msg = getRestMessageWithoutUser();
		try {
			String fromAddr = "guoshijie@shinc.net";
			String pwd = "@WSXvfr4";
			
			Address[] toAddr = new Address[1];
			toAddr[0] = new InternetAddress("guoshijie@shinc.net","郭世杰",charset);
//			toAddr[1] = new InternetAddress("steve_hi@163.com", "郭世杰2",charset);
//			toAddr[2] = new InternetAddress("gsj_java@163.com", "郭世杰3",charset);
			
			String bccAddr = "steve_hi@163.com";
			String title = mailService.getMailTitle();
			String content = mailService.getMailContent();
			
			mailService.sendMail(fromAddr, pwd, toAddr, null, bccAddr, title, content);
			msg.setCode(ErrorMessage.SUCCESS.getCode());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
}
