package net.shinc.quartz.task.xhscomment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import net.shinc.utils.Helper;
import net.shinc.utils.RandomUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;

/**
 * <b>quartz job</b>
 * <p>
 * 	查询最近某段时间内的sh_article表，根据ID查询新华社内部的每条新闻的评论列表
 * </p>
 * @author zhangtaichao 2015年9月11日
 *
 */
public class FetchCommentListJob {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private int recentDays = 1;
	
	@Value("${xhs.api.fetchCommentListUrl}")
	private String fetchCommentListUrl;
	/**
	 * 获取评论列表pageSize
	 */
	@Value("${xhs.api.fetchCommentList.pageSize}")
	private int pageSize;
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private CloseableHttpClient httpClient;
	
	@Autowired
	private ApplicationContext context;
	
	public void work() {
		logger.info("begin");
		
		try {
			ExecutorService executor = (ExecutorService)context.getBean("prototypePoolExecutor");
			Map map = new HashMap();
			if(recentDays <= 0) {
				map.put("recentDays", 0);
			} else {
				map.put("recentDays", recentDays - 1);
			}
			
			List<Map<String,Object>> list = this.sqlSession.selectList("net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper.selectRecentWithComment", map);
			
			if(list != null && list.size() > 0) {
				for(Map tmp : list) {
					try {
						FetchCommentListThread fetchCommentListThread = (FetchCommentListThread) context.getBean("fetchCommentListThread");
						fetchCommentListThread.setMap(tmp);
						executor.execute(fetchCommentListThread);
					} catch(RejectedExecutionException e) {
						break;
					}
				}
				executor.shutdown();
				executor.awaitTermination(1, TimeUnit.HOURS);
			}
			
			
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		logger.info("end");
	}
	
	public static class FetchCommentListThread implements Runnable {
		private Logger logger = LoggerFactory.getLogger(getClass());
		
		@Value("${xhs.api.fetchCommentListUrl}")
		private String fetchCommentListUrl;
		/**
		 * 获取评论列表pageSize
		 */
		@Value("${xhs.api.fetchCommentList.pageSize}")
		private int pageSize;
		
		@Autowired
		@Qualifier("sqlSession")
		private SqlSessionTemplate sqlSession;
		
		@Autowired
		private CloseableHttpClient httpClient;
		
		@Autowired
		private ApplicationContext context;
		
		private Map map;
		
		
		

		@Override
		public void run() {
			
			if(map == null) return ;
			
			Long commentLocal = (Long)map.get("comment_local");
			
			//计算起始页
			Long start =  commentLocal / pageSize + 1;
			
			String id = (String)map.get("id");
			boolean hasMore = false;
			hasMore = fetchCommentList(id, start, pageSize);
			while(hasMore) {
				start++;
				hasMore = fetchCommentList(id, start, pageSize);
			}
			
		}
		
		private boolean fetchCommentList(String articleId,long pageNumber,int pageSize) {
			HttpPost post = new HttpPost(fetchCommentListUrl);

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id",articleId));
			params.add(new BasicNameValuePair("pn", String.valueOf(pageNumber)));
			params.add(new BasicNameValuePair("ps", String.valueOf(pageSize)));
			try {
				post.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			} catch (UnsupportedEncodingException e1) {
			}
				
			post.setHeader("X-Forwarded-For", RandomUtils.generateIp());
			
			CloseableHttpResponse response = null;
			try {
				response = httpClient.execute(post);
				HttpEntity entity = response.getEntity();
				String result =  EntityUtils.toString(entity);
				Map resultMap = Helper.jsonToMap(result);
				
				if("success".equals(resultMap.get("state"))) {
					List data = (List)resultMap.get("data");
					if(!CollectionUtils.isEmpty(data)) {
						
						for(Object tmp : data) {
							if(tmp != null) {
								((Map)tmp).put("article_id", articleId);
								try {
									this.sqlSession.insert("net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper.insertXhsCommentList", tmp);
								} catch(DuplicateKeyException e) {
								}
							}
						} 
						Double hasmore = (Double)resultMap.get("hasmore");
						if(hasmore.intValue() == 1) {
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
				
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
				return false;
			}
		}


		public Map getMap() {
			return map;
		}

		public void setMap(Map map) {
			this.map = map;
		}
		
	}
	
	
	
}
