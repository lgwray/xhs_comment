package net.shinc.controller.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.shinc.common.AbstractBaseController;
import net.shinc.formbean.common.QueryCommentForm;
import net.shinc.service.impl.CommentServiceImpl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private static Logger logger = LoggerFactory.getLogger(CommentController.class);

	
	

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
			List re = commentService.getCommentsByCategory(form.getNewsType(), form.getPageSize(), form.getPage());
			list.addAll(re);
			
		} else if("2".equals(con)) {
			try {
				List re = commentService.getCommentsByTitle(form.getNewsType(), form.getPageSize(), form.getPage());
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
			List list = form.getCommentList();
			for(Iterator<String> it = list.iterator(); it.hasNext();) {
				String content = it.next();
				String re = commentService.sendComment("0", id, content);
				logger.info("评论id:" + id + "  comment:" + content + "  result:" + re);
			}
			return "success";
		} catch(Exception e) {
			ExceptionUtils.getStackTrace(e);
			return "error";
		}
		
	}
	

}
