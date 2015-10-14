package net.shinc.service.common.impl;

import javax.mail.MessagingException;

import net.shinc.service.common.MailService;
import net.shinc.utils.MailUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName MenuServiceImpl 
 * @Description 目录菜单接口的实现
 * @author guoshijie 
 * @date 2015年7月27日 下午5:15:24
 */
@Service
public class MailServiceImpl implements MailService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void sendMail(String fromAddr, String pwd, String toAddr, String ccAddr, String bccAddr, String title, String content) {
		try {
			MailUtils.sendMail(fromAddr, pwd, toAddr, ccAddr, bccAddr, title, content);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
