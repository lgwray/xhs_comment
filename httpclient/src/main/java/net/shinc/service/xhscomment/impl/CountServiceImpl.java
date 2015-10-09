package net.shinc.service.xhscomment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.mappers.comment.CommentMapper;
import net.shinc.quartz.task.xhscomment.FetchArticleListJob.ArticleCategory;
import net.shinc.service.xhscomment.CountService;
import net.shinc.utils.DateUtils;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * @ClassName CountServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年9月17日 上午10:18:15  
 */
@Service
public class CountServiceImpl implements CountService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private String pattern = "yyyy-MM-dd";
	
	@Autowired
	private CommentMapper cmMapper;
	
	@Override
	public List getLocalEverydayCommentsNums() {
		List list = cmMapper.getLocalCommentsNums();
		return list;
	}

	@Override
	public Map getLocalCommentsNumsByDate(String date) {
		if(!StringUtils.isEmpty(date)) {
			Map map = cmMapper.getLocalCommentsNumsByDate(date);
		}
		return null;
	}

	@Override
	public List<Map> getTotalPercent(String date) {
		List<Map> list = new ArrayList<Map>();
		if(!StringUtils.isEmpty(date)) {
			Map map = getTotalByDate(date);
			list.add(map);
		} else {
			List<String> fewsDate = DateUtils.getBeforeFewsDate(4, pattern);
			for (String dateStr : fewsDate) {
				list.add(getTotalByDate(dateStr));
			}
		}
		return list;
	}
	
	public Map getTotalByDate(String date) {
		Map map = new HashMap();
		if(!StringUtils.isEmpty(date)) {
			map.put("date", date);
			Map articlesNumByDate = getArticlesNumByDate(date);
			map.put("articleNum", articlesNumByDate.get("sum"));
			
			List percentList = new ArrayList();
			Map yaowen = getTotalNumAndPercent(date, "0");
			percentList.add(yaowen);
			
			//总数
			Map xhsTotalMap = getTotalNumAndPercent(date);
			percentList.add(xhsTotalMap);
			
			map.put("percentList", percentList);
			return map;
		}
		return null;
	}
	
	/**
	 * 获取新华社总数与本地总数，及百分比
	 * @param date
	 * @return
	 */
	public Map getTotalNumAndPercent(String date) {
		Map map = new HashMap();
		map.put("desc", "总数");
		
		//新华社总数
		Map map2 = cmMapper.getXhsCommentSum(date);
		BigDecimal xhsSum = (BigDecimal)map2.get("sum");
		map.put("xhsSum", xhsSum);
		
		//世和总数
		Map map3 = cmMapper.getLocalCommentsNumsByDate(date);
		Long shincSum = (Long)map3.get("sum");
		map.put("shincSum", shincSum);
		
		//占比
		String percent = Helper.getPercent(shincSum.toString(), xhsSum.toString());
		map.put("percent", percent);
		return map;
	}
	
	/**
	 * 获取新华社总数与本地总数，及百分比(根据栏目和日期)
	 * @return
	 */
	public Map getTotalNumAndPercent(String date,String categoryid) {
		Map map = new HashMap();
		String remark = null;
		
		ArticleCategory[] values = ArticleCategory.values();
		for (ArticleCategory articleCategory : values) {
			if (articleCategory.getValue().equals(categoryid)) {
				remark = articleCategory.getRemark();
				break;
			}
		}
		map.put("desc", remark);
		
		//新华社总数
		Map param = new HashMap();
		param.put("date", date);
		param.put("categoryid", categoryid);
		Map byCategory = cmMapper.getXhsCommentSumByCategory(param);
		BigDecimal xhsSum = (BigDecimal)byCategory.get("sum");
		map.put("xhsSum", xhsSum);
		
		//世和总数
		Map map3 = cmMapper.getLocalCommentsNumsByCategory(param);
		Long shincSum = (Long)map3.get("sum");
		map.put("shincSum", shincSum);
		
		//占比
		String percent = Helper.getPercent(shincSum.toString(), xhsSum.toString());
		map.put("percent", percent);
		
		return map;
	}
	
	@Override
	public Map getArticlesNumByDate(String date) {
		if(!StringUtils.isEmpty(date)) {
			Map articlesNumByDate = cmMapper.getArticlesNumByDate(date);
			return articlesNumByDate;
		}
		return null;
	}

	@Override
	public Map getXhsCommentSum(String date) {
		return null;
	}

	@Override
	public Map getXhsCommentSumByCategory(String date, String categoryid) {
		if(!StringUtils.isEmpty(categoryid)){
			Map map = cmMapper.getTodayRemoteNumsByCategory(categoryid);
			return map;
		}
		return null;
	}

	@Override
	public Integer getCommentNumByUserId(String date, String userId, String comment_way) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("date", date);
		map.put("comment_way", comment_way);
		Map commentNumByUserId = cmMapper.getCommentNumByUserId(map);
		Long numstr = (Long)commentNumByUserId.get("sum");
		int value = numstr.intValue();
		return value;
	}

}
