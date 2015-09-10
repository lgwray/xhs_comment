package net.shinc.service.xhscomment.impl;

import net.shinc.orm.mybatis.bean.xhscomment.NickName;
import net.shinc.orm.mybatis.mappers.xhscomment.NickNameMapper;
import net.shinc.service.xhscomment.NickNameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NickNameServiceImpl implements NickNameService {

	@Autowired
	private NickNameMapper nickNameMapper;

	@Override
	public Integer addNickName(NickName nickname) {
		if(null != nickname){
			int i = nickNameMapper.insertSelective(nickname);
			return i;
		}
		return 0;
	}
	

}
