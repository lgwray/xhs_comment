package net.shinc.service.xhscomment;

import java.util.List;
import java.util.Map;


/** 
 * @ClassName CountService 
 * @Description 统计接口
 * @author guoshijie 
 * @date 2015年9月21日 上午11:33:10  
 */
public interface CountService {

	/**
	 * 获得新华社与本地的总评论数、及要闻评论数、及占比
	 * @param date
	 * @return
	 */
	public List<Map> getTotalPercent(String date);
	
	/**
	 * 获得历史每天的总评论数
	 * @return
	 */
	public List getLocalEverydayCommentsNums();
	
	/**
	 * 获取本地评论数(根据日期)
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public Map getLocalCommentsNumsByDate(String date);
	
	/**
	 * 获取抓取的文章数(根据日期)
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public Map getArticlesNumByDate(String date);
	
	/**
	 * 获得新华社的总评论数(根据日期)
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public Map getXhsCommentSum(String date);
	
	/**
	 * 获得新华社当天的评论数(根据栏目和日期)
	 * @param categoryid 0:要闻
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public Map getXhsCommentSumByCategory(String date, String categoryid);
}
