package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.common.BatchDao;
import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;

@BatchDao
public interface JnlCommentMapper {
	
	public int insert(JnlComment record);
	
	public int insertBatch(List<JnlComment> list);
	
	public void updateBatch(List<JnlComment> list);
	
	public List<JnlComment> queryBySendFlag(Map map);
	
	public int updateCommentSendFlagBatch(List<JnlComment> list);
	
}
