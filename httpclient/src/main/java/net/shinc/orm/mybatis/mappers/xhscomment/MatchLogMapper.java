package net.shinc.orm.mybatis.mappers.xhscomment;

import net.shinc.orm.mybatis.bean.xhscomment.MatchLog;

/** 
 * @ClassName MatchLogMapper 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月18日 下午6:44:00  
 */
public interface MatchLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatchLog record);

    int insertSelective(MatchLog record);

    MatchLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatchLog record);

    int updateByPrimaryKey(MatchLog record);
}