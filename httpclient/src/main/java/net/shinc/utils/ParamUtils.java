package net.shinc.utils;

import java.util.ArrayList;
import java.util.List;

import net.shinc.orm.mybatis.bean.common.News;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * @ClassName ParamUtils 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月14日 下午4:12:29
 */
public class ParamUtils {

	/**
	 * 准备获取新闻列表_请求参数map
	 * @param currentTime
	 * @return
	 */
	public static List<NameValuePair> getNewsListParamMap(String userId) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("cid", "470"));
		list.add(new BasicNameValuePair("pn", "1"));
		list.add(new BasicNameValuePair("ctype", "4001"));
		list.add(new BasicNameValuePair("selids", "461,462,463,464,502"));
		List<NameValuePair> commonParamList = getCommonParamList(userId);
		list.addAll(commonParamList);
		return list;
	}
	
	/**
	 * 准备获取新闻列表_请求参数map
	 * @param currentTime
	 * @return
	 */
	public static List<NameValuePair> getNewsListParamMap(String userId,String cid,String ctype) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("cid", cid));
		list.add(new BasicNameValuePair("pn", "1"));
		list.add(new BasicNameValuePair("ctype", ctype));
		list.add(new BasicNameValuePair("selids", ""));
		List<NameValuePair> commonParamList = getCommonParamList(userId);
		list.addAll(commonParamList);
		return list;
	}
	
	/**
	 * 准备发布评论_请求参数map
	 * @param news 文章对象
	 * @param userId 用户id
	 * @return
	 */
	public static List<NameValuePair> getDiscussParamList(News news, String userId, String username) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("id", news.getId()));
		list.add(new BasicNameValuePair("channel", "shihe"));
		list.add(new BasicNameValuePair("ip", "182.92.189.173"));
		list.add(new BasicNameValuePair("username", username));
		list.add(new BasicNameValuePair("content", news.getContent()));
		List<NameValuePair> commonParamList = getCommonParamList(userId);
		list.addAll(commonParamList);
		return list;
	}
	
	/**
	 * 准备发布评论_请求参数map
	 * @param articleId 文章id
	 * @param content 评论内容
	 * @param userId 用户id
	 * @param username 用户昵称
	 * @return
	 */
	public static List<NameValuePair> getDiscussParamList(String articleId, String content, String userId, String username) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("id",articleId));
		list.add(new BasicNameValuePair("channel", "shihe"));
		list.add(new BasicNameValuePair("ip", "182.92.189.173"));
		list.add(new BasicNameValuePair("username", username));
		list.add(new BasicNameValuePair("content", content));
		List<NameValuePair> commonParamList = getCommonParamList(userId);
		list.addAll(commonParamList);
		return list;
	}
	
	/**
	 * 获取新闻内容_请求参数
	 * @param docid
	 * @param showpic
	 * @param userId
	 * @return
	 */
	public static List<NameValuePair> getXhsArticleContentParamList(String docid, String showpic, String userId) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("docid",docid));
		list.add(new BasicNameValuePair("showpic",showpic));
		list.add(new BasicNameValuePair("channel", "shihe"));
		list.add(new BasicNameValuePair("ip", "182.92.189.173"));
		List<NameValuePair> commonParamList = getCommonParamList(userId);
		list.addAll(commonParamList);
		return list;
	}
	
	/**
	 * 新华社通用请求参数
	 * @param userId
	 * @return
	 */
	public static List<NameValuePair> getCommonParamList(String userId) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("userID", userId));
		list.add(new BasicNameValuePair("udid", "0"));
		list.add(new BasicNameValuePair("clientApp", "104"));
		list.add(new BasicNameValuePair("clientBundleID", "com.xinhuamm.d0001"));
		list.add(new BasicNameValuePair("clientType", "1"));
		list.add(new BasicNameValuePair("clientVer", "3.1.0"));
		list.add(new BasicNameValuePair("clientMarket", "337"));
		list.add(new BasicNameValuePair("clientOS", "10.2.1"));
		list.add(new BasicNameValuePair("clientModel", "iPhone6s"));
		list.add(new BasicNameValuePair("clientNet", "wifi"));
		list.add(new BasicNameValuePair("clientToken", ""));
		list.add(new BasicNameValuePair("clientId", "14fb4cd6b9e165926399a25f40418c27"));
		list.add(new BasicNameValuePair("clientLable", "E7C40419-636A-43A5-B01E-D0C9B19883BC"));
		list.add(new BasicNameValuePair("clientDev", "1"));
		list.add(new BasicNameValuePair("clientPrison", "0"));
		list.add(new BasicNameValuePair("clientWidth", "750"));
		list.add(new BasicNameValuePair("clientHeight", "1334"));
		list.add(new BasicNameValuePair("clientLongitude", "0.000000"));
		list.add(new BasicNameValuePair("clientLatitude", "0.000000"));
		list.add(new BasicNameValuePair("clientDate", Helper.getCurrentTimeMillis()));
		list.add(new BasicNameValuePair("province", "北京市"));
		list.add(new BasicNameValuePair("address", "北京市 朝阳区 阜通西大街 靠近保利院线电影(卜蜂莲花望京宝星店)"));
		list.add(new BasicNameValuePair("showpic", "1"));
		return list;
	}
}
