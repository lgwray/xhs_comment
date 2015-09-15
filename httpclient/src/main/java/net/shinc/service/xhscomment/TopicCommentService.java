package net.shinc.service.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.TopicComment;

/**
 * @ClassName TopicCommentService 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月15日 下午3:15:06
 */
public interface TopicCommentService {

	public Integer addCommentsWithTopic(TopicComment record);
}
