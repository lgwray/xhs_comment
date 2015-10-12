package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.SinaWeibo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SinaWeiboMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SinaWeibo record);

    int insertSelective(SinaWeibo record);

    SinaWeibo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaWeibo record);

    int updateByPrimaryKeyWithBLOBs(SinaWeibo record);

    int updateByPrimaryKey(SinaWeibo record);
    
    public List getSinaWbCommentByArticleId(String articleId,PageBounds pb);
}