package net.shinc.service.xhscomment.impl;

import java.util.Map;

import net.shinc.service.impl.NewsServiceImpl;
import net.shinc.service.xhscomment.XhsArticleSpiderService;
import net.shinc.utils.Helper;
import net.shinc.utils.HttpClient;
import net.shinc.utils.ParamUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** 
 * @ClassName XhsArticleSpiderServiceImpl 
 * @Description 新华社新闻内容抓取接口实现
 * @author Steven Guo
 * @date 2016年1月11日 下午5:23:37  
 */
@Service
public class XhsArticleSpiderServiceImpl implements XhsArticleSpiderService {

	private static Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Value("${xhs.article.content.url}")
	private String articleContentUrl;
	
	@Override
	public Map getArticleContentById(String docId,String showPic,String userId) {
		String res = HttpClient.post(articleContentUrl, ParamUtils.getXhsArticleContentParamList(docId,showPic,userId));
		Map map = Helper.jsonToMap(res);
		Map newsMap = (Map)map.get("news");
//		String content = (String)newsMap.get("Content");
//		logger.info(content);
		return newsMap;
	}

}
