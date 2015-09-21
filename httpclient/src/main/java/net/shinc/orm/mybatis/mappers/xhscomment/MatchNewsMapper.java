package net.shinc.orm.mybatis.mappers.xhscomment;

import java.util.List;

import net.shinc.orm.mybatis.bean.xhscomment.MatchNews;

/** 
 * @ClassName MatchNewsMapper 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月18日 下午6:44:07  
 */
public interface MatchNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatchNews record);

    int insertSelective(MatchNews record);

    MatchNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchNews record);

    int updateByPrimaryKey(MatchNews record);
    
    List<MatchNews> getMatchNewsBatch(List<String> list);
    
}