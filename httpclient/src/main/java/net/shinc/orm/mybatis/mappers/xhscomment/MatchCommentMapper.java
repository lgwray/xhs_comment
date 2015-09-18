package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;

public interface MatchCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatchComment record);

    int insertSelective(MatchComment record);

    MatchComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchComment record);

    int updateByPrimaryKeyWithBLOBs(MatchComment record);

    int updateByPrimaryKey(MatchComment record);
    
    List<MatchComment> getMatchNewsCommentsBatch(List<Integer> list);
    
    List<MatchComment> getMatchNewsCommentsBatch(List<Integer> list, PageBounds pb);
}