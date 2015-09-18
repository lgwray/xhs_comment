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
	 * 根据MatchNews的id批量查询评论(不分页)
	 * @param list MatchNews的id
	 * @return
	 */
	public List<MatchComment> getMatchNewsCommentsBatch(List<Integer> list);
	
	/**
	 * 根据MatchNews的id批量查询评论(分页)
	 * @param list MatchNews的id
	 * @return
	 */
	public List<MatchComment> getMatchNewsCommentsBatchWithPagination(List<Integer> list, PageBounds pb);
}
