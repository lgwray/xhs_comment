package net.shinc.orm.mybatis.mappers.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.Nick;

public interface NickMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Nick record);

    int insertSelective(Nick record);

    Nick selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nick record);

    int updateByPrimaryKey(Nick record);
}