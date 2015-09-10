package net.shinc.xhscomment.sender;

import java.util.ArrayList;
import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;
import net.shinc.orm.mybatis.bean.xhscomment.JnlComment.SendFlag;
import net.shinc.service.xhscomment.JnlCommentService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class CommentQueueProducerThread implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommentQueue queue;
	
	@Autowired
	private JnlCommentService commentService;
	
	public void run() {
		logger.info("started");
		while(true) {
			try {
				int loadSize = queue.getFree();
				List<JnlComment> list = commentService.getCommentBySendFlag(SendFlag.nosend, loadSize);
				if(list == null || list.size() == 0) {
					Thread.sleep(10000);
					continue;
				} 
				//更新为发送中
				List<JnlComment> sendingList = new ArrayList<JnlComment>();
				for(JnlComment comment: list) {
					JnlComment tmp = new JnlComment();
					tmp.setId(comment.getId());
					tmp.setSendFlag(SendFlag.sending.getValue());
					sendingList.add(tmp);
				}
				commentService.updateCommentSendFlag(sendingList);
				
				for(JnlComment comment: list) {
					queue.offer(comment);
				}
			} catch(Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			
		}
	}
	
	

}
