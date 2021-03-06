package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import net.shinc.common.TreeNode;
import net.shinc.orm.mybatis.bean.xhscomment.CommentCategory;

/**
 * 基础评论分类表
 * @author zhangtaichao 2015年8月19日
 *
 */
public interface CommentCategoryMapper {

	public List<TreeNode<CommentCategory>> getCategoryList();
	
	public int addCategory(CommentCategory category);
	
}
