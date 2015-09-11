package net.shinc.config;

import net.shinc.xhscomment.task.fetcher.ArticleDetailTask;
import net.shinc.xhscomment.task.sender.CommentQueue;
import net.shinc.xhscomment.task.sender.CommentQueueProducerThread;
import net.shinc.xhscomment.task.sender.CommentSender;
import net.shinc.xhscomment.task.sender.CommentSenderThread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CommentSenderConfig {
    
	@Bean
	public CommentQueue commentQueue() throws Exception {
		CommentQueue queue = new CommentQueue();
		return queue;
	}
	
	@Bean
	@Scope("prototype")
	public CommentSenderThread senderThread() {
		CommentSenderThread st = new CommentSenderThread();
		return st;
	}
	
	@Bean(initMethod="start")
	public CommentSender commentSender() {
		CommentSender cs = new CommentSender();
		return cs;
	}
	
	@Bean
	public CommentQueueProducerThread commentQueueProducerThread() {
		CommentQueueProducerThread cqp = new CommentQueueProducerThread();
		return cqp;
	}
	
	@Bean(initMethod="start")
	public ArticleDetailTask articleDetailTask() {
		ArticleDetailTask adt = new ArticleDetailTask();
		return adt;
	}
}
