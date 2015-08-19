package net.shinc.controller.common;

import java.util.Locale;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.IRestMessage;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonErrorController extends AbstractBaseController implements ErrorController {
	

	@RequestMapping(value="/error")
	@ResponseBody
	public IRestMessage error(Locale locale) {
		
		return getRestMessageWithoutUser();
			
	}
	
	@Override
	public String getErrorPath() {
		
		return "/error";
	}

}
