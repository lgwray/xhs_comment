package net.shinc.service.xhscomment.impl;

import java.util.List;
import java.util.Map;

import net.shinc.formbean.common.NickForm;
import net.shinc.orm.mybatis.bean.xhscomment.Nick;
import net.shinc.orm.mybatis.mappers.xhscomment.NickMapper;
import net.shinc.orm.mybatis.mappers.xhscomment.NickNameMapper;
import net.shinc.service.xhscomment.NickNameService;
import net.shinc.service.xhscomment.NickService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class NickServiceImpl implements NickService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private NickMapper nickMapper;
	
	@Autowired
	private NickNameMapper nickNameMapper;
	
	@Autowired
	private NickNameService nickNameService;
	
	@Override
	public PageList<Nick> getNickListByPage(PageBounds pageBounds) {
		List<Nick> list = nickMapper.getNickListByPage(pageBounds);
		PageList<Nick> pageList = (PageList<Nick>) list;
		return pageList;
	}
	
	@Override
	public PageList<Map> getNickListByPage2(PageBounds pageBounds) {
		List<Map> list = nickMapper.getNickListByPage2(pageBounds);
		PageList<Map> pageList = (PageList<Map>) list;
		return pageList;
	}

	@Override
	public Integer deleteNick(Nick nick) {
		Integer id = nick.getId();
		if(null != id){
			return nickMapper.deleteByPrimaryKey(id);
		}
		return 0;
	}

	@Override
	@Transactional
	public Integer filterNick(NickForm nickform) {
		List<Map> nickList = nickform.getNickList();
		int num2 = nickMapper.updateFlagBatch((nickList));
		logger.info("deal nicknames: " + num2);
		return num2;
	}

	@Override
	public Integer deleteNickById(Integer id) {
		if(null!=id){
			return nickMapper.deleteByPrimaryKey(id);
		}
		return 0;
	}

	@Override
	public Integer deleteNickBatch(List<Map> list) {
		if(!CollectionUtils.isEmpty(list)){
			int i = nickMapper.deleteBatch(list);
			return i;
		}
		return 0;
	}

}
