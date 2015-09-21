package net.shinc.service.common.impl;

import java.util.List;

import net.shinc.orm.mybatis.bean.common.SameWord;
import net.shinc.orm.mybatis.mappers.common.SameWordMapper;
import net.shinc.service.common.SameWordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SameWordServiceImpl implements SameWordService {

	@Autowired
	private SameWordMapper sameWordMapper;
	@Override
	public SameWord getSameWord(String word) {
		return sameWordMapper.getSameWord(word);
	}

	@Override
	public List<SameWord> getAll() {
		return sameWordMapper.getAll();
	}

}
