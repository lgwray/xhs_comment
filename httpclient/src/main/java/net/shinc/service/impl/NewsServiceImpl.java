package net.shinc.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.shinc.controller.xhscomment.NewsController;
import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.orm.mybatis.mappers.comment.CommentMapper;
import net.shinc.service.NewsService;
import net.shinc.utils.Helper;
import net.shinc.utils.HttpClient;
import net.shinc.utils.ParamUtils;
import net.shinc.utils.RandomUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName NewsServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月14日 上午1:05:48
 */
@Service
public class NewsServiceImpl implements NewsService {

	private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Value("${php.news.list}")
	private String phpUrl;

	public List getNewsList(String userId, String listUrl) {
		String res = HttpClient.post(listUrl, ParamUtils.getNewsListParamMap(userId));
		logger.info(res);
		Map map = Helper.jsonToMap(res);
		List list = (List) map.get("data_scroll");
		List list2 = (List) map.get("data");
		list.addAll(list2);
		return list;
	}
	
	/**
	 * 新闻列表
	 */
	public List getNewsList(String userId, String listUrl,String cid,String ctype) {
		String res = HttpClient.post(listUrl, ParamUtils.getNewsListParamMap(userId,cid,ctype));
		logger.info(res);
		Map map = Helper.jsonToMap(res);
		List list = (List) map.get("data_scroll");
		List list2 = (List) map.get("data");
		list.addAll(list2);
		return list;
	}
	/**
	 * 本地新闻评论数列表
	 */
	public List getLocalCommentsList() {
		List list = null;
		return list;
	}

	/**
	 * 发布评论
	 */
	@Override
	public String sendComment(String url, String userId, News news, String username) {
		String res = HttpClient.post(url, ParamUtils.getDiscussParamList(news, userId, username));
		logger.info("评论结果==>" + res);
		return res;
	}

	/**
	 * 发布评论
	 */
	@Override
	public String sendComment(String url, String userId, String articleId, String content, String username) {
		String res = HttpClient.post(url, ParamUtils.getDiscussParamList(articleId, content, userId, username));
		logger.info("评论结果==>" + res);
		return res;
	}
	
