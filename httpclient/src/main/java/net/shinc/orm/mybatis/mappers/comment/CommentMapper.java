package net.shinc.orm.mybatis.mappers.comment;

import java.util.Date;
import java.util.List;

import net.shinc.orm.mybatis.bean.common.Article;
import net.shinc.orm.mybatis.bean.common.News;

public interface CommentMapper {
	public List<News> selectNeuterComment(Integer count);
	
	public List<Article> selectArticleListByDate();
	
	public void insertArticleListBatch(List articleList);
}
