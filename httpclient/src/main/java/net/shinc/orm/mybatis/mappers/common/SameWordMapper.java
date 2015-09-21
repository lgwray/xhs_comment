package net.shinc.orm.mybatis.mappers.common;

import java.util.List;

import net.shinc.orm.mybatis.bean.common.SameWord;

public interface SameWordMapper {
	

    public SameWord getSameWord(String word);
    
    public List<SameWord> getAll();

}