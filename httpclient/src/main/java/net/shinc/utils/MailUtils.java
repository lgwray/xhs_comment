package net.shinc.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.SimpleEmail;

/**
 * @ClassName MailUtils
 * @Description 发送邮件
 * @author guoshijie
 * @date 2015年10月14日 上午10:46:58
 */
public class MailUtils {

	public static void main(String[] args) throws MessagingException {
		try {
			String fromAddr = "guoshijie@shinc.net";
			String pwd = "@WSXvfr4";
			
			Address[] toAddr = new Address[6];
			toAddr[0] = new InternetAddress("guoshijie@shinc.net", "郭世杰");
			
			String bccAddr = "guoshijie_hi@yeah.net";
			String title = "java测试邮件";
			String content = "<a href='http://123.56.157.137:3003/'>新华社评论后台</a>";
			sendMail(fromAddr, pwd, toAddr, null, bccAddr, title, content);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 发邮件demo(commons-email)  ==>SimpleEmail
	 * @param arg
	 * @throws Exception
	 */
	public static void sendMailByApache () throws Exception {
        SimpleEmail email = new SimpleEmail ( );
        // smtp host
        email.setHostName ( "smtp.163.com" );
        // 登陆邮件服务器的用户名和密码
        email.setAuthentication ( "zieckey", "123456" );
        // 接收人
        email.addTo ( "zieckey@yahoo.com.cn", "Zieckey" );
        // 发送人
        email.setFrom ( "zieckey@163.com", "Me" );
        // 标题
        email.setSubject ( "Test message" );
        // 邮件内容
        email.setMsg ( "This is a simple test of commons-email" );
        // 发送
        email.send ( );
        System.out.println ( "Send email successful!" );
	}
	
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
	public static void sendMail(String fromAddr,String pwd, Address[] toAddr, Address[] ccAddr, String bccAddr, String title, String content) throws MessagingException {
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		/*
		 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
		 * mail.user / mail.from
		 */
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");
		
		if(fromAddr.endsWith("163.com")){
			props.put("mail.smtp.host", "smtp.163.com");
		} else {
			props.put("mail.smtp.host", "smtp.exmail.qq.com");
		}
		
		// 发件人的账号
		props.put("mail.user", fromAddr);
		// 访问SMTP服务时需要提供的密码
		props.put("mail.password", pwd);

		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		
		// 设置发件人
		InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
		message.setFrom(form);

		// 设置收件人
		message.setRecipients(RecipientType.TO, toAddr);

		// 设置抄送
		if (null != ccAddr && ccAddr.length > 0) {
			message.setRecipients(RecipientType.CC, ccAddr);
		}

		// 设置密送，其他的收件人不能看到密送的邮件地址
		if(!StringUtils.isEmpty(bccAddr)) {
			InternetAddress bcc = new InternetAddress(bccAddr);
			message.setRecipient(RecipientType.BCC, bcc);
		}

		// 设置邮件标题
		message.setSubject(title);

		// 设置邮件的内容体
		message.setContent(content, "text/html;charset=UTF-8");

		// 发送邮件
		Transport.send(message);
	}
}
