package net.shinc.service.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.Topic;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @ClassName TopicService 
 * @Description 话题
 * @author guoshijie 
 * @date 2015年9月14日 下午8:40:14  
 */
public interface TopicService {

	public Integer addTopic(Topic topic);
	
	public Integer deleteTopic(Topic topic);

	public Integer updateTopic(Topic topic);
	
	public List<Topic> getTopics();

	public PageList<Topic> getTopicsWithPagination(PageBounds pageBounds);
	
	
}
