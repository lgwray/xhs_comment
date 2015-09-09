package net.shinc.service.impl;

import java.util.List;

import net.shinc.service.WeiboService;
import net.shinc.utils.Helper;
import net.shinc.utils.HttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/** 
 * @ClassName WeiboServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月9日 下午2:56:55  
 */
@Service
public class WeiboServiceImpl implements WeiboService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${php.news.weibo}")
	private String phpWeiboUrl;

	@Override
	public String getWeiboComments(String type, String mid, String page, String num) {
		String url = phpWeiboUrl + "?type="+type+"&mid="+mid+"&page="+page+"&num="+num;
		logger.info("weibo url:"+url);
		String res = HttpClient.get(url);
		return res;
	}

	@Override
	public List getWeiboCommentsList(String type, String mid, String page, String num) {
		String url = phpWeiboUrl + "?type="+type+"&mid="+mid+"&page="+page+"&num="+num;
		logger.info("weibo url:"+url);
		String res = HttpClient.get(url);
		List list = Helper.jsonToList(res);
		return list;
	}

}
