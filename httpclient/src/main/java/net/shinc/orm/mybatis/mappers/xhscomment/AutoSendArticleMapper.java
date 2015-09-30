package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.AutoSendArticle;

public interface AutoSendArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AutoSendArticle record);

    int insertSelective(AutoSendArticle record);

    AutoSendArticle selectByPrimaryKey(Integer id);
    
    AutoSendArticle selectByArticleId(Integer articleId);
    
    AutoSendArticle selectByArticleIdAndMatchNewsId(Map map);

    int updateByPrimaryKeySelective(AutoSendArticle record);

    int updateByPrimaryKey(AutoSendArticle record);
    
    List<Map> getAutoSendList(Map map);
    
}