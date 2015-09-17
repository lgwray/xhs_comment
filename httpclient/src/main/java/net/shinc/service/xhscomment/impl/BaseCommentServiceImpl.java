package net.shinc.service.xhscomment.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.shinc.common.TreeNode;
import net.shinc.orm.mybatis.bean.xhscomment.CategoryComment;
import net.shinc.orm.mybatis.bean.xhscomment.CommentCategory;
import net.shinc.orm.mybatis.mappers.comment.CommentMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.CategoryCommentMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.CommentCategoryMapper;
import net.shinc.service.xhscomment.BaseCommentService;
import net.shinc.utils.DateUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BaseCommentServiceImpl implements BaseCommentService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<TreeNode<CommentCategory>> categoryList;
	
	@Autowired
	private CategoryCommentMapper commentMapper;
	
	@Autowired
	private CommentCategoryMapper categoryMapper;
	
	@Autowired
	private CommentMapper cmMapper;

	@Override
	public List<TreeNode<CommentCategory>> getCategory() {
		try {
			categoryList = categoryMapper.getCategoryList();
			return categoryList;
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
		
	}
	
	public List<TreeNode<CommentCategory>> getCategoryTree() {
		try {
			List<TreeNode<CommentCategory>> list = categoryMapper.getCategoryList();
			
			List<TreeNode<CommentCategory>> root = new ArrayList<TreeNode<CommentCategory>>();
			Map<Integer,TreeNode<CommentCategory>> map = new HashMap();
			for(Iterator<TreeNode<CommentCategory>> it = list.iterator(); it.hasNext();) {
				TreeNode<CommentCategory> node = it.next();
				map.put(node.getId(), node);
			}
			
			for(Iterator<TreeNode<CommentCategory>> it = list.iterator(); it.hasNext();) {
				TreeNode<CommentCategory> node = it.next();
				Integer parentId = node.getParent();
				if(parentId == 0) {
					root.add(node);
				} else {
					TreeNode<CommentCategory> parent = map.get(parentId);
					if(parent == null) {
						logger.warn("can not find its parent for :" + node);
					} else {
						parent.addChild(node);
					}
				}
			}
			return root;
			
			
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
	}

	@Override
	public int addCategory(String name,Integer parent) throws DuplicateKeyException {
		
		if(StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException(name);
		}
		CommentCategory category = new CommentCategory();
		category.setName(name);
		category.setParent(parent);
		categoryList = null;
		categoryMapper.addCategory(category);
		return category.getId();
		
	}

	@Override
	public void addComment(Integer categoryId, String content, Date uptime) {
		
		if(categoryId == null || StringUtils.isEmpty(content) || uptime == null) {
			throw new IllegalArgumentException("check your params");
		}
		CategoryComment comment = new CategoryComment();
		comment.setCategoryId(categoryId);
		comment.setContent(content);
		comment.setAddTime(uptime);
		commentMapper.addComment(comment);
		
	}

	@Override
	public List<CategoryComment> getCommentList(Integer categoryId,RowBounds rb) {
		if(categoryId == null || rb == null) {
			throw new IllegalArgumentException("check your params");
		}
		CategoryComment cmt = new CategoryComment();
		cmt.setCategoryId(categoryId);
		return commentMapper.getCommentList(cmt, rb);
	}
	
	/**
	 * 获得每天的总评论数
	 * @return
	 */
	@Override
	public List getLocalEverydayCommentsNums() {
		List list = cmMapper.getLocalCommentsNums();
		return list;
	}
	
	@Override
	public Map getTodayCommentsNums() {
		String today = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
		Map map = cmMapper.getLocalCommentsNumsByDate(today);
		return map;
	}
	
	@Override
	public Map getTodayRemoteNums() {
		return cmMapper.getTodayRemoteNums();
	}
	
	@Override
	public Map getTodayRemoteNumsByCategory(String categoryid) {
		if(!StringUtils.isEmpty(categoryid)){
			Map map = cmMapper.getTodayRemoteNumsByCategory(categoryid);
			return map;
		}
		return null;
	}
	
	public static void main(String[] args) {
		String dateToString = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
		System.out.println(dateToString);
	}


	
}
