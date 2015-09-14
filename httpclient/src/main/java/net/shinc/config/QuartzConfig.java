package net.shinc.config;

import net.shinc.quartz.task.xhscomment.FetchArticleDetailJob;
import net.shinc.quartz.task.xhscomment.FetchArticleListJob;
import net.shinc.quartz.task.xhscomment.FetchArticleListJob.FetchArticleListThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig  {
	
	@Autowired
	private Environment env;

	@Bean
	public SchedulerFactoryBean startQuartz() {
		SchedulerFactoryBean fb = new SchedulerFactoryBean();
		
		fb.setTriggers(fetchArticleDetailTrigger().getObject()
				,fetchArticleListJobTrigger().getObject()
				);
		
		return fb;
	}
	
	/** fetchArticleDetailJob begin */
	@Bean
	public FetchArticleDetailJob fetchArticleDetailJob() {
		return new FetchArticleDetailJob();
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean fetchArticleDetailJobDetail() {
		MethodInvokingJobDetailFactoryBean fb = new MethodInvokingJobDetailFactoryBean();
		fb.setTargetBeanName("fetchArticleDetailJob");
		fb.setTargetMethod("work");
		fb.setConcurrent(false);
		return fb;
	}
	
	@Bean
	public SimpleTriggerFactoryBean fetchArticleDetailTrigger() {
		SimpleTriggerFactoryBean fb = new SimpleTriggerFactoryBean();
		fb.setGroup("fetchArticleDetailGroup");
		fb.setName("fetchArticleDetailTrigger");
		fb.setJobDetail(fetchArticleDetailJobDetail().getObject());
		fb.setRepeatInterval(1000 * env.getProperty("ArticleDetailTask.intevalSeconds", Integer.class));
		return fb;
	}
	/** fetchArticleDetailJob begin */
	
	@Bean
	@Scope("prototype")
	public FetchArticleListThread fetchArticleListThread() {
		return new FetchArticleListThread();
	}
	
	@Bean
	public FetchArticleListJob fetchArticleListJob() {
		return new FetchArticleListJob();
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean fetchArticleListJobDetail() {
		MethodInvokingJobDetailFactoryBean fb = new MethodInvokingJobDetailFactoryBean();
		fb.setTargetBeanName("fetchArticleListJob");
		fb.setTargetMethod("work");
		fb.setConcurrent(false);
		return fb;
	}
	
	@Bean
	public SimpleTriggerFactoryBean fetchArticleListJobTrigger() {
		SimpleTriggerFactoryBean fb = new SimpleTriggerFactoryBean();
		fb.setGroup("fetchArticleListJobGroup");
		fb.setName("fetchArticleListJobTrigger");
		fb.setJobDetail(fetchArticleListJobDetail().getObject());
		fb.setRepeatInterval(1000 * env.getProperty("FetchArticleList.intevalSeconds", Integer.class));
		return fb;
	}
}
