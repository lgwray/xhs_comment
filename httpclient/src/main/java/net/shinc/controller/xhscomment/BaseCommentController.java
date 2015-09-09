package net.shinc.controller.xhscomment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.validation.Valid;

import net.shinc.common.AbstractBaseController;
import net.shinc.common.ErrorMessage;
import net.shinc.common.IRestMessage;
import net.shinc.common.TreeNode;
import net.shinc.formbean.common.AddCommentForm;
import net.shinc.formbean.common.CommentItForm;
import net.shinc.orm.mybatis.bean.common.AdminUser;
import net.shinc.orm.mybatis.bean.xhscomment.CommentCategory;
import net.shinc.service.WeiboService;
import net.shinc.service.common.impl.JnlServiceImpl;
import net.shinc.service.impl.CommentServiceImpl;
import net.shinc.service.xhscomment.BaseCommentService;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


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
	private ThreadPoolTaskExecutor threadPoolExecutor;
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@Autowired
	private JnlServiceImpl jnlService;
	
	@Autowired
	private WeiboService weiboService;
	
	@Autowired
	private BaseCommentService baseCommentService;
	private static Logger logger = LoggerFactory.getLogger(BaseCommentController.class);

	private int perGroup = 5;
	

	/**
	 * 获取分类列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCategory")
	public IRestMessage getCategory() {
		IRestMessage msg = getRestMessage();
		try {
			List list = baseCommentService.getCategoryTree();
			Map map = new HashMap();
			for(Iterator<TreeNode<CommentCategory>> it = list.iterator(); it.hasNext();){
				map.putAll(dealTreeNode(it.next()));
			}
			
			if(list != null) {
				msg.setCode(ErrorMessage.SUCCESS.getCode());
				msg.setResult(list);
			}
//			msg.setResult(map);
//			msg.setCode(ErrorMessage.SUCCESS.getCode());
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg ;
	}

	public Map dealTreeNode(TreeNode<CommentCategory> node) {
		Map map = new HashMap();
		if(node.isLeaf()) {
			Map tmp = new HashMap();
			tmp.put("name", node.getId());
			tmp.put("type", "item");
			
			map.put(node.getItem().getName(), tmp);
			return map;
		} else {
			Map tmp = new HashMap();
			tmp.put("name", node.getId());
			tmp.put("type", "folder");
			
			Map childrenMap = new HashMap();
			
			Map childMap = new HashMap();
			
			List<TreeNode<CommentCategory>> childList = node.getChild();
			for(Iterator<TreeNode<CommentCategory>> it = childList.iterator(); it.hasNext();) {
				TreeNode<CommentCategory> tmpNode = it.next();
				childMap.putAll(dealTreeNode(tmpNode));
			}
			childrenMap.put("children", childMap);
			tmp.put("additionalParameters", childrenMap);
			map.put(node.getItem().getName(), tmp);
			return map;
		}
		
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
	public IRestMessage addCategory(@RequestParam("name") String name,@RequestParam("parent") Integer parent) {
		
		IRestMessage msg = getRestMessage();
		
		if(StringUtils.isEmpty(name)) {
			msg.setCode(ErrorMessage.ERROR_PARAM_CHECK.getCode());
		}
		try {
			int id = baseCommentService.addCategory(name,parent);
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			msg.setResult(id);
			
		} catch(DuplicateKeyException de) {
			msg.setCode(ErrorMessage.ERRPR_RECORD_EXISTS.getCode());
			
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg ;
	}

	/**
	 * @param categoryId 分类id
	 * @param page 
	 * @param num
	 * @return
	 * <p>
	 * {"code":"SUCCESS","message":"交易成功","detail":null,"result":[{"id":145,"content":"a","addTime":1439972680000,"nickName":"黄易侦查营营长","categoryId":1,"category":{"id":1,"name":"??"}}],"userInfo":null,"pageInfo":{"limit":1,"page":1,"totalCount":3,"offset":0,"firstPage":true,"lastPage":false,"prePage":1,"nextPage":2,"hasPrePage":false,"hasNextPage":true,"startRow":1,"endRow":1,"totalPages":3,"slider":[1,2,3]}}
	 * </>
	 * <p>
	 * {"code":"RESULT_EMPTY","message":"暂无数据","detail":null,"result":null,"userInfo":null,"pageInfo":null}
	 * </p>
	 */
	@ResponseBody
	@RequestMapping(value = "/queryBaseComment")
	public IRestMessage queryBaseComment(@RequestParam(value="categoryId",required=true) Integer categoryId,
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value = "num",defaultValue="10") Integer num) {
		
		IRestMessage msg = getRestMessage();
		try {
			PageBounds pb = new PageBounds(page,num);
			
			List list = baseCommentService.getCommentList(categoryId, pb);
			if(CollectionUtils.isEmpty(list)) {
				msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
			} else {
				msg.setResult(list);
				msg.setPageInfo(((PageList)list).getPaginator());
				msg.setCode(ErrorMessage.SUCCESS.getCode());
			}
			return msg;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return msg ;
		}
		
		
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
	public IRestMessage addComment(AddCommentForm form) {
		
		IRestMessage msg = getRestMessage();
		String categoryId = form.getCategoryId();
		List commentList = form.getCommentList();
		
		if(StringUtils.isEmpty(categoryId)) {
			msg.setCode(ErrorMessage.ERROR_PARAM_CHECK.getCode());
		}
		
		if(CollectionUtils.isEmpty(commentList)) {
			msg.setCode(ErrorMessage.ERROR_PARAM_CHECK.getCode());
		}
		Date date = new Date();
		try {
			
			for(Iterator<Map> it = commentList.iterator();it.hasNext();) {
				
				baseCommentService.addComment(Integer.parseInt(categoryId), (String)it.next().get("content"), date);
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
			@RequestParam("type") String type,
			@RequestParam("content") String content,
			@RequestParam(value="page",defaultValue="1") String page,
			@RequestParam(value = "num",defaultValue="10") String num) {
		
		IRestMessage msg = getRestMessage();
		
		List list = new ArrayList();
		if("1".equals(queryType)) {
			try {
				List re = commentService.getCommentsByCategory(content,Integer.parseInt(num),Integer.parseInt(page));
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
				List re = commentService.getCommentsByTitle(type,content,Integer.parseInt(num),Integer.parseInt(page));
				if(re != null){
					list.addAll(re);
					msg.setCode(ErrorMessage.SUCCESS.getCode());
				} else {
					msg.setCode(ErrorMessage.RESULT_EMPTY.getCode());
				}
			} catch(Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		} else if("3".equals(queryType)){
			try {
				List list2 = weiboService.getWeiboCommentsList(type, content, page, num);
				if(!CollectionUtils.isEmpty(list2)){
					list.addAll(list2);
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

	/**
	 * <p>
	 * articleId=1&commentList[0][comment]=commentcontent&commentList[0][nick]=nickName
	 * commentList结构为:
	 * [
	 * 	{comment:...,nick:...},
	 * 	{comment:...,nick:...}
	 * ]
	 * </p>
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/commentIt")
	@ResponseBody
	public IRestMessage commentIt(@Valid CommentItForm form) {
		IRestMessage msg = getRestMessage();
		String articleId = form.getArticleId();
		List<Map> commentList = form.getCommentList();
		try {
			int allNum = 0;
			for(Iterator<Map> it = commentList.iterator(); it.hasNext();) {
				Map map = it.next();
				String comment = (String)map.get("comment");
				String nick = (String)map.get("nick");
				if(StringUtils.isEmpty(nick)) {
					nick = "新华社客户端网友";
				}
				String re = commentService.sendComment("0", articleId, comment,nick);
				Map resMap = Helper.jsonToMap(re);
				if("success".equals(resMap.get("state"))){
					allNum ++;
				}
				String userId = "-1";
				try {
					userId = String.valueOf(AdminUser.getCurrentUser().getId());
				} catch(Exception e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
				jnlService.insertJnlArticleComment(articleId, comment, null, userId, "1");
				logger.info("评论id:" + articleId + "  map:" + map + "  result:" + re);  //result:{"state":"success","message":"\u63d0\u4ea4\u6210\u529f"}
			}
			logger.info("文章ID:"+articleId+"成功评论了"+allNum+"条评论");
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			return msg;
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return msg;
		}
	}
	
	@RequestMapping(value = "/commentItMultiThread")
	@ResponseBody
	public IRestMessage commentItMultiThread(@Valid CommentItForm form) {
		IRestMessage msg = getRestMessage();
		String articleId = form.getArticleId();
		List<Map> commentList = form.getCommentList();
		
		int size = commentList.size();
		int count = size % perGroup == 0 ? size / perGroup : size / perGroup + 1;
		
		List<CommentTask> taskList = new ArrayList<CommentTask>();
		for(int i = 0; i < count; i++) {
			int fromIndex = i * perGroup;
			int toIndex = fromIndex + perGroup;
			List<Map> sub = toIndex > size ? commentList.subList(fromIndex, size) : commentList.subList(fromIndex, toIndex);
			CommentTask task = new CommentTask(jnlService, sub, articleId);
			taskList.add(task);
		}
		
		CommentCallable task = new CommentCallable(msg, taskList);
		Future<IRestMessage> future = threadPoolExecutor.submit(task);
		try {
			IRestMessage msg2 = future.get();
			return msg2;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return msg;
	}
	
	class CommentTask implements Callable<Boolean> {

		private JnlServiceImpl jnlservice;
		private List<Map> list;
		private String articleId;
		
		public CommentTask(){}
		
		public CommentTask(JnlServiceImpl jnlService, List<Map> list,String articleId) {
			this.jnlservice = jnlService;
			this.list = new ArrayList<Map>();
			if(list != null) {
				this.list.addAll(list);
			}
			this.articleId = articleId;
		}
		
		@Override
		public Boolean call() throws Exception {
			try {
				for (Iterator<Map> it = this.list.iterator(); it.hasNext();) {
					Map map = it.next();
					String comment = (String) map.get("comment");
					String nick = (String) map.get("nick");
					if (StringUtils.isEmpty(nick)) {
						nick = "新华社客户端网友";
					}
					String result = commentService.sendComment("0", this.articleId, comment, nick);
					String userId = "-1";
					try {
						userId = String.valueOf(AdminUser.getCurrentUser().getId());
					} catch(Exception e) {
						logger.error(ExceptionUtils.getStackTrace(e));
					}
					jnlService.insertJnlArticleComment(articleId, comment, null, userId, "1");
					logger.info("评论id:" + articleId + "  map:" + map + "  result:" + result);
				}
				return true;
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
				return false;
			}
		}
	}
	
	class CommentCallable implements Callable<IRestMessage> {
		
		private List<CommentTask> list;
		
		private int taskSize;
		
		private int taskDone = 0;
		
		private IRestMessage msg;
		
		public CommentCallable(){}
		
		public CommentCallable(IRestMessage msg, List<CommentTask> list) {
			this.msg = msg;
			this.list = list;
			this.taskSize = list.size();
		}

		@Override
		public IRestMessage call() throws Exception {
			Map<Future<Boolean>,Boolean> resmap= new HashMap<Future<Boolean>,Boolean>();
			for(int i=0; i< taskSize; i++) {
				 Future<Boolean> future = threadPoolExecutor.submit(list.get(i));
				 resmap.put(future, false);
			}
			
			logger.info("group size:" + resmap.size());
			while(taskDone != taskSize) {
				logger.info("taskSize: " + taskSize + "\ttaskDone: " + taskDone);
				Set<Future<Boolean>> keySet = resmap.keySet();
				for (Future<Boolean> future : keySet) {
					if(!resmap.get(future)) {
						if(future.isDone()) {
							taskDone ++ ;
							resmap.put(future, true);
						}
					}
				}
			}
			
			msg.setCode(ErrorMessage.SUCCESS.getCode());
			return msg;
		}
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String str = "%22id%22:%22310%22,%22comment%22:%22%E5%BC%BA%E5%9B%BD%E6%A2%A6%E9%A6%96%E5%85%88%E6%98%AF%E5%BC%BA%E5%86%9B%E6%A2%A6%EF%BC%81%22,%22nick%22:%22xiaoxi940824%22},{%22id%22:%22311%22,%22comment%22:%22%E4%B8%80%E4%B8%AA%E5%85%A8%E4%B8%96%E7%95%8C%E8%BD%B0%E5%8A%A8%E7%9A%84%E5%A4%A7%E5%9E%8B%E9%98%85%E5%85%B5%22,%22nick%22:%22%E5%86%B7%E9%9B%A8%E5%A4%9C01%22},{%22id%22:%22314%22,%22comment%22:%22%E5%9C%A8%E9%98%85%E5%85%B5%E4%B8%AD%E5%90%91%E4%B8%96%E4%BA%BA%E5%B1%95%E7%A4%BA%E5%85%88%E8%BF%9B%E7%9A%84%E6%AD%A6%E5%99%A8%E8%A3%85%E5%A4%87%E6%98%AF%E4%B8%96%E7%95%8C%E5%90%84%E5%9B%BD%E7%9A%84%E9%80%9A%E8%A1%8C%E5%81%9A%E6%B3%95%EF%BC%8C%E8%BF%99%E6%AC%A1%E9%98%85%E5%85%B5%E9%9B%86%E4%B8%AD%E5%B1%95%E7%A4%BA%E4%BA%8684%E7%9A%84%E6%96%B0%E5%9E%8B%E6%AD%A6%E5%99%A8%E8%A3%85%E5%A4%87%EF%BC%8C%E4%BD%93%E7%8E%B0%E4%BA%86%E6%88%91%E5%86%9B%E7%8E%B0%E4%BB%A3%E5%8C%96%E5%BB%BA%E8%AE%BE%E7%9A%84%E5%8F%91%E5%B1%95%E6%B0%B4%E5%B9%B3%EF%BC%8C%E8%BF%99%E6%98%AF%E5%BC%80%E6%94%BE%E9%80%8F%E6%98%8E%E3%80%81%E5%92%8C%E5%B9%B3%E5%8F%8B%E5%A5%BD%E7%9A%84%E4%BD%93%E7%8E%B0%EF%BC%8C%E4%BC%A0%E9%80%92%E7%9A%84%E6%98%AF%E4%B8%8E%E4%B8%96%E7%95%8C%E5%90%84%E5%9B%BD%E4%BA%BA%E6%B0%91%E4%B8%80%E9%81%93%E5%85%B1%E5%90%8C%E7%BB%B4%E6%8A%A4%E4%B8%96%E7%95%8C%E5%92%8C%E5%B9%B3%E7%9A%84%E6%AD%A3%E8%83%BD%E9%87%8F%EF%BC%81%22,%22nick%22:%22%22";
//		String str1 = "data=%7B%22mesgStatus%22%3A%225%22%2C%22messageId%22%3A%22f__-sIzQA04e1xq5%7C3148249%7CAjV8vfwEByVtNH09Uay9PalRexV2YRVM9fPV7WXKtCPD%40apoll%22%7D";
//		String str2 = "data=%7B%22mesgStatus%22%3A%225%22%2C%22messageId%22%3A%22f__-sI0JJzIUOLA8%7C3146984%7CAjV8vfwEByVtNH09Uay9PalRexV2YRVM9fPV7WXKtCPD%40apoll%22%7D";
//		String result1 = URLDecoder.decode(str1,"UTF-8");
//		String result2 = URLDecoder.decode(str2,"UTF-8");
		
//		String enc = "这次阅兵集中展示了84%的";
//		String encResult =URLEncoder.encode(enc);
//		System.out.println(result1);
//		System.out.println(result2);
//		System.out.println(encResult);
		
		int perGroup = 6;
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		
		int size = list.size();
		int count = size % perGroup == 0 ? size / perGroup : size / perGroup + 1;
		
		for(int i = 0; i < count; i++) {
			int fromIndex = i * perGroup;
			int toIndex = fromIndex + perGroup;
			List sub = toIndex > size ? list.subList(fromIndex, size) : list.subList(fromIndex, toIndex);
			printList(sub);
		}
		
	}
	
	public static void printList(List list) {
		System.out.println("\n=======begin========");
		for (Object obj : list) {
			System.out.println(obj);
		}
		System.out.println("=======end==========");
	}
}
