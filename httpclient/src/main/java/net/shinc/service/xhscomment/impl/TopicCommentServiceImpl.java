package net.shinc.service.xhscomment.impl;

import net.shinc.orm.mybatis.bean.xhscomment.TopicComment;
import net.shinc.service.xhscomment.TopicCommentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
/** 
 * @ClassName TopicCommentServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月15日 下午5:26:58  
 */
@Service
public class TopicCommentServiceImpl implements TopicCommentService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public Integer addCommentsWithTopic(TopicComment record) {
		return null;
	}

}
