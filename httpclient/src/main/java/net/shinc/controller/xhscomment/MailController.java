package net.shinc.controller.xhscomment;

import java.util.Date;

import javax.mail.Address;
import javax.mail.internet.InternetAddress;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.common.MailService;
import net.shinc.utils.DateUtils;

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
	String now = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
	
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
			
			//收件人列表
			Address[] toAddr = new Address[1];
			toAddr[0] = new InternetAddress("dingpeng@shinc.net", "丁鹏",charset);
			
			//抄送人列表
			Address[] ccAddr = new Address[1];
			ccAddr[0] = new InternetAddress("zhaozhonglin@shinc.net","赵忠琳",charset);
			
			//密送
			String bccAddr = "guoshijie@shinc.net";
			
			String title = mailService.getMailTitle();
			String content = mailService.getMailContent();
			
			StringBuilder res = new StringBuilder();
			res.append("发件人: "+fromAddr);
			res.append("    收件人: ");
			for (Address address : toAddr) {
				InternetAddress ia = (InternetAddress)address;
				String personal = ia.getPersonal();
				res.append(personal+" ");
			}
			
			mailService.sendMail(fromAddr, pwd, toAddr, ccAddr, bccAddr, title, content);
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			msg.setResult(res);
			logger.info("发送时间: "+now+"\t\t状态: 发送成功\t"+res);
		} catch (Exception e) {
			logger.info("发送时间: "+now+"\t\t状态: 发送失败");
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
}
