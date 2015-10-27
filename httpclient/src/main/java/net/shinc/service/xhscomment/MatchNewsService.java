package net.shinc.service.xhscomment;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;
import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;

/** 
 * @ClassName MatchNewsService 
 * @Description 新闻精确匹配新闻列表
 * @author guoshijie 
 * @date 2015年9月17日 下午5:07:58  
 */
public interface MatchNewsService {

	public MatchNews getMatchNewsById(Integer id);
	
	/**
	 * 根据MatchNews的id列表批量获取匹配新闻列表
	 * @param list
	 * @return
	 */
	public List<MatchNews> getMatchNewsBatch(List<String> list);
	
	/**
	 * 根据MatchNews的id批量查询评论(不分页)
	 * @param list MatchNews的id
	 * @return
	 */
	public List<MatchComment> getMatchNewsCommentsBatch(List<String> list);
	
	/**
	 * 查询匹配新闻所拥有的评论数
	 * @param matchNewsId
	 * @return
	 */
	public Integer getMatchNewsCommentsCount(Integer matchNewsId);
	
	/**
	 * 根据MatchNews的id批量查询评论(分页)
	 * @param list MatchNews的id
	 * @return
	 */
	public List<MatchComment> getMatchNewsCommentsBatchWithPagination(List<String> list, PageBounds pb);
	
	public List<MatchComment> getMatchNewsCommentsBatchWithPagination2(String matchNewsId,PageBounds pb);
	
	
}
