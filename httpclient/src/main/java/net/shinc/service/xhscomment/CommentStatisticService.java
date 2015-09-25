package net.shinc.service.xhscomment;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName CommentStatisticService 
 * @Description 评论统计服务接口
 * @author guoshijie 
 * @date 2015年9月25日 下午6:56:38  
 */
public interface CommentStatisticService {

	/**
	 * 获得新华社与本地的总评论数、及要闻评论数、及占比
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public List<Map<String,Object>> getCommentStatisticByDate(List<String> date);
	
}
