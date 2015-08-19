package net.shinc.service.xhscomment.impl;

import java.util.Date;
import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.CategoryComment;
import net.shinc.orm.mybatis.bean.xhscomment.CommentCategory;
import net.shinc.orm.mybatis.mappers.xhscomment.CategoryCommentMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.CommentCategoryMapper;
import net.shinc.service.xhscomment.BaseCommentService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class BaseCommentServiceImpl implements BaseCommentService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<CommentCategory> categoryList;
	
	@Autowired
	private CategoryCommentMapper commentMapper;
	
	@Autowired
	private CommentCategoryMapper categoryMapper;

	@Override
	public List<CommentCategory> getCategory() {
		try {
			if(categoryList == null) {
				categoryList = categoryMapper.getCategoryList();
			}
			return categoryList;
		} catch(Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return null;
		}
		
	}

	@Override
	public void addCategory(String name) throws DuplicateKeyException {
		
		if(StringUtils.isEmpty(name)) {
			throw new IllegalArgumentException(name);
		}
		CommentCategory category = new CommentCategory();
		category.setName(name);
		categoryMapper.addCategory(category);
		categoryList = null;
		
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

}
