package net.shinc.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.shinc.orm.mybatis.bean.common.AdminUser;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class AbstractBaseController implements ApplicationContextAware {
	
	protected ApplicationContext applicationContext;
	
	@Autowired
	protected MessageSource messageSource;
	
	protected IRestMessage restMessage;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        binder.setAutoGrowCollectionLimit(1024);// 数字越界问题
    }
	public IRestMessage getRestMessage() {
		IRestMessage msg = (IRestMessage)applicationContext.getBean("restMessage");
		AdminUser currentUser = AdminUser.getCurrentUser();
		if(null != currentUser){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", currentUser.getId());
			map.put("userName", currentUser.getUsername());
			map.put("menuMap", currentUser.getMenuMap());
			msg.setUserInfo(map);
		}
		return msg;
	}

	public IRestMessage getRestMessageWithoutUser() {
		IRestMessage msg = (IRestMessage)applicationContext.getBean("restMessage");
		return msg;
	}
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
