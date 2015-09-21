package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.ArticleHasMatchNewsKey;

public interface ArticleHasMatchNewsMapper {
    int deleteByPrimaryKey(ArticleHasMatchNewsKey key);

    int insert(ArticleHasMatchNewsKey record);

    int insertSelective(ArticleHasMatchNewsKey record);
    
    List<String> selectMatchNewsIdByArticleId(Integer articleId);
}