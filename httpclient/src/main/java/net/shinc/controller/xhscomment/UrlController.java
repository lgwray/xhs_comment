package net.shinc.controller.xhscomment;

import java.io.UnsupportedEncodingException;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/url")
public class UrlController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 获取总数和要闻的百分比统计
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/urlDecode")
	@ResponseBody
	public IRestMessage urlDecode(@RequestParam(value="str",required=true) String str) {
		IRestMessage msg = getRestMessageWithoutUser();
		try {
			logger.info(str);
			String str2="9%E6%9C%88%E4%BB%BDCPI%E5%90%8C%E6%AF%94%E4%B8%8A%E6%B6%A81.6%20%E6%B6%A8%E5%B9%85%E9%87%8D%E8%BF%94%E2%80%9C1%E6%97%B6%E4%BB%A3%E2%80%9D";
			if(str.equals(str2)) {
				System.out.println("===");
			}
			
			String res = java.net.URLDecoder.decode(new String(str2),"utf-8");  
			if(!StringUtils.isEmpty(res)) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(res);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
	public static void main(String[] args) {
		String str="9%E6%9C%88%E4%BB%BDCPI%E5%90%8C%E6%AF%94%E4%B8%8A%E6%B6%A81.6%20%E6%B6%A8%E5%B9%85%E9%87%8D%E8%BF%94%E2%80%9C1%E6%97%B6%E4%BB%A3%E2%80%9D";
		try {
			String res = java.net.URLDecoder.decode(str,"utf-8");
			System.out.println(res);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
	}
}
