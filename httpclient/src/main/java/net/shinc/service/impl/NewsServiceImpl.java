package net.shinc.service.impl;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.controller.common.NewsController;
import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.service.NewsService;
import net.shinc.utils.Helper;
import net.shinc.utils.HttpXmlClient;
import net.shinc.utils.RandomUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName NewsServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月14日 上午1:05:48
 */
@Service
public class NewsServiceImpl implements NewsService {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);

	public List getNewsList(String userId, String listUrl) {
		String res = HttpXmlClient.post(listUrl, getNewsListParamMap(userId));
		logger.info(res);
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
	public static Map<String, String> getNewsListParamMap(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("cid", "470");
		map.put("pn", "1");
		map.put("ctype", "4001");
		map.put("selids", "461,462,463,464,502");
		Map<String, String> commonParamMap = getCommonParamMap(userId);
		map.putAll(commonParamMap);
		return map;
	}

	/**
	 * 准备发布评论_请求参数map
	 * @param news 文章对象
	 * @param userId 用户id
	 * @return
	 */
	public static Map<String, String> getDiscussParamMap(News news, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", news.getId());
		map.put("content", news.getContent());
		Map<String, String> commonParamMap = getCommonParamMap(userId);
		map.putAll(commonParamMap);
		return map;
	}

	public static Map<String, String> getDiscussParamMap(String articleId, String content, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", articleId);
		map.put("content", content);
		Map<String, String> commonParamMap = getCommonParamMap(userId);
		map.putAll(commonParamMap);
		return map;
	}

	/**
	 * 通用基础请求参数
	 * @param userId 用户id
	 * @return
	 */
	public static Map<String, String> getCommonParamMap(String userId) {
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
		map.put("clientDate", Helper.getCurrentTimeMillis());
		map.put("province", "北京市");
		map.put("address", "北京市 朝阳区 阜通西大街 靠近保利院线电影(卜蜂莲花望京宝星店)");
		return map;
	}

	/**
	 * 发布评论
	 */
	@Override
	public String sendComment(String url, String userId, News news) {
		String res = HttpXmlClient.post(url, getDiscussParamMap(news, userId));
		return res;
	}

	/**
	 * 发布评论
	 */
	@Override
	public String sendComment(String url, String userId, String articleId, String content) {
		String res = HttpXmlClient.post(url, getDiscussParamMap(articleId, content, userId));
		logger.info("评论结果==>" + res);
		return res;
	}

	/**
	 * 给某一文章批量发布评论
	 * @param map 某文章
	 * @param sendCommentUrl 发布评论链接
	 * @param userId 发布评论的用户id
	 * @param minNum 目标评论数,例如:1000条
	 * @param limitNum 每篇文章限制批量评论条数
	 * @param phpUrl 请求PHP接口地址
	 * @param randomMin 随机数最小值
	 * @param randomMax 随机数最大值
	 */
	@Override
	public void sendCommentBatch(Map map, String sendCommentUrl, String userId, int minNum, int limitNum, String phpUrl, Integer randomMin, Integer randomMax) {
		int discussNum = 0; //成功评论条数
		String articleId = (String) map.get("id");
		String topic = (String) map.get("topic"); // title
		String comment = (String) map.get("comment"); // 评论数
		logger.info(articleId + "	" + topic);
		if (null != articleId && !"".equals(articleId)) {// 排除类似：推荐・体育
			if (null != comment && !"".equals(comment)) {
				int curNum = Integer.parseInt(comment);// 评论数
				if (curNum < minNum) {
					// 1.上送topic,发接口取评论 上送topic
					List list = getCommentsByTitle(phpUrl,topic,articleId);
					// 2.遍历评论
					if (null != list && list.size() > 0) {
						int discussNums = calculateNum(list.size(), limitNum, minNum, curNum, randomMin, randomMax);
						logger.info("will discuss num ==>" + discussNums);
						for (int j = 0; j < discussNums; j++) {
							Map m = (Map) list.get(j);
							String content = (String) m.get("comment");
							String res = sendComment(sendCommentUrl, userId, articleId, delHtmlTag(content));
							Map jsonToMap = Helper.jsonToMap(res);
							if ("success".equals(jsonToMap.get("state"))) {
								discussNum++;
							} else {
								continue;
							}
						}
					}
				}// end minNum
			}
		}
		logger.info("文章id:" + articleId + "   成功发布评论" + discussNum + "条");
	}

	/**
	 * 删除评论内容中的html标签（待完善）
	 * @param content
	 * @return
	 */
	public static String delHtmlTag(String content){
		return content.replace("<br>", "");
	}
	
	/**
	 * 计算需要发布的评论条数
	 * @param nowHas 抓回来的评论条数
	 * @param limitNum 每篇文章限制批量评论条数,例如5条
	 * @param minNum 目标评论数,例如:1000条
	 * @param curNum 当前文章评论条数
	 * @param randomMin 随机数最小值
	 * @param randomMax 随机数最大值
	 * @return
	 */
	private int calculateNum(int nowHas, int limitNum, int minNum, int curNum, int randomMin, int randomMax) {
		//为了避免所有文章评论条数都一样,所以在预期评论条数上加一个随机数
		int randomNum = RandomUtils.getRandom(randomMin, randomMax);//随机数
		
		if (limitNum > 0) {
			if (nowHas < limitNum) {
				return nowHas;
			} else if (nowHas > limitNum) {
				return limitNum;
			} else {
				return nowHas;
			}
		}
		
		if (limitNum < 0) {
			int need = minNum + randomNum - curNum;
			if(nowHas > need) {
				return need;
			} else if(nowHas < need){
				return nowHas;
			} else {
				return need;
			}
		}
		
		return 0;
	}

	/**
	 * 根据title抓取评论
	 * @param title
	 * @return
	 */
	public static List getCommentsByTitle(String phpUrl, String title,String articleId) {
		String url = phpUrl + "match?str=" + title;
		String uri = dealUrl(url).toString();
		String comments = HttpXmlClient.get(uri);
		logger.info("爬虫到的评论==>" + comments);
		if (null != comments && !"".equals(comments)) {
			List list = Helper.jsonToList(comments);
			if (null != list && list.size() > 0) {
				logger.info("文章id:"+articleId+"  爬虫到评论条数==>" + list.size());
			} else {
				logger.info("文章id:"+articleId+"  未抓取到评论");
			}
			return list;
		}
		return null;
	}

	/**
	 * 处理URL,以防url中出现‘｜’‘&’等特殊字符
	 * @param urlPre
	 * @return
	 */
	public static URI dealUrl(String urlPre) {
		URI uri = null;
		try {
			URL url = new URL(urlPre);
			uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return uri;
	}

	public static void main(String[] args) {
		try {
			String title = "滨海爆炸|预计失联人数超21人";
			String title1 = "新华社无人机航拍爆炸现场";
			String title2 = "爆炸";
			String title3 = "直击|天津港爆炸事故核心现场";
//			List list = getCommentsByTitle("http://spider.localhost/",title3,null);
			List list = getCommentsByTitle("http://192.168.1.222/",title3,null);
			
			if (null != list && list.size() > 0) {
				int num = list.size() >= 5 ? 5 : list.size();
				for (int j = 0; j < 5; j++) {
					Map m = (Map) list.get(j);
					logger.info((String)m.get("comment"));
				}
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}
	
//	public static void main(String[] args) {
//		String str = "彻查原因！给国家和人民和一个交代！<br>希望信息透明，及时，公开。防止欺上瞒下！";
//		System.out.println(delHtmlTag(str));
//	}

}
