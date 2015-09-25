package net.shinc.orm.mybatis.mappers.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.AutoSendArticle;

public interface AutoSendArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AutoSendArticle record);

    int insertSelective(AutoSendArticle record);

    AutoSendArticle selectByPrimaryKey(Integer id);
    
    AutoSendArticle selectByArticleId(Integer articleId);

    int updateByPrimaryKeySelective(AutoSendArticle record);

    int updateByPrimaryKey(AutoSendArticle record);
}