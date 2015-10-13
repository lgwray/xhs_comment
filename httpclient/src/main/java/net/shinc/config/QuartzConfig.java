package net.shinc.config;

import net.shinc.quartz.task.xhscomment.FetchArticleListJob;
import net.shinc.quartz.task.xhscomment.FetchArticleListJob.FetchArticleListThread;
import net.shinc.quartz.task.xhscomment.FetchCommentListJob;
import net.shinc.quartz.task.xhscomment.FetchCommentListJob.FetchCommentListThread;
import net.shinc.quartz.task.xhscomment.GenerateCommentReportJob;

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
		
		fb.setTriggers(fetchArticleListJobTrigger().getObject()
				,fetchCommentListJobTrigger().getObject()
				,generateCommentReportTrigger().getObject()
				);
		
		return fb;
	}
	
	/**  FetchArticleListJob begin */
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
	/**  FetchArticleListJob end */
	
	/** FetchCommentListJob begin */
	
	@Bean
	@Scope("prototype")
	public FetchCommentListThread fetchCommentListThread() {
		return new FetchCommentListThread();
	}
	
	@Bean
	public FetchCommentListJob fetchCommentListJob() {
		return new FetchCommentListJob();
	}
	@Bean
	public MethodInvokingJobDetailFactoryBean fetchCommentListJobDetail() {
		MethodInvokingJobDetailFactoryBean fb = new MethodInvokingJobDetailFactoryBean();
		fb.setTargetBeanName("fetchCommentListJob");
		fb.setTargetMethod("work");
		fb.setConcurrent(false);
		return fb;
	}
	
	@Bean
	public SimpleTriggerFactoryBean fetchCommentListJobTrigger() {
		SimpleTriggerFactoryBean fb = new SimpleTriggerFactoryBean();
		fb.setJobDetail(fetchCommentListJobDetail().getObject());
		fb.setRepeatInterval(1000 * env.getProperty("FetchCommentListJob.intevalSeconds", Integer.class));
		return fb;
	}
	/** FetchCommentListJob end */
	
	
	/** generateCommentReportJob begin */
	@Bean
	public GenerateCommentReportJob generateCommentReportJob() {
		return new GenerateCommentReportJob();
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean generateCommentReportJobDetail() {
		MethodInvokingJobDetailFactoryBean fb = new MethodInvokingJobDetailFactoryBean();
		fb.setTargetBeanName("generateCommentReportJob");
		fb.setTargetMethod("work");
		fb.setConcurrent(false);
		return fb;
	}
	
	@Bean
	public SimpleTriggerFactoryBean generateCommentReportTrigger() {
		SimpleTriggerFactoryBean fb = new SimpleTriggerFactoryBean();
		fb.setGroup("generateCommentReportJobGroup");
		fb.setName("generateCommentReportTrigger");
		fb.setJobDetail(generateCommentReportJobDetail().getObject());
		fb.setRepeatInterval(1000 * env.getProperty("GenerateCommentReportTask.intevalSeconds", Integer.class));
		return fb;
	}
	/** generateCommentReportJob end */
}
