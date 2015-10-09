package net.shinc.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.mappers.common.JnlMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * @ClassName JnlServiceImpl 
 * @Description TODO
 * @author zhonglinzhao 
 * @date 2015年8月19日 下午5:03:17
 */
@Service
public class JnlServiceImpl {
	@Autowired
	private JnlMapper jnlMapper;
	
	/**
	 * 记录评论日志
	 * @param articleId
	 * @param content
	 * @param contentType
	 * @param userId
	 * @param commentWay
	 * @return
	 */
	public int insertJnlArticleComment(String articleId, String content,String contentType,String userId,String commentWay) {
		Map map = new HashMap();
		map.put("articleId", articleId);
		map.put("content", content);
		map.put("contentType", contentType);
		map.put("userId", userId);
		map.put("commentWay", commentWay);
		return jnlMapper.insertJnlArticleComment(map);
	}
	
	/**
	 * 查询评论日志
	 * @param userId
	 * @param addDate
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 */
	public List<Map> selectCommentJnl(Map map,PageBounds pb) {
		return jnlMapper.selectCommentJnl(map,pb);
	}
	
	/**
	 * 查询评论日志总数
	 * @param userId
	 * @param addDate
	 * @param pageIndex
	 * @param pageCount
	 * @return
	 */
	public int selectCommentJnlCount(Map map) {
		int count = jnlMapper.selectCommentJnlCount(map);
		return count;
	}
}