	@Override
	public int sendCommentBatchByPeople(Map map, String sendCommentUrl, String userId, int minNum, int limitNum, String phpUrl) {
		int discussNum = 0;
		String articleId = (String) map.get("id");
		String topic = (String) map.get("title"); // title
		String newsType = (String) map.get("newsType"); // 查询内容
		String newsCount = (String) map.get("newsCount"); // 查询笔数
		String condition = (String) map.get("condition"); // 分类or关键字
//		String comment = (String) map.get("comment"); // 评论数
		logger.info(articleId + "	" + topic);
		if (null != articleId && !"".equals(articleId)) {// 排除类似：推荐・体育
//			if (null != comment && !"".equals(comment)) {
					// 1.上送topic,发接口取评论 上送topic
					List list = new ArrayList();
					if("1".equals(condition)){
						list = getCommentsByNewsType(phpUrl,newsType,newsCount);
					}else if("2".equals(condition)){
						list = getCommentsByTitle(phpUrl, newsType,articleId,newsCount);
					}
					// 2.遍历评论
//						int discussNums = calculateNum(list.size(), limitNum);
						for (int j = 0; j < list.size(); j++) {
							Map m = (Map) list.get(j);
							String content = (String) m.get("comment");
							String nickname = (String)m.get("nick");
							String res = sendComment(sendCommentUrl, userId, articleId, delHtmlTag(content), nickname);
							logger.info("评论结果==>" + res);
							Map jsonToMap = Helper.jsonToMap(res);
							if ("success".equals(jsonToMap.get("state"))) {
								discussNum++;
							}
						}
//			}
		}
		logger.info("文章id:" + articleId + "   成功发布评论" + discussNum + "条");
		return discussNum;
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
//					List list = getCommentsByTitle(phpUrl,topic,articleId,null);
					
					List<News> list = commentMapper.selectNeuterComment(100);//从数据库随机取100条评论
//					 2.遍历评论
					if (null != list && list.size() > 0) {
						int discussNums = calculateNum(list.size(), limitNum, minNum, curNum, randomMin, randomMax);
						logger.info("will discuss num ==>" + discussNums);
						for (int j = 0; j < discussNums; j++) {
							News news = (News) list.get(j);
							String content = (String) news.getContent();
							String nickname = "";
							String res = sendComment(sendCommentUrl, userId, articleId, delHtmlTag(content),nickname);
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
		return content.replace("<br>", "").replace("网易", "**");
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
	 * 根据newsType抓取评论
	 * @param title
	 * @return
	 */
	public static List getCommentsByNewsType(String phpUrl, String newsType,String newsCount) {
		String url = phpUrl + "category.php?catname=" + newsType;
		if(newsCount != null && !"".equals(newsCount.trim())){
			url = url + "&num=" + newsCount;
		}
		String uri = Helper.dealUrl(url).toString();
		String comments = HttpClient.get(uri);
		logger.info("爬虫到的评论==>" + comments);
		if (null != comments && !"".equals(comments)) {
			List list = Helper.jsonToList(comments);
			logger.info("爬虫到评论条数==>" + list.size());
			return list;
		}
		return null;
	}
	/**
	 * 获取本地评论条数
	 * @param list
	 * @return
	 */
	public List getLocalArticleCommentsCounts(List list) {
		List resultList = null;
		resultList = commentMapper.getLocalArticleCommentsCounts(list);
		return resultList;
	}
	
	/**
	 * 根据title抓取评论
	 * @param title
	 * @return
	 */
	public static List getCommentsByTitle(String phpUrl, String title,String articleId,String newsCount) {
		String urlstr = phpUrl + "/match.php";
		
		URI url = URI.create(urlstr);
		
		URI u = null;
		try {
			u = new URIBuilder()
			    .setScheme(url.getScheme())
			    .setHost(url.getHost())
			    .setPort(url.getPort())
			    .setPath(url.getPath())
			    .setParameter("str", title)
			    .setParameter("num", String.valueOf(newsCount))
			    .build();
		} catch (URISyntaxException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		String comments = HttpClient.get(u.toString());
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
	
	public static void main(String[] args) {
		try {
			String title = "滨海爆炸|预计失联人数超21人";
			String title1 = "新华社无人机航拍爆炸现场";
			String title2 = "爆炸";
			String title3 = "直击|天津港爆炸事故核心现场";
			String title4 = "动新闻|3D还原天津滨海新区爆炸细节";
//			List list = getCommentsByTitle("http://spider.localhost/",title3,null,"5");
//			List list = getCommentsByTitle("http://182.92.189.177:8007",title3,null,null);
			List list = getCommentsByTitle("http://192.168.1.222",title3,null,null);
			
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

	//http://news.test.com/newslist.php?type=news&str=%E7%9B%97%E7%AA%83&page=1&num=10
	@Override
	public List getNewsListByTitle(String type, String str, String page, String num) {
		String url = phpUrl + "newslist.php?type="+type+"&str="+str+"&page="+page+"&num="+num;
		logger.info("phpNewsListUrl:"+url);
		String res = HttpClient.get(url);
		List list = Helper.jsonToList(res);
		return list;
	}
	
	//http://news.test.com/contentlist.php?type=news&newsid=34&page=1&num=5
	@Override
	public List getCommentsByNews(String type, String newsid, String page, String num) {
		String url = phpUrl + "contentlist.php?type="+type+"&newsid="+newsid+"&page="+page+"&num="+num;
		logger.info("phpNewsListUrl:"+url);
		String res = HttpClient.get(url);
		List list = Helper.jsonToList(res);
		return list;
	}
	
//	public static void main(String[] args) {
//		String str = "彻查原因！给国家和人民和一个交代！<br>希望信息透明，及时，公开。防止欺上瞒下！";
//		System.out.println(delHtmlTag(str));
//	}

}
