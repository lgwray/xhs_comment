package net.shinc.service.xhscomment.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;
import net.shinc.orm.mybatis.bean.xhscomment.JnlComment.SendFlag;
import net.shinc.orm.mybatis.mappers.xhscomment.JnlCommentMapper;
import net.shinc.service.xhscomment.JnlCommentService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.executor.BatchResult;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class JnlCommentServiceImpl implements JnlCommentService {

	private static Logger logger = LoggerFactory.getLogger(JnlCommentServiceImpl.class);
	@Autowired
	private JnlCommentMapper jcm;
	
	@Autowired
	@Qualifier("sqlSessionBatch")
	private SqlSessionTemplate sqlSessionBatch;
	
	private int batchSize = 100;
	
	@Override
	public Integer putComment(List<JnlComment> list) {
		int sum = 0;
			if(CollectionUtils.isEmpty(list)) {
				return 0;
			}
			int size = list.size();
			int times = size % batchSize == 0 ? size / batchSize : size / batchSize + 1;
			for(int i=0; i<times; i++) {
				int begin = batchSize * i;
				int end = begin + batchSize;
				end = Math.min(end, size);
				List<JnlComment> subList = list.subList(begin, end);
				for (JnlComment jnlComment : subList) {
					try {
						int num = jcm.insert(jnlComment);
						sum = sum + num;
					} catch (DuplicateKeyException e) {
						logger.info(ExceptionUtils.getStackTrace(e));
					}
				}
			}
		return sum;
	}
	
	public int getBatchSize() {
		return batchSize;
	}
	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}
	@Override
	public List<JnlComment> getCommentBySendFlag(SendFlag sendflag, Integer pageSize) {
		if(pageSize == null || pageSize <= 0) {
			return null;
		}
		Map map = new HashMap();
		map.put("SendFlag", sendflag.getValue());
		map.put("limit", pageSize);
		return jcm.queryBySendFlag(map);
	}
	
	public void updateCommentSendFlagSingle(JnlComment comment) {
			
		this.sqlSessionBatch.update("net.shinc.orm.mybatis.mappers.xhscomment.JnlCommentMapper.updateCommentSendFlagBatch",comment);
		
	}
	
	public void resetCommentSendFlag() {
		this.sqlSessionBatch.update("net.shinc.orm.mybatis.mappers.xhscomment.JnlCommentMapper.resetCommentSendFlag");
	}
	
	@Transactional
	public List<BatchResult> updateCommentSendFlag(List<JnlComment> paramList) {
		if(CollectionUtils.isEmpty(paramList)) {
			throw new IllegalArgumentException();
		}
		for(Iterator<JnlComment> it = paramList.iterator(); it.hasNext();) {
			JnlComment c = it.next();
			this.sqlSessionBatch.update("net.shinc.orm.mybatis.mappers.xhscomment.JnlCommentMapper.updateCommentSendFlagBatch",c);
		}
		return this.sqlSessionBatch.flushStatements();
	}

	
}
