package net.shinc.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.shinc.utils.Helper;
import net.shinc.utils.ParamUtils;
import net.shinc.utils.RandomUtils;
import net.shinc.utils.StringUtils;
import net.shinc.utils.UnicodeUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

/**
 * @ClassName NewsServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月14日 上午1:05:48
 */
@Service
public class CommentServiceImpl {

	@Autowired
	private CloseableHttpClient httpClient;
	
	@Value("${phpUrl}")
	private String remoteApiUrl = "http://192.168.1.222";
	
	@Value("${sendCommentUrl}")
	private String sendCommentUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment";

	private Logger logger = LoggerFactory.getLogger(getClass());

	
	/**根据title抓取评论
	 * @param title
	 * @param pageSize 每页条数
	 * @param page 第page页
	 * @return
	 * @throws URISyntaxException 
	 */
	public List getCommentsByTitle(String type,String title,int pageSize, int page ) throws URISyntaxException {
		
		pageSize = pageSize < 1 ? 200 :pageSize;
		page = page < 1 ? 1 : page;
		URI url = URI.create(getRemoteApiUrl() + "/match.php");
		
		URI u = new URIBuilder()
	        .setScheme(url.getScheme())
	        .setHost(url.getHost())
	        .setPath(url.getPath())
	        .setPort(url.getPort())
	        .setParameter("type", type)
	        .setParameter("str", title)
	        .setParameter("num", String.valueOf(pageSize))
	        .setParameter("page", String.valueOf(page))
	        .build();
		
		
		HttpGet get = new HttpGet(u);
		
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String result =  EntityUtils.toString(entity);
			Gson gson = new Gson();
			return gson.fromJson(result, ArrayList.class);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}finally {
			try {
				response.close();
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return null;
	}
	
	/**根据分类抓取评论
	 * @param title
	 * @param pageSize 每页条数
	 * @param page 第page页
	 * @return
	 * @throws URISyntaxException 
	 */
	public List getCommentsByCategory(String title,int pageSize, int page ) throws URISyntaxException {
		
		pageSize = pageSize < 1 ? 1000 :pageSize;
		page = page < 1 ? 1 : page;
		
		URI url = URI.create(getRemoteApiUrl() + "/category.php");
		
		URI u = new URIBuilder()
	        .setScheme(url.getScheme())
	        .setHost(url.getHost())
	        .setPort(url.getPort())
	        .setPath(url.getPath())
	        .setParameter("catname", title)
	        .setParameter("num", String.valueOf(pageSize))
	        .setParameter("page", String.valueOf(page))
	        .build();
		
		
		HttpGet get = new HttpGet(u);
		
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String result =  EntityUtils.toString(entity);
			Gson gson = new Gson();
			return gson.fromJson(result, ArrayList.class);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}finally {
			try {
				response.close();
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return null;
	}

	/**
	 * 发送评论
	 * @param userId
	 * @param articleId
	 * @param content
	 * @param username
	 * @return
	 */
	public String sendComment(String userId, String articleId, String content,String username) {
		HttpPost post = new HttpPost(getSendCommentUrl());
		post.setHeader("X-Forwarded-For", RandomUtils.generateIp());
		post.setHeader("User-Agent", RandomUtils.getUserAgentRandom());
		CloseableHttpResponse response = null;
		try {
			logger.info("userId="+userId+",articlId="+articleId+",content="+content+",username="+username);
			post.setEntity(new UrlEncodedFormEntity(ParamUtils.getDiscussParamList(articleId, content, userId,username),HTTP.UTF_8));
			CloseableHttpClient httpClient = new org.apache.http.impl.client.DefaultHttpClient();
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String result =  EntityUtils.toString(entity);
			String after = StringUtils.formatString(result);
			logger.info	(UnicodeUtils.decodeUnicode(after));
			return after;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}
	
	public static void main(String[] args) {
		CommentServiceImpl service = new CommentServiceImpl();
		service.sendComment("1", "1670441", "喜欢", "可口可乐加冰");
	}
	
	public String getRemoteApiUrl() {
		return remoteApiUrl;
	}

	public void setRemoteApiUrl(String remoteApiUrl) {
		this.remoteApiUrl = remoteApiUrl;
	}

	public String getSendCommentUrl() {
		return sendCommentUrl;
	}

	public void setSendCommentUrl(String sendCommentUrl) {
		this.sendCommentUrl = sendCommentUrl;
	}

	
}
