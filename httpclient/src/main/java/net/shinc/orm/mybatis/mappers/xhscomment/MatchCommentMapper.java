package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/** 
 * @ClassName MatchCommentMapper 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月18日 下午6:43:52  
 */
public interface MatchCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatchComment record);

    int insertSelective(MatchComment record);

    MatchComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchComment record);

    int updateByPrimaryKeyWithBLOBs(MatchComment record);

    int updateByPrimaryKey(MatchComment record);
    
    List<MatchComment> getMatchNewsCommentsBatch(List<String> list);
    
    int getMatchNewsCommentsCount(Integer matchNewsId);
    
    List<MatchComment> getMatchNewsCommentsBatch(List<String> list, PageBounds pb);
    
    List<MatchComment> getMatchNewsCommentsBatch2(Map map, PageBounds pb);
}