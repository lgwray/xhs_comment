package net.shinc.service.xhscomment.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.AutoSendArticle;
import net.shinc.orm.mybatis.mappers.xhscomment.AutoSendArticleMapper;
import net.shinc.service.xhscomment.AutoSendArticleService;
import net.shinc.utils.DateUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
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
	public Integer addAutoSendArticle(AutoSendArticle record,Integer days) {
		try {
			if (null != record) {
				String articleId = record.getArticleId();
				String matchNewsId = record.getMatchNewsId();
				Boolean b = hasAutoSendArticle(Integer.parseInt(articleId),Integer.parseInt(matchNewsId));
				Integer num = 0;
				if(b){
					if(record.getEnabled().equals("1")) {
						Date beginDate = new Date();
						record.setBegindate(beginDate);
						Date enddate = DateUtils.getAfterDays(Calendar.getInstance(), days);
						record.setEnddate(enddate);
					}
					num = autoMapper.updateByPrimaryKeySelective(record);
				} else {
					Date beginDate = new Date();
					record.setBegindate(beginDate);
					Date enddate = DateUtils.getAfterDays(Calendar.getInstance(), days);
					record.setEnddate(enddate);
					num = autoMapper.insertSelective(record);
				}
				return num;
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return 0;
	}

	@Override
	public Boolean hasAutoSendArticle(Integer articleId,Integer matchNewsId) {
		Map map = new HashMap();
		map.put("articleId", articleId);
		map.put("matchNewsId", matchNewsId);
		AutoSendArticle record = autoMapper.selectByArticleIdAndMatchNewsId(map);
		if(null != record) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean isEnableAutoSendArticle(Integer articleId,Integer matchNewsId) {
		Map map = new HashMap();
		map.put("articleId", articleId);
		map.put("matchNewsId", matchNewsId);
		AutoSendArticle record = autoMapper.selectByArticleIdAndMatchNewsId(map);
		if(null != record) {
			if(record.getEnabled().equals("1")){
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public List<Map> getAutoSendList() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("currentDate", new Date());
		List<Map> autoSendList = autoMapper.getAutoSendList(param);
		return autoSendList;
	}

	@Override
	public Integer getAutoCommentNumByDate(String date) {
		return null;
	}

}
