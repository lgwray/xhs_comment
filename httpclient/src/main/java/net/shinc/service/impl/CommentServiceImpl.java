package net.shinc.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import net.shinc.utils.Helper;
import net.shinc.utils.ParamUtils;
import net.shinc.utils.RandomUtils;

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

//	/**
//	 * 根据newsType抓取评论
//	 * @param title
//	 * @return
//	 */
//	public static List getCommentsByNewsType(String phpUrl, String newsType,String newsCount) {
//		String url = phpUrl + "category?catname=" + newsType;
//		if(newsCount != null && !"".equals(newsCount.trim())){
//			url = url + "&num=" + newsCount;
//		}
//		String uri = dealUrl(url).toString();
//		String comments = HttpXmlClient.get(uri);
//		logger.info("爬虫到的评论==>" + comments);
//		if (null != comments && !"".equals(comments)) {
//			List list = Helper.jsonToList(comments);
//			logger.info("爬虫到评论条数==>" + list.size());
//			return list;
//		}
//		return null;
//	}
	
	
	/**根据title抓取评论
	 * @param title
	 * @param pageSize 每页条数
	 * @param page 第page页
	 * @return
	 * @throws URISyntaxException 
	 */
	public List getCommentsByTitle(String title,int pageSize, int page ) throws URISyntaxException {
		
		pageSize = pageSize < 1 ? 200 :pageSize;
		page = page < 1 ? 1 : page;
//		U url = getRemoteApiUrl();// + "/match?str=" + title + "&num=" + pageSize + "&page=" + page;
		URI url = URI.create(getRemoteApiUrl() + "/match");
		
		URI u = new URIBuilder()
	        .setScheme(url.getScheme())
	        .setHost(url.getHost())
	        .setPath(url.getPath())
	        .setPort(url.getPort())
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
	
	public static void main(String [] args) {
		URI uri = URI.create("http://192.168.1.22:8080");
		System.out.println(uri);
		try {
			URI u = new URIBuilder()
			.setScheme(uri.getScheme())
			.setHost(uri.getHost())
			.setPath(uri.getPath())
			.setParameter("q", "httpclient")
			.setParameter("btnG", "Google Search")
			.setParameter("aq", "f")
			.setParameter("oq", "|dafs|")
			.build();
			System.out.println(u);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**根据title抓取评论
	 * @param title
	 * @param pageSize 每页条数
	 * @param page 第page页
	 * @return
	 * @throws URISyntaxException 
	 */
	public List getCommentsByCategory(String title,int pageSize, int page ) throws URISyntaxException {
		
		pageSize = pageSize < 1 ? 1000 :pageSize;
		page = page < 1 ? 1 : page;
		
		URI url = URI.create(getRemoteApiUrl() + "/category");
		
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

	
	/**根据title抓取评论
	 * @param title
	 * @param pageSize 每页条数
	 * @param page 第page页
	 * @return
	 */
	public String sendComment(String userId, String articleId, String content,String username) {
		HttpPost post = new HttpPost(getSendCommentUrl());
		post.setHeader("X-Forwarded-For", RandomUtils.generateIp());
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(ParamUtils.getDiscussParamList(articleId, content, userId,username),HTTP.UTF_8));
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String result =  EntityUtils.toString(entity);
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
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
