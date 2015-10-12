package net.shinc.orm.mybatis.mappers.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.SinaWeiboArticleKey;

public interface SinaWeiboArticleMapper {
    int deleteByPrimaryKey(SinaWeiboArticleKey key);

    int insert(SinaWeiboArticleKey record);

    int insertSelective(SinaWeiboArticleKey record);
}