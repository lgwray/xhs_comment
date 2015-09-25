package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.CommentStatistic;

public interface CommentStatisticMapper {
	
    int insert(CommentStatistic record);

    int insertSelective(CommentStatistic record);
    
    List<CommentStatistic> getCommentStatisticByDate(String date);
    
}