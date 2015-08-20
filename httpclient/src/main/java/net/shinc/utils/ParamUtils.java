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
		list.add(new BasicNameValuePair("selids", "461,462,463,464,502"));
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
	 * 新华社能用请求参数
	 * @param userId
	 * @return
	 */
	public static List<NameValuePair> getCommonParamList(String userId) {
		List<NameValuePair> list = new ArrayList<NameValuePair>();
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
}
