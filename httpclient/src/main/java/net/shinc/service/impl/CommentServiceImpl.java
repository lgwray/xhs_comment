package net.shinc.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.shinc.utils.Helper;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	private String remoteApiUrl = "http://192.168.1.222";
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
	 */
	public List getCommentsByTitle(String title,int pageSize, int page ) {
		
		pageSize = pageSize < 1 ? 200 :pageSize;
		page = page < 1 ? 1 : page;
		String url = getRemoteApiUrl() + "/match?str=" + title + "&num=" + pageSize + "&page=" + page;
		url = Helper.dealUrl(url).toString();
		
		
		HttpGet get = new HttpGet(url);
		
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
	public List getCommentsByCategory(String title,int pageSize, int page ) {
		
		pageSize = pageSize < 1 ? 1000 :pageSize;
		page = page < 1 ? 1 : page;
		
		String url = getRemoteApiUrl() + "/category?catname=" + title + "&num=" + pageSize + "&page=" + page;
		url = Helper.dealUrl(url).toString();
		
		
		HttpGet get = new HttpGet(url);
		
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
	public String sendComment(String userId, String articleId, String content) {
		HttpPost post = new HttpPost(getSendCommentUrl());
		post.setHeader("X-Forwarded-For", "1.1.1.1");
		CloseableHttpResponse response = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(getDiscussParamMap(articleId, content, userId),HTTP.UTF_8));
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String result =  EntityUtils.toString(entity);
			return result;
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
		
	}
	
	public List<NameValuePair> getDiscussParamMap(String articleId, String content, String userId) {
		List<NameValuePair> list = new ArrayList();
		list.add(new BasicNameValuePair("id", articleId));
		list.add(new BasicNameValuePair("content", content));
		List<NameValuePair> commonList = getCommonParamMap(userId);
		list.addAll(commonList);
		return list;
	}
	
	public List<NameValuePair> getCommonParamMap(String userId) {
		List<NameValuePair> list = new ArrayList();
		list.add(new BasicNameValuePair("userID", userId));
		list.add(new BasicNameValuePair("udid", "0"));
		list.add(new BasicNameValuePair("clientApp", "104"));
		list.add(new BasicNameValuePair("clientBundleID", "net.xinhuamm.mainclient"));
		list.add(new BasicNameValuePair("clientType", "2"));
		list.add(new BasicNameValuePair("clientVer", "2.0.2"));
		list.add(new BasicNameValuePair("clientMarket", "337"));
		list.add(new BasicNameValuePair("clientOS", "4.4.4"));
		list.add(new BasicNameValuePair("clientModel", "HM NOTE 1LTE"));
		list.add(new BasicNameValuePair("clientNet", "wifi"));
		list.add(new BasicNameValuePair("clientToken", "1b1c92d10ac72c611ab9b5de96febb13"));
		list.add(new BasicNameValuePair("clientId", "1b1c92d10ac72c611ab9b5de96febb13"));
		list.add(new BasicNameValuePair("clientLable", "866401023331302"));
		list.add(new BasicNameValuePair("clientDev", "0"));
		list.add(new BasicNameValuePair("clientPrison", "0"));
		list.add(new BasicNameValuePair("clientWidth", "720"));
		list.add(new BasicNameValuePair("clientHeight", "1280"));
		list.add(new BasicNameValuePair("clientLongitude", "116.482487"));
		list.add(new BasicNameValuePair("clientLatitude", "39.997927"));
		list.add(new BasicNameValuePair("clientDate", Helper.getCurrentTimeMillis()));
		list.add(new BasicNameValuePair("province", "北京市"));
		list.add(new BasicNameValuePair("address", "北京市 朝阳区 阜通西大街 靠近保利院线电影(卜蜂莲花望京宝星店)"));
		return list;
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
