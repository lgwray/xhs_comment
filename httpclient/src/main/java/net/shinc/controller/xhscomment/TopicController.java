package net.shinc.controller.xhscomment;

import java.util.List;

import javax.validation.Valid;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.orm.mybatis.bean.xhscomment.Topic;
import net.shinc.service.xhscomment.TopicService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @ClassName TopicController 
 * @Description 话题
 * @author guoshijie 
 * @date 2015年9月14日 下午9:04:04  
 */
@Controller
@RequestMapping(value = "/topic")
public class TopicController extends AbstractBaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TopicService topicService;
	
	@RequestMapping(value = "addTopic")
	@ResponseBody
	public IRestMessage addTopic(@Valid Topic topic){
		IRestMessage msg = getRestMessage();
		try {
			Integer num = topicService.addTopic(topic);
			if(num > 0) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
			} else {
				msg.setCode(ErrorMessage.ADD_FAILED.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
		}
		return msg;
	}
	
	@RequestMapping(value = "deleteTopic")
	@ResponseBody
	public IRestMessage deleteTopic(Topic topic){
		IRestMessage msg = getRestMessage();
		try {
			Integer num = topicService.deleteTopic(topic);
			if(num > 0) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
			} else {
				msg.setCode(ErrorMessage.DELETE_FAILED.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
		}
		return msg;
	}
	
	@RequestMapping(value = "getTopicsWithPagination")
	@ResponseBody
	public IRestMessage getTopicsWithPagination(@RequestParam(value="page",defaultValue="1",required=true) String page
			,@RequestParam(value="num") String num){
		IRestMessage msg = getRestMessage();
		try {
			PageBounds pb = new PageBounds(Integer.parseInt(page), Integer.parseInt(num));
			PageList<Topic> list = topicService.getTopicsWithPagination(pb);
			if(!CollectionUtils.isEmpty(list)) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(msg);
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
		}
		return msg;
	}
	
	
	@RequestMapping(value = "getTopics")
	@ResponseBody
	public IRestMessage getTopics(){
		IRestMessage msg = getRestMessage();
		try {
			List<Topic> list = topicService.getTopics();
			if(!CollectionUtils.isEmpty(list)) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(msg);
			} else {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
		}
		return msg;
	}
	
}
