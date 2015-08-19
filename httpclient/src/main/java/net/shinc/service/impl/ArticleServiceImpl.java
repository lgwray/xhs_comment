package net.shinc.service.impl;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.mappers.comment.CommentMapper;

import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @ClassName ArticleServiceImpl 
 * @Description TODO
 * @author zhonglinzhao 
 * @date 2015年8月18日 下午3:14:29
 */

@Service
public class ArticleServiceImpl {
	@Autowired
	private CloseableHttpClient httpClient;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommentMapper commentMapper;
	
	public void refreshArticleList(List<Map> list){
		if(list != null){
			String id = "";
			for(int i=0;i<list.size();i++){
				Map map = list.get(i);
				id = (String)map.get("id");
				if(id != null && !"".equals(id)){
					try{
						commentMapper.insertArticle(list.get(i));
					}catch(Exception e){
						logger.error("SQLException => "+e);
					}
				}
			}
		}
	}
}
