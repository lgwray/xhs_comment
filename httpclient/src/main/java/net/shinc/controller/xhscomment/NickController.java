package net.shinc.controller.xhscomment;

import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.formbean.common.NickForm;
import net.shinc.service.xhscomment.NickNameService;
import net.shinc.service.xhscomment.NickService;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/** 
 * @ClassName NickController 
 * @Description 昵称
 * @author guoshijie 
 * @date 2015年9月10日 下午5:22:19  
 */
@Controller
@RequestMapping(value = "/nick")
public class NickController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NickService nickService;
	
	@Autowired
	private NickNameService nickNameService;
	
	@Value("${page.count}")
	private String limit;
	
	/**
	 * 获取杂乱昵称列表
	 * @param page 当前页
	 * @param num 每页条数
	 * @return
	 */
	@RequestMapping(value = "/getNickListByPage")
	@ResponseBody
	public IRestMessage getNickListByPage(@RequestParam(value="page",defaultValue="1",required=true) String page, String num) {
		IRestMessage msg = getRestMessageWithoutUser();
		try {
			logger.info("page:"+page+"\tnum:"+num);
			if (StringUtils.isEmpty(num)) {
				num = limit;
			}
			logger.info("page:" + page + "\tnum:" + num);
			PageBounds pb = new PageBounds(Integer.parseInt(page), Integer.parseInt(num));
//			PageList<Nick> list = nickService.getNickListByPage(pb);
			PageList<Map> list = nickService.getNickListByPage2(pb);
			if (null != list && list.size() > 0) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
				msg.setDetail(String.valueOf(list.size()));
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
	/**
	 * 过滤昵称
	 * @param nickList
	 * @return
	 */
	@RequestMapping(value = "/filterNick")
	@ResponseBody
	public IRestMessage filterNick(NickForm nickList) {
		IRestMessage msg = getRestMessageWithoutUser();
		Integer num = nickService.filterNick(nickList);
		if(null!=num && num >0){
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			msg.setResult(num);
		}else {
			msg.setCode(ErrorMessage.ADD_FAILED.getCode());
		}
		return msg;
	}
	
}
