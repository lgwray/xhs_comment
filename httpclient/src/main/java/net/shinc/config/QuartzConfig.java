package net.shinc.config;

import net.shinc.quartz.task.xhscomment.GenerateCommentReportJob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
		fb.setTriggers(
				generateCommentReportTrigger().getObject()
				);
		
		return fb;
	}
	
	
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
