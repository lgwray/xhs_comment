package net.shinc.service.xhscomment.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;
import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;
import net.shinc.orm.mybatis.bean.xhscomment.Nick;
import net.shinc.orm.mybatis.mappers.xhscomment.MatchCommentMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.MatchNewsMapper;
import net.shinc.service.xhscomment.MatchNewsService;
import net.shinc.service.xhscomment.NickService;

import org.apache.commons.lang3.StringUtils;
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
	
	@Autowired
	private NickService nickService;
	
	@Override
	public MatchNews getMatchNewsById(Integer id) {
		if(null != id) {
			MatchNews news = mnMapper.selectByPrimaryKey(id);
			return news;
		}
		return null;
	}

	@Override
	public List<MatchComment> getMatchNewsCommentsBatch(List<String> list) {
		if(!CollectionUtils.isEmpty(list)) {
			List<MatchComment> comments = mcMapper.getMatchNewsCommentsBatch(list);
			return comments;
		}
		return null;
	}

	@Override
	public List<MatchComment> getMatchNewsCommentsBatchWithPagination(List<String> list, PageBounds pb) {
		if(!CollectionUtils.isEmpty(list)) {
			List<MatchComment> comments = mcMapper.getMatchNewsCommentsBatch(list,pb);
			if(!CollectionUtils.isEmpty(comments)) {
				int num = comments.size();
				List<Nick> nicklist = nickService.getNicksRandom(num);
		        int nicksize = nicklist.size();
				for (int i = 0; i < num; i++) {
					MatchComment matchComment = comments.get(i);
					matchComment.setNick(nicklist.get(i).getNickname());
				}
			}
			return comments;
		}
		return null;
	}
	
	@Override
	public List<MatchComment> getMatchNewsCommentsBatchWithPagination2(String matchNewsId,PageBounds pb) {
		if(!StringUtils.isEmpty(matchNewsId)) {
			Map map = new HashMap();
			map.put("matchNewsId", matchNewsId);
			map.put("limit", pb.getLimit());
			List<MatchComment> comments = mcMapper.getMatchNewsCommentsBatch2(map,pb);
			return comments;
		}
		return null;
	}

	@Override
	public List<MatchNews> getMatchNewsBatch(List<String> list) {
		if(!CollectionUtils.isEmpty(list)){
			List<MatchNews> list2 = mnMapper.getMatchNewsBatch(list);
			return list2;
		}
		return null;
	}

	@Override
	public Integer getMatchNewsCommentsCount(Integer matchNewsId) {
		return mcMapper.getMatchNewsCommentsCount(matchNewsId);
	}

}
