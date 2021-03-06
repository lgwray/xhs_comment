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
import net.shinc.service.xhscomment.CountService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


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

	@Autowired
	private CountService countService;
	
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
	 * 查看已发布的评论
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/selectCommentJnl")
	@ResponseBody
	public IRestMessage selectCommentJnl(@RequestParam(value="userId",required=false,defaultValue="0") String userId, 
			@RequestParam(value="addDate",required=true) String addDate,
			@RequestParam(value="articleid",required=false) String articleid,
			@RequestParam(value="content",required=false) String content,
			@RequestParam(value="page",required=false,defaultValue="1") Integer page,
			@RequestParam(value = "num",required=false,defaultValue="50") Integer num
			) {
		
		IRestMessage msg = getRestMessage();
		if(ErrorMessage.NEED_LOGIN.getCode().equals(msg.getCode())) {
			return msg;
		}
		
		PageBounds pb = new PageBounds(page,num);
		
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("addDate", addDate);
		map.put("articleid", articleid);
		map.put("content", content.trim());
		logger.info(map.toString());
		
		List list = jnlServiceImpl.selectCommentJnl(map,pb);
		int sum = jnlServiceImpl.selectCommentJnlCount(map);
		Integer autoSum = countService.getCommentNumByUserId(addDate, userId, "2");
		Integer handSum = countService.getCommentNumByUserId(addDate, userId, "1");
		
		Map resultMap = new HashMap();
		resultMap.put("list", list);
		resultMap.put("count", sum);
		resultMap.put("autoSum", autoSum);
		resultMap.put("handSum", handSum);
		logger.info("绩效列表==>" + list.toString());
		
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(resultMap); 
		msg.setDetail(String.valueOf(list.size()));
		PageList pagelist = (PageList)list;
		msg.setPageInfo(pagelist.getPaginator());
		
		return msg;
	}
	
	/**
	 * 取消发送评论
	 * @return
	 */
	@RequestMapping(value = "/cancelSend")
	@ResponseBody
	public IRestMessage cancelSend(@RequestParam(value="sendId") String sendId) {
		IRestMessage msg = getRestMessageWithoutUser();
		Integer cancelSend = jnlServiceImpl.cancelSend(sendId);
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(String.valueOf(cancelSend)); 
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
		Map map = baseCommentService.getTodayCommentsNums();
		if(!CollectionUtils.isEmpty(map)) {
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			msg.setResult(map); 
		} else {
			msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
		}
		return msg;
	}

	/**
	 * 获得新华社当天的总评论数
	 * @return
	 */
	@RequestMapping(value = "/getTodayxhsNums")
	@ResponseBody
	public IRestMessage getTodayxhsNums() {
		IRestMessage msg = getRestMessageWithoutUser();
		Map map = baseCommentService.getTodayRemoteNums();
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(map); 
		return msg;
	}
	
	/**
	 * 获得当天的新华社总评论数
	 * @return
	 */
	@RequestMapping(value = "/getTodayxhsNumsByCategory")
	@ResponseBody
	public IRestMessage getTodayxhsNumsByCategory(String categoryid) {
		IRestMessage msg = getRestMessageWithoutUser();
		Map map = baseCommentService.getTodayRemoteNumsByCategory(categoryid);
		msg.setCode(ErrorMessage.SUCCESS.getCode());
		msg.setResult(map); 
		return msg;
	}
}
