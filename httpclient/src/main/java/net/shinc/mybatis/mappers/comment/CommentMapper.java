package net.shinc.mybatis.mappers.comment;

import java.util.List;

import net.shinc.orm.mybatis.bean.common.News;

public interface CommentMapper {
	public List<News> selectNeuterComment(Integer count);
}
