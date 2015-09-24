package net.shinc.service.xhscomment.impl;

import net.shinc.orm.mybatis.bean.xhscomment.AutoSendArticle;
import net.shinc.orm.mybatis.mappers.xhscomment.AutoSendArticleMapper;
import net.shinc.service.xhscomment.AutoSendArticleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AutoSendArticleServiceImpl
 * @Description TODO
 * @author guoshijie
 * @date 2015年9月24日 下午8:16:01
 */
@Service
public class AutoSendArticleServiceImpl implements AutoSendArticleService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AutoSendArticleMapper autoMapper;

	@Override
	public Integer addAutoSendArticle(AutoSendArticle record) {
		if (null != record) {
			Integer num = autoMapper.insertSelective(record);
			return num;
		}
		return null;
	}

}
