package net.shinc.service.xhscomment;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.dao.DuplicateKeyException;

import net.shinc.common.TreeNode;
import net.shinc.orm.mybatis.bean.common.ResultBean;
import net.shinc.orm.mybatis.bean.xhscomment.CommentCategory;

/**
 * 基础评论服务层
 * @author zhangtaichao 2015年8月19日
 *
 */
public interface BaseCommentService {

	/**
	 * @return 基础评论 分类信息
	 */
	public List getCategory();
	
	public List getCategoryTree();
	
	/**
	 * 新增一个分类
	 * @param name
	 */
	public int addCategory(String name,Integer parent) throws DuplicateKeyException;
	
	/**
	 * 新增一条基础评论
	 * @param categoryId 分类ID
	 * @param content 内容
	 * @param uptime 上传时间
	 */
	public void addComment(Integer categoryId,String content,Date uptime);
	
	/**
	 * 根据分类ID查询基础评论
	 * @param categoryId
	 * @return
	 */
	public List getCommentList(Integer categoryId,RowBounds rb);
	
	/**
	 * 获得每天的总评论数
	 * @return
	 */
	public List getLocalEverydayCommentsNums();
	
	/**
	 * 获得当天的总评论数
	 * @return
	 */
	public List getTodayCommentsNums();
}
