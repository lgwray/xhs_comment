package net.shinc.service.xhscomment;

import java.util.List;

import org.apache.ibatis.executor.BatchResult;

import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;


/**
 * 发送评论服务
 * @author zhangtaichao 2015年9月10日
 *
 */
public interface JnlCommentService {

	public Integer putComment(List<JnlComment> list);
	
	public List<JnlComment> getCommentBySendFlag(JnlComment.SendFlag sendflag,Integer pageSize);
	
	public List<BatchResult> updateCommentSendFlag(List<JnlComment> paramList);
	
	public void resetCommentSendFlag();
}
