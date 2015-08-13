package net.shinc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.controller.common.HttpController;
import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.service.NewsService;
import net.shinc.utils.Helper;
import net.shinc.utils.HttpXmlClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

	private static Logger logger = LoggerFactory.getLogger(HttpController.class);

	public List getNewsList(String userId, String listUrl) {
		String res = HttpXmlClient.post(listUrl, getNewsListParamMap(userId, Helper.getCurrentTimeMillis()));
		Map map = Helper.jsonToMap(res);
		List list = (List) map.get("data_scroll");
		List list2 = (List) map.get("data");
		list.addAll(list2);
		return list;
	}

	/**
	 * 准备获取新闻列表_请求参数map
	 * @param currentTime
	 * @return
	 */
	public static Map<String, String> getNewsListParamMap(String userId, String currentTime) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cid", "470");
		map.put("pn", "1");
		map.put("ctype", "4001");
		map.put("selids", "461,462,463,464,502");
		Map<String, String> commonParamMap = getCommonParamMap(userId, currentTime);
		map.putAll(commonParamMap);
		return map;
	}

	/**
	 * 准备发布评论_请求参数map
	 * @param id 文章id
	 * @param content 评论内容
	 * @param userId 用户id
	 * @param currentTime
	 * @return
	 */
	public static Map<String, String> getDiscussParamMap(News news, String userId, String currentTime) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", news.getId());
		map.put("content", news.getContent());
		Map<String, String> commonParamMap = getCommonParamMap(userId, currentTime);
		map.putAll(commonParamMap);
		return map;
	}

	/**
	 * 通用请求参数
	 * @param userId 用户id
	 * @param currentTime
	 * @return
	 */
	public static Map<String, String> getCommonParamMap(String userId, String currentTime) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userID", userId);
		map.put("udid", "0");
		map.put("clientApp", "104");
		map.put("clientBundleID", "net.xinhuamm.mainclient");
		map.put("clientType", "2");
		map.put("clientVer", "2.0.2");
		map.put("clientMarket", "337");
		map.put("clientOS", "4.4.4");
		map.put("clientModel", "HM NOTE 1LTE");
		map.put("clientNet", "wifi");
		map.put("clientToken", "1b1c92d10ac72c611ab9b5de96febb13");
		map.put("clientId", "1b1c92d10ac72c611ab9b5de96febb13");
		map.put("clientLable", "866401023331302");
		map.put("clientDev", "0");
		map.put("clientPrison", "0");
		map.put("clientWidth", "720");
		map.put("clientHeight", "1280");
		map.put("clientLongitude", "116.482487");
		map.put("clientLatitude", "39.997927");
		map.put("clientDate", currentTime);
		map.put("province", "北京市");
		map.put("address", "北京市 朝阳区 阜通西大街 靠近保利院线电影(卜蜂莲花望京宝星店)");
		return map;
	}

	@Override
	public String sendComment(String url, String userId, News news) {
		String res = HttpXmlClient.post(url, getDiscussParamMap(news, userId, Helper.getCurrentTimeMillis()));
		return res;
	}

}
