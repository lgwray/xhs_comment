package net.shinc.quartz.task.xhscomment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class GenerateCommentJob {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private int recentDays = 1;
	
	@Autowired
	@Qualifier("sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private ApplicationContext context;
	
	@Value("${xhs.single.article.targetSize}")
	private Integer targetSize;
	
	public void work() {
		logger.info("begin");

		try {
			List<String> articleIdList = new ArrayList<String>();
			articleIdList.add("255243");
			for(String id : articleIdList) {
				Map param = new HashMap();
				param.put("id", id);
				Map tmp = this.sqlSession.selectOne("net.shinc.orm.mybatis.mappers.xhscomment.ArticleMapper.selectCommentNumByArticleId", param);
				String local = (String)tmp.get("comment_local");//抓取到本地的数据总数
				String total = (String)tmp.get("comment_total");//抓取到的总数
				
				
			}
			
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		logger.info("end");
	}
	
	
	
}
