package net.shinc.controller.xhscomment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.formbean.common.QueryCommentForm;
import net.shinc.service.common.AdminUserService;
import net.shinc.service.common.impl.JnlServiceImpl;
import net.shinc.service.impl.CommentServiceImpl;
import net.shinc.service.xhscomment.BaseCommentService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zhangtaichao 2015年8月14日
 *
 */
@Controller
public class CommentController extends AbstractBaseController {
	
	

	@Autowired
	private CommentServiceImpl commentService;
	@Autowired
	private JnlServiceImpl jnlServiceImpl;
	@Autowired
	private AdminUserService adminUserService;
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private BaseCommentService baseCommentService;

	/**
	 * 首页
	 * @return
	 */
//	@ResponseBody
	@RequestMapping(value = "/commentPre")
	public String commentPre(@RequestParam("id") String id,@RequestParam("title") String title ,Model model) {
		model.addAttribute("id", id);
		model.addAttribute("title", title);
		return "commentPre" ;
	}

	@RequestMapping(value = "/commentResult")
	@ResponseBody
	public List commentResult(QueryCommentForm form) {
		String con = form.getCondition();
		List list = new ArrayList();
		if("1".equals(con)) {
			try {
				List re = commentService.getCommentsByCategory(form.getNewsType(), form.getPageSize(), form.getPage());
				list.addAll(re);
			} catch(Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			
		} else if("2".equals(con)) {
			try {
				List re = commentService.getCommentsByTitle(form.getType(),form.getNewsType(), form.getPageSize(), form.getPage());
				list.addAll(re);
			} catch(Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		} else {
			logger.error("erro condition:" + con);
		}
		
		return list ;
	}
	
	@RequestMapping(value = "/commentIt")
	@ResponseBody
	public String commentIt(QueryCommentForm form) {
		try {
			String id = form.getId();
			List list = form.getList();
			for(Iterator<Map> it = list.iterator(); it.hasNext();) {
				Map map = it.next();
				 
				String comment = (String)map.get("comment");
				String nick = (String)map.get("nick");
				if(StringUtils.isEmpty(nick)) {
					nick = "新华社客户端网友";
				}
				String re = commentService.sendComment("0", id, comment,nick);
				logger.info("评论id:" + id + "  map:" + map + "  result:" + re);
			}
			return "success";
		} catch(Exception e) {
			ExceptionUtils.getStackTrace(e);
			return "error";
		}
		
	}
	
	/**
	 * 绩效列表
	 * @return
	 */
	@RequestMapping(value = "/selectCommentJnl")
	@ResponseBody
	public IRestMessage selectCommentJnl(String userId, String addDate,String pageIndex,String pageCount) {
		IRestMessage msg = getRestMessage();
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("addDate", addDate);
		map.put("pageIndex", pageIndex);
		map.put("pageCount", pageCount);
		List list = jnlServiceImpl.selectCommentJnl(map);
		int count = jnlServiceImpl.selectCommentJnlCount(map);
		Map resultMap = new HashMap();
		resultMap.put("list", list);
		resultMap.put("count", count);
		logger.info("绩效列表==>" + list.toString());
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(resultMap); 
		return msg;
	}
	
	/**
	 * 获取管理员列表
	 * @return
	 */
	@RequestMapping(value = "/getAllAdminUserList")
	@ResponseBody
	public IRestMessage getAllAdminUserList() {
		IRestMessage msg = getRestMessage();
		List list = adminUserService.getAllAdminUserList();
		logger.info("绩效列表==>" + list.toString());
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(list); 
		return msg;
	}
	
	/**
	 * 获得每天的总评论数
	 * @return
	 */
	@RequestMapping(value = "/getLocalEverydayCommentsNums")
	@ResponseBody
	public IRestMessage getLocalEverydayCommentsNums() {
		IRestMessage msg = getRestMessageWithoutUser();
		List list = baseCommentService.getLocalEverydayCommentsNums();
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(list); 
		return msg;
	}
	
	/**
	 * 获得当天的总评论数
	 * @return
	 */
	@RequestMapping(value = "/getTodayCommentsNums")
	@ResponseBody
	public IRestMessage getTodayCommentsNums() {
		IRestMessage msg = getRestMessageWithoutUser();
		List list = baseCommentService.getTodayCommentsNums();
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(list); 
		return msg;
	}

}
