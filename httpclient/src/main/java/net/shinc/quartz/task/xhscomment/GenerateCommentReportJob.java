package net.shinc.quartz.task.xhscomment;

import net.shinc.service.xhscomment.CommentReportService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * @ClassName GenerateCommentReportJob 
 * @Description 每日评论数报表
 * @author guoshijie 
 * @date 2015年10月10日 上午11:52:36  
 */
public class GenerateCommentReportJob {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommentReportService crService;
	
	public void work() {
		logger.info("GenerateCommentReportJob--begin");
		try {
			crService.generateTodayReport();
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		logger.info("GenerateCommentReportJob--end");
	}
	
}
