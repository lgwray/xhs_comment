package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.Nick;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface NickMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteBatch(List<Map> list);
    
    int updateFlagBatch(List<Map> list);

    int insert(Nick record);

    int insertSelective(Nick record);

    Nick selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Nick record);

    int updateByPrimaryKey(Nick record);
    
    public List<Nick> getNickListByPage(PageBounds pb);
    public List<Map> getNickListByPage2(Map map,PageBounds pb);
}