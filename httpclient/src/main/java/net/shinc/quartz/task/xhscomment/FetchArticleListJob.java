package net.shinc.quartz.task.xhscomment;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import net.shinc.service.NewsService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;



/**
 * <b>quartz job</b>
 * <p>发送新华社接口，查询新闻列表</p>
 * @author zhangtaichao 2015年9月14日
 *
 */
public class FetchArticleListJob {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ApplicationContext context;
	
	public enum ArticleCategory {
		
		要闻("470" , "4001","0" ,"要闻"),
		体育("461" , "4002","1" ,"体育"),
		国际("462" , "4002","2" ,"国际"),
		财经("463" , "4002","3" ,"财经"),
		军事("464" , "4002","4" ,"军事"),
		汽车("477" , "4002","5" ,"汽车"),
		图片("479" , "4002","6" ,"图片"),
		视频("480" , "4002","7" ,"视频"),
		评论("235" , "4003","8" ,"评论"),
		社会("3172", "4003","9" ,"社会"),
		科技("3173", "4003","10","科技"),
		动新闻("3174", "4003","11","动新闻");
		
		
		private String cid;
		private String ctype;
		private String value;
		private String remark;
		ArticleCategory(String cid,String ctype,String value,String remark) {
			this.cid = cid;
			this.ctype = ctype;
			this.value = value;
			this.remark = remark;
					
		}
		public String getCid() {
			return cid;
		}
		public void setCid(String cid) {
			this.cid = cid;
		}
		public String getCtype() {
			return ctype;
		}
		public void setCtype(String ctype) {
			this.ctype = ctype;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
	}
	
	public void work() {
		logger.info("start");
		ExecutorService executor = (ExecutorService)context.getBean("prototypePoolExecutor");
		
		for(ArticleCategory ac : ArticleCategory.values()) {
			FetchArticleListThread thread = (FetchArticleListThread)context.getBean("fetchArticleListThread");
			thread.setCategory(ac);
			thread.setUserId("0");
			executor.execute(thread);
		}
		executor.shutdown();
		try {
			executor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
		}
		logger.info("end");
	}
	
	public static class FetchArticleListThread implements Runnable {
		private Logger logger = LoggerFactory.getLogger(getClass());
		private String userId;
		private ArticleCategory category;
		
		@Autowired
		private NewsService newsService;
		
		@Autowired
		private CloseableHttpClient httpClient;
		
		@Autowired
		@Qualifier("sqlSession")
		private SqlSessionTemplate sqlSession;
		@Override
		public void run() {
			List articleList = newsService.getNewsList(getUserId(), category.getCid(),category.getCtype());
			if(!CollectionUtils.isEmpty(articleList)) {
				for(Object o : articleList) {
					try {
						Map tmp = (Map)o;
						tmp.put("category", category.getValue());
						if(StringUtils.isEmpty(tmp.get("releaseDate"))) {
							tmp.put("releaseDate", new Date());
						}
						this.sqlSession.insert("net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper.insertArticle",o);
					} catch(DuplicateKeyException e) {
						
					} catch(Exception e) {
						logger.error(ExceptionUtils.getStackTrace(e));
					}
				}
			}
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public ArticleCategory getCategory() {
			return category;
		}

		public void setCategory(ArticleCategory category) {
			this.category = category;
		}

		
	}
	
}
