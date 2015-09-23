package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.XhsNav;

public interface XhsNavMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XhsNav record);
    
    int insertBatch(List<XhsNav> list);

    int insertSelective(XhsNav record);

    XhsNav selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XhsNav record);

    int updateByPrimaryKey(XhsNav record);
}