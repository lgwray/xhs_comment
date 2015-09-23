package net.shinc.orm.mybatis.mappers.common;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface JnlMapper {
	

    public int insertJnlArticleComment(Map map);
    
    public List<Map> selectCommentJnl(Map map,PageBounds pb);
    public int selectCommentJnlCount(Map map);

}