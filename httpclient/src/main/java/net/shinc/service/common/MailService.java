package net.shinc.service.common;

import javax.mail.Address;
import javax.mail.MessagingException;

/**
 * @ClassName MenuService 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年7月27日 下午5:16:54
 */
public interface MailService {

	/**
	 * 发邮件(javax.mail)
	 * @param fromAddr 	发件地址
	 * @param pwd		密码
	 * @param toAddr 	收件地址
	 * @param ccAddr 	抄送地址
	 * @param bccAddr 	密送地址
	 * @param title 	邮件主题
	 * @param content 	邮件内容
	 * @throws MessagingException
	 */
	public void sendMail(String fromAddr,String pwd, Address[] toAddr, String ccAddr, String bccAddr, String title, String content);
	
	public String getMailTitle();
	
	public String getMailContent();

}
