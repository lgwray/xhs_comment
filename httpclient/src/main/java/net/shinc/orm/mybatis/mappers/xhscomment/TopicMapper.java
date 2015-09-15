package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.Topic;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface TopicMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer id);
    
    List<Topic> getTopicsWithPagination(PageBounds pb);
    
    List<Topic> getTopics();
    
    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKey(Topic record);
    
}