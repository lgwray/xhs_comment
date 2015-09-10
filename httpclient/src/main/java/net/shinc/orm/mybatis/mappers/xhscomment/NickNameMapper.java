package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.NickName;

public interface NickNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NickName record);
    
    int insertBatch(List<Map> list);

    int insertSelective(NickName record);

    NickName selectByPrimaryKey(Integer id);
    
    NickName selectByNickName(NickName record);

    int updateByPrimaryKeySelective(NickName record);

    int updateByPrimaryKey(NickName record);
}