package net.shinc.service.xhscomment.impl;

import java.util.Date;
import java.util.List;

import net.shinc.common.ShincUtil;
import net.shinc.orm.mybatis.bean.xhscomment.Topic;
import net.shinc.orm.mybatis.mappers.xhscomment.TopicMapper;
import net.shinc.service.xhscomment.TopicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/** 
 * @ClassName TopicServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月14日 下午8:52:18  
 */
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicMapper topicMapper;
	public static String pattern = "yyyy-MM-dd HH:mm:ss";
	
	@Override
	public Integer addTopic(Topic topic) {
		if(null != topic) {
			topic.setCreateTime(ShincUtil.formatDate(new Date(), pattern));
			Integer num = topicMapper.insertSelective(topic);
			return num;
		}
		return 0;
	}

	@Override
	public Integer deleteTopic(Topic topic) {
		if(null != topic) {
			Integer num = topicMapper.deleteByPrimaryKey(topic.getId());
			return num;
		}
		return 0;
	}

	@Override
	public Integer updateTopic(Topic topic) {
		if(null != topic) {
			Integer num = topicMapper.updateByPrimaryKeySelective(topic);
			return num;
		}
		return 0;
	}

	@Override
	public PageList<Topic> getTopicsWithPagination(PageBounds pageBounds) {
		List<Topic> list = topicMapper.getTopicsWithPagination(pageBounds);
		if(!CollectionUtils.isEmpty(list)) {
			PageList<Topic> pagelist = (PageList<Topic>)list;
			return pagelist;
		}
		return null;
	}

	@Override
	public List<Topic> getTopics() {
		List<Topic> list = topicMapper.getTopics();
		if(!CollectionUtils.isEmpty(list)) {
			return list;
		}
		return null;
	}

}
