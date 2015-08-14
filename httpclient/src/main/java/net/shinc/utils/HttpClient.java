package net.shinc.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import net.shinc.orm.mybatis.bean.common.News;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName HttpClient 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月14日 下午3:05:33
 */
public class HttpClient {
	
	private static Logger logger = Logger.getLogger(HttpXmlClient.class);
	private static String baseUrl = "http://192.168.1.222/match";
	private static String newsListUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/indexlist";
	private static String sendCommentUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment";
	private static String charset = "UTF-8";

//	@Autowired
//	private static CloseableHttpClient httpClient;
	
	/**
	 * 发送post请求
	 * @param postUrl
	 * @param params
	 * @return
	 */
	public static String post(String postUrl, List<NameValuePair> params) {
		HttpPost post = new HttpPost();
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			post.setURI(URI.create(postUrl));
			
			HttpEntity entity = new UrlEncodedFormEntity(params, charset);
			post.setEntity(entity);
			post.setHeader("X-Forwarded-For", RandomUtils.generateIp());
			
			HttpResponse res = httpClient.execute(post);
			Header[] headers = res.getAllHeaders();
			for(int i = 0; i < headers.length; i++) {
				logger.info("responseHeaders["+i+"]: "+headers[i]);
			}
			
			String result = EntityUtils.toString(res.getEntity());
			logger.info("responseBody: " + UnicodeUtils.decodeUnicode(result));
			return result;
		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));
		} finally {
			post.releaseConnection();
		}
		return null;
	}
	
	public static String get(String url) {
		HttpGet get = new HttpGet();
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			get.setURI(URI.create(url));
			get.setHeader("X-Forwarded-For", RandomUtils.generateIp());
			
			HttpResponse res = httpClient.execute(get);
			Header[] headers = res.getAllHeaders();
			for(int i = 0; i < headers.length; i++) {
				logger.info("responseHeaders["+i+"]: "+headers[i]);
			}
			
			String result = EntityUtils.toString(res.getEntity());
			logger.info("responseBody: " + UnicodeUtils.decodeUnicode(result));
			return result;
		} catch (Exception e) {
			logger.info(ExceptionUtils.getStackTrace(e));
		} finally {
			get.releaseConnection();
		}
		return null;
	}
	
	
	/**
	 * 准备请求参数
	 * @return
	 */
	public static List<NameValuePair> getAddParams() {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("username", "admin"));
		list.add(new BasicNameValuePair("password", "admin"));
		return list;
	}
	
	public static void main(String[] args) {
		//拉取新闻列表
//		post(newsListUrl, ParamUtils.getNewsListParamMap("0"));
		
		//测试发布评论
		News news = new News();
		news.setId("215465");
		news.setContent("好惨烈");
		post(sendCommentUrl, ParamUtils.getDiscussParamList(news, "0"));
		
		//测试get请求
//		String url = "http://192.168.1.222/match?str=天津爆炸&num=5";
//		get(url);
	}
}
