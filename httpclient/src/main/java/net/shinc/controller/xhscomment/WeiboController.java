package net.shinc.controller.xhscomment;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.WeiboService;
import net.shinc.utils.UnicodeUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @ClassName WeiboController 
 * @Description 微博
 * @author guoshijie 
 * @date 2015年9月9日 下午2:46:09  
 */
@Controller
@RequestMapping(value = "/weibo")
public class WeiboController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private String type = "weibo";
	
	@Autowired
	private WeiboService weiboService;
	
	/**
	 * @param mid
	 * @param page 当前页
	 * @param num 每页条数
	 * @return
	 */
	@RequestMapping(value = "/getWeiboComments")
	@ResponseBody
	public IRestMessage getWeiboComments(String mid, @RequestParam(value="page",defaultValue="1",required=true) String page, String num) {
		IRestMessage msg = getRestMessage();
		try {
			logger.info("mid"+mid);
			String res = weiboService.getWeiboComments(type, mid, page, num);
			String unicode = UnicodeUtils.decodeUnicode(res);
			logger.info("res:"+unicode);
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			msg.setResult(unicode);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
}
