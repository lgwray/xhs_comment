package net.shinc.xhscomment.task.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class CommentSender {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private int threadSize = 20;
	
	private ThreadPoolTaskExecutor executor;
	
	@Autowired
	private ApplicationContext context;
	public void start() {
		executor = (ThreadPoolTaskExecutor)context.getBean("threadPoolExecutor");
		for(int i=0; i<threadSize; i++) {
			CommentSenderThread t = (CommentSenderThread)context.getBean("senderThread");
			Thread thread = executor.createThread(t);
			thread.setName("sender-" + i);
			thread.setDaemon(true);
			thread.start();
		}
		
		CommentQueueProducerThread t = (CommentQueueProducerThread)context.getBean("commentQueueProducerThread");
		Thread cqpt = executor.createThread(t);
		cqpt.setName("producer-0");
		cqpt.setDaemon(true);
		cqpt.start();
	}

}
