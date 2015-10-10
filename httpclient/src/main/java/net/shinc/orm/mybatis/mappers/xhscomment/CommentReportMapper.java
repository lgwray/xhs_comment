package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.CommentReport;

public interface CommentReportMapper {
	
    int insert(CommentReport record);

    int insertSelective(CommentReport record);
    
    int deleteReportByDate(String date);
    
    List<Map> getReportByDate(String date);
}