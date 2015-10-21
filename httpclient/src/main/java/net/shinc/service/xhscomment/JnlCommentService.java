package net.shinc.service.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;

import org.apache.ibatis.executor.BatchResult;


/**
 * 发送评论服务
 * @author zhangtaichao 2015年9月10日
 *
 */
public interface JnlCommentService {

	public Map putComment(List<JnlComment> list);
	
	public List<JnlComment> getCommentBySendFlag(JnlComment.SendFlag sendflag,Integer pageSize, String comment_way);
	
	public List<BatchResult> updateCommentSendFlag(List<JnlComment> paramList);
	
	public void resetCommentSendFlag();
}
