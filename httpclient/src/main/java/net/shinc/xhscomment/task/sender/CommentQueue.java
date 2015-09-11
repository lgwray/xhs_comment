package net.shinc.xhscomment.task.sender;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.InitializingBean;

import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;

public class CommentQueue {
	
	
	private int queueMax = 1000;
	
	private ConcurrentLinkedQueue<JnlComment> commentQueue;

	public CommentQueue() throws Exception {
		commentQueue = new ConcurrentLinkedQueue<JnlComment>();
		
	}

	public void offer(JnlComment comment) {
		commentQueue.offer(comment);
	}
	
	public JnlComment poll() {
		return commentQueue.poll();
	}
	
	public int getFree() {
		return queueMax - commentQueue.size();
	}

	public int getQueueMax() {
		return queueMax;
	}



	public void setQueueMax(int queueMax) {
		this.queueMax = queueMax;
	}



	public ConcurrentLinkedQueue<JnlComment> getCommentQueue() {
		return commentQueue;
	}

	public void setCommentQueue(ConcurrentLinkedQueue<JnlComment> commentQueue) {
		this.commentQueue = commentQueue;
	}

	
}
