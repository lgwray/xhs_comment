package net.shinc.orm.mybatis.mappers.common;

import java.util.List;
import java.util.Map;

public interface JnlMapper {
	

    public int insertJnlArticleComment(Map map);
    
    public List<Map> selectCommentJnl(Map map);
    public int selectCommentJnlCount(Map map);

}