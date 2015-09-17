package net.shinc.service.xhscomment;

import java.util.List;
import java.util.Map;

public interface CountService {

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
	
	public Map getTotalPercent(String date);
	
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
	 * 获得新华社当天的评论数(根据栏目)
	 * @param categoryid 0:要闻
	 * @return
	 */
	public Map getXhsCommentSumByCategory(String date, String categoryid);
}
