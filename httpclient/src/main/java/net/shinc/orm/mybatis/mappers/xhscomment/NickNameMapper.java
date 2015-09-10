package net.shinc.orm.mybatis.mappers.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.NickName;

public interface NickNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NickName record);

    int insertSelective(NickName record);

    NickName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NickName record);

    int updateByPrimaryKey(NickName record);
}