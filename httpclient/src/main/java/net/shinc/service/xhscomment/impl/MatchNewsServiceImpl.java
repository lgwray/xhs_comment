package net.shinc.service.xhscomment.impl;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;
import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;
import net.shinc.orm.mybatis.mappers.xhscomment.MatchCommentMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.MatchNewsMapper;
import net.shinc.service.xhscomment.MatchNewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/** 
 * @ClassName MatchNewsServiceImpl 
 * @Description 新闻精确匹配新闻列表接口实现
 * @author guoshijie 
 * @date 2015年9月17日 下午5:14:40  
 */
@Service
public class MatchNewsServiceImpl implements MatchNewsService {

	@Autowired
	private MatchCommentMapper mcMapper;
	
	@Autowired
	private MatchNewsMapper mnMapper;
	
	@Override
	public MatchNews getMatchNewsById(Integer id) {
		if(null != id) {
			MatchNews news = mnMapper.selectByPrimaryKey(id);
			return news;
		}
		return null;
	}

	@Override
	public List<MatchComment> getMatchNewsCommentsBatch(List<Integer> list) {
		if(!CollectionUtils.isEmpty(list)) {
			List<MatchComment> comments = mcMapper.getMatchNewsCommentsBatch(list);
			return comments;
		}
		return null;
	}

	@Override
	public List<MatchComment> getMatchNewsCommentsBatchWithPagination(List<Integer> list, PageBounds pb) {
		if(!CollectionUtils.isEmpty(list)) {
			List<MatchComment> comments = mcMapper.getMatchNewsCommentsBatch(list);
			return comments;
		}
		return null;
	}

}
