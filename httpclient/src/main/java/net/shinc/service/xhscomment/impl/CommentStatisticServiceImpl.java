package net.shinc.service.xhscomment.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.orm.mybatis.bean.xhscomment.CommentStatistic;
import net.shinc.orm.mybatis.mappers.xhscomment.CommentStatisticMapper;
import net.shinc.service.xhscomment.CommentStatisticService;

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
	
	@Override
	public List<Map<String,Object>> getCommentStatisticByDate(List<String> date) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(!CollectionUtils.isEmpty(date)) {
			for (String dateStr : date) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("date", dateStr);
				
				List<CommentStatistic> commentStatisticList = csMapper.getCommentStatisticByDate(dateStr);
				List<Map<String,Object>> percentList = new ArrayList<Map<String,Object>>();
				if(!CollectionUtils.isEmpty(commentStatisticList)) {
					for (CommentStatistic commentStatistic : commentStatisticList) {
						Map<String,Object> item = new HashMap<String,Object>();
						Integer shincSum = commentStatistic.getDivisor();
						Integer xhsSum = commentStatistic.getDividend();
						Integer percent = commentStatistic.getPercent();
						Integer flag = commentStatistic.getStatisticType();
						String desc = flag == 1 ? "总数" : "要闻";
						
						item.put("shincSum", shincSum);
						item.put("xhsSum", xhsSum);
						item.put("percent", percent);
						item.put("desc", desc);
						percentList.add(item);
					}
				}
				map.put("percentList", percentList);
				list.add(map);
			}
			return list;
		}
		return null;
	}

}
