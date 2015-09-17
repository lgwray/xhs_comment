package net.shinc.orm.mybatis.mappers.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;

public interface MatchNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatchNews record);

    int insertSelective(MatchNews record);

    MatchNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchNews record);

    int updateByPrimaryKey(MatchNews record);
}