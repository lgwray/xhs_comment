package net.shinc.quartz.task.xhscomment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.utils.Helper;
import net.shinc.utils.RandomUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 获取新闻详细信息任务
 * <p>
 * 	查询近一段时间新闻，循环抓取评论数
 * </p>
 * @author zhangtaichao 2015年9月11日
 *
 */
public class ArticleDetailTask implements Runnable {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private int recentDays = 7;
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private CloseableHttpClient httpClient;
	
	@Autowired
	private ApplicationContext context;
	
	@Value("${ArticleDetailTask.intevalSeconds}")
	private int intevalSeconds;

	public void start() {
		ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor)context.getBean("threadPoolExecutor");
		ArticleDetailTask t = (ArticleDetailTask)context.getBean("articleDetailTask");
		Thread cqpt = executor.createThread(t);
		cqpt.setName("ArticleDetailTask-0");
		cqpt.setDaemon(true);
		cqpt.start();
	}
	
	@Override
	public void run() {
		logger.info("started");
		while(true) {
			try {
				Map map = new HashMap();
				map.put("recentDays", recentDays);
				List<Map> list = this.sqlSession.selectList("net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper.selectRecent", map);
				
				if(list != null && list.size() > 0) {
					for(Map tmp : list) {
						String detailurl = (String)tmp.get("detail_url");
						if(!StringUtils.isEmpty(detailurl)) {
							HttpGet get = new HttpGet(detailurl);
							get.setHeader("X-Forwarded-For", RandomUtils.generateIp());
							
							CloseableHttpResponse response = null;
							try {
								response = httpClient.execute(get);
								HttpEntity entity = response.getEntity();
								String result =  EntityUtils.toString(entity);
								Map resultMap = Helper.jsonToMap(result);
								List data = (List)resultMap.get("data");
								if(!CollectionUtils.isEmpty(data)) {
									String comment = (String)((Map)data.get(0)).get("comment");
									
									String numStr = comment;
									try {
										int num = Integer.parseInt(comment);
										numStr = String.valueOf(num);
									} catch (Exception e) {
										if(comment.indexOf("万") != -1) {
											String strnum = comment.substring(0,comment.indexOf("万"));
											try {
												Double num = Double.parseDouble(strnum);
												num = num * 10000;
												numStr = String.valueOf(num.intValue());
											} catch (Exception e1) {
											}
										}
									}
									Map updateMap = new HashMap();
									updateMap.put("comment", numStr);
									updateMap.put("id", tmp.get("id"));
									
									this.sqlSession.update("net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper.updateComment", updateMap);
								}
							} catch (Exception e) {
								logger.error(ExceptionUtils.getStackTrace(e));
							}
						}
								
					}
				}
				
				
			} catch(Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			
			try {
				Thread.sleep(1000 * intevalSeconds);
			} catch (InterruptedException e) {
			}
			
		}
		
	}

}
