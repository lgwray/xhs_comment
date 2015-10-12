package net.shinc.service.xhscomment;

import java.util.List;
import java.util.Map;


/** 
 * @ClassName CommentReportService 
 * @Description 每日评论数报表
 * @author guoshijie 
 * @date 2015年10月10日 上午10:47:45  
 */
public interface CommentReportService {

	/**
	 * 生成今天评论报表
	 */
	public void generateTodayReport();
	public void generateReportByDate(String today);
	
	public Integer deleteReportByDate(String date);
	
	/**
	 * 查询评论数报表
	 * @param date
	 * @return
	 */
	public List<Map> getReportByDate(String date);
	
	
}
