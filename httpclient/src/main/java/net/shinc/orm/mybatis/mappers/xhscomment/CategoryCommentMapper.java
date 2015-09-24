package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import net.shinc.orm.mybatis.bean.xhscomment.CategoryComment;
import net.shinc.orm.mybatis.bean.xhscomment.CommentCategory;

/**
 * 基础评论
 * @author zhangtaichao 2015年8月19日
 *
 */
public interface CategoryCommentMapper {
	
	
	/**
	 * @param category 分类信息
	 * @return
	 */
	public List<CategoryComment> getCommentList(CategoryComment cmt,RowBounds rb);
	
	public List<CategoryComment> getCommentListWithNoNickname(CategoryComment cmt,RowBounds rb);
	
	/**
	 * 添加一条基础评论信息
	 * @param comment
	 */
	public void addComment(CategoryComment comment);
}
