package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.Map;

/** 
 * @ClassName ArticleMapper 
 * @Description 新闻
 * @author guoshijie 
 * @date 2015年9月18日 上午11:36:47  
 */
public interface ArticleMapper {

	public Map selectArticleByPrimaryKey(Integer id);
	
	public Integer insertLocalArticle(Map map);
}
