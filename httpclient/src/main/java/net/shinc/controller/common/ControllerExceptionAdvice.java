package net.shinc.controller.common;

import java.util.Locale;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.IRestMessage;

import org.springframework.context.NoSuchMessageException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangtaichao 2015年5月29日
 *
 */
@ControllerAdvice(basePackages="net.shinc.controller")
public class ControllerExceptionAdvice extends AbstractBaseController {
	
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public IRestMessage handlerMissingServletRequestParameterException(MissingServletRequestParameterException ex,Locale locale) {
		
		IRestMessage msg = getRestMessageWithoutUser();
		String paramName = ex.getParameterName();
		
		String paramNameSource = paramName;
		try {
			msg.setCode(net.shinc.common.ErrorMessage.ERROR_PARAM_CHECK.getCode());
			msg.setDetail(paramName + "can not be null");
		}catch(NoSuchMessageException nme) {
		}
		
		
		return msg;
		
	}
	

}
