package net.shinc.orm.mybatis.mappers.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;

public interface MatchCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatchComment record);

    int insertSelective(MatchComment record);

    MatchComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchComment record);

    int updateByPrimaryKeyWithBLOBs(MatchComment record);

    int updateByPrimaryKey(MatchComment record);
}