package net.shinc.service.xhscomment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.CommentStatistic;
import net.shinc.orm.mybatis.mappers.xhscomment.CommentStatisticMapper;
import net.shinc.service.xhscomment.CommentStatisticService;
import net.shinc.service.xhscomment.CountService;
import net.shinc.utils.DateUtils;
import net.shinc.utils.Helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/** 
 * @ClassName CommentStatisticServiceImpl 
 * @Description 评论统计接口实现
 * @author guoshijie 
 * @date 2015年9月25日 下午6:58:33  
 */
@Service
public class CommentStatisticServiceImpl implements CommentStatisticService {

	@Autowired
	private CommentStatisticMapper csMapper;
	private String pattern = "yyyy-MM-dd";
	
	@Autowired
	private CountService countService;
	
	@Override
	public Map<String,Object> getCommentStatisticByDate(String date) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("date", date);
		
//		Map articlesNumByDate = countService.getArticlesNumByDate(date);
//		map.put("articleNum", articlesNumByDate.get("sum"));
		
		List<CommentStatistic> commentStatisticList = csMapper.getCommentStatisticByDate(date);
		List<Map<String,Object>> percentList = new ArrayList<Map<String,Object>>();
		if(!CollectionUtils.isEmpty(commentStatisticList)) {
			for (CommentStatistic commentStatistic : commentStatisticList) {
				Map<String,Object> item = new HashMap<String,Object>();
				Integer shincSum = commentStatistic.getDivisor();
				Integer xhsSum = commentStatistic.getDividend();
				Integer articleSum = commentStatistic.getArticleNum();
				Integer autoSum = commentStatistic.getAutoNum();
				BigDecimal percent = commentStatistic.getPercent();
				Integer flag = commentStatistic.getStatisticType();
				String desc = flag == 1 ? "总数" : "要闻";
				String lastUpdateTime = commentStatistic.getInsertDate();
				
				map.put("articleNum", articleSum);
				map.put("autoNum", autoSum);
				map.put("lastUpdateTime", lastUpdateTime);
				
				item.put("shincSum", shincSum);
				item.put("xhsSum", xhsSum);
				item.put("articleSum", articleSum);
				item.put("autoSum", autoSum);
				item.put("percent", Helper.decimalToPercent(percent));
				item.put("desc", desc);
				percentList.add(item);
			}
			map.put("percentList", percentList);
			return map;
		}
		return null;
	}
	
	@Override
	public List<Map<String,Object>> getCommentStatisticByDate(List<String> datelist) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(!CollectionUtils.isEmpty(datelist)) {
			for (String dateStr : datelist) {
				Map<String, Object> map = getCommentStatisticByDate(dateStr);
				if(!CollectionUtils.isEmpty(map)) {
					list.add(map);
				}
			}
			return list;
		}
		return null;
	}
	
	@Override
	public List<Map<String,Object>> getPercentByDays(String date){
		List<String> list = null;
		if(!StringUtils.isEmpty(date)) {
			list = new ArrayList<String>();
			list.add(date);
		} else {
			list = DateUtils.getBeforeFewsDate(7, pattern);
		}
		List<Map<String, Object>> commentStatisticByDate = getCommentStatisticByDate(list);
		return commentStatisticByDate;
	}

}
