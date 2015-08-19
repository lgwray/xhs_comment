package net.shinc.controller.xhscomment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.service.impl.CommentServiceImpl;
import net.shinc.service.xhscomment.BaseCommentService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * 基础评论信息及其分析信息维护
 * @author zhangtaichao 2015年8月14日
 *
 */
@Controller
@RequestMapping(value = "/basecomment")
public class BaseCommentController extends AbstractBaseController {
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@Autowired
	private BaseCommentService baseCommentService;
	private static Logger logger = LoggerFactory.getLogger(BaseCommentController.class);

	
	

	/**
	 * 获取分类列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCategory")
	public IRestMessage getCategory() {
		IRestMessage msg = getRestMessage();
		try {
			List list = baseCommentService.getCategory();
			if(list != null) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
			}
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg ;
	}

	
	/**
	 * @param name
	 * @return
	 * <p>
	 * {"code":"SUCCESS","message":"交易成功","detail":null,"result":null,"userInfo":null}
	 * </p>
	 * <p>
	 * {"code":"ERRPR_RECORD_EXISTS","message":"记录已存在","detail":null,"result":null,"userInfo":null}
	 * </p>
	 */
	@ResponseBody
	@RequestMapping(value = "/addCategory")
	public IRestMessage addCategory(@RequestParam("name") String name) {
		
		IRestMessage msg = getRestMessage();
		
		if(StringUtils.isEmpty(name)) {
			msg.setCode(ErrorMessage.ERROR_PARAM_CHECK.getCode());
		}
		try {
			baseCommentService.addCategory(name);
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			
		} catch(DuplicateKeyException de) {
			msg.setCode(ErrorMessage.ERRPR_RECORD_EXISTS.getCode());
			
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg ;
	}

	
	/**
	 * @param categoryId 分类ID
	 * @param commentList 评论列表
	 * @return
	 * <p>
	 * {"code":"SUCCESS","message":"交易成功","detail":null,"result":null,"userInfo":null}
	 * </p>
	 */
	@ResponseBody
	@RequestMapping(value = "/addComment")
	public IRestMessage addComment(@RequestParam("categoryId") String categoryId,
			@RequestParam("commentList") List commentList) {
		
		IRestMessage msg = getRestMessage();
		
		if(StringUtils.isEmpty(categoryId)) {
			msg.setCode(ErrorMessage.ERROR_PARAM_CHECK.getCode());
		}
		
		if(CollectionUtils.isEmpty(commentList)) {
			msg.setCode(ErrorMessage.ERROR_PARAM_CHECK.getCode());
		}
		Date date = new Date();
		try {
			
			for(Iterator<String> it = commentList.iterator();it.hasNext();) {
				
				baseCommentService.addComment(Integer.parseInt(categoryId), it.next(), date);
			}
			msg.setCode(ErrorMessage.SUCCESS.getCode());
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		return msg ;
	}

	
	/**
	 * 调用php接口，根据关键字或类别查询评论数据
	 * @param queryType 1=根据类别 2=根据内容
	 * @param content 查询条件
	 * @param page 页码
	 * @param num 每页条数
	 * @return
	 * <p>
	 * {
			"code": "SUCCESS",
			"message": "交易成功",
			"detail": null,
			"result": [{
				"id": "4488",
				"catid": null,
				"newsid": "897",
				"comment": "不可能，我美爹最牛逼了！",
				"nick": "尿塌神厕",
				"addtime": "2015-08-13 23:40:49",
				"news_type": null
			}, {
				"id": "4489",
				"catid": null,
				"newsid": "897",
				"comment": "环球时报、太行军事<br>呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵呵",
				"nick": "萍水相逢ZD5G",
				"addtime": "2015-08-13 22:56:42",
				"news_type": null
			}],
			"userInfo": null
		}
	 * </P
	 */
	@ResponseBody
	@RequestMapping(value = "/queryRemoteComment")
	public IRestMessage queryRemoteComment(@RequestParam("queryType") String queryType,
			@RequestParam("content") String content,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value = "num",defaultValue="10") String num) {
		
		IRestMessage msg = getRestMessage();
		
		List list = new ArrayList();
		if("1".equals(queryType)) {
			try {
				List re = commentService.getCommentsByCategory(content,Integer.parseInt(page),Integer.parseInt(num));
				if(re != null){
					list.addAll(re);
					msg.setCode(ErrorMessage.SUCCESS.getCode());
				} else {
					msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
				}
			} catch(Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			
		} else if("2".equals(queryType)) {
			try {
				List re = commentService.getCommentsByTitle(content,Integer.parseInt(page),Integer.parseInt(num));
				if(re != null){
					list.addAll(re);
					msg.setCode(ErrorMessage.SUCCESS.getCode());
				} else {
					msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
				}
			} catch(Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		} else {
			msg.setCode(ErrorMessage.ERROR_PARAM_CHECK.getCode());
		}
		msg.setResult(list);
		return msg ;
		
	}

	
	
}
