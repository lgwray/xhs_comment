package net.shinc.service.common.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Address;
import javax.mail.MessagingException;

import net.shinc.service.common.MailService;
import net.shinc.service.xhscomment.CommentStatisticService;
import net.shinc.utils.DateUtils;
import net.shinc.utils.MailUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/** 
 * @ClassName MailServiceImpl 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年10月14日 下午10:55:49  
 */
@Service
public class MailServiceImpl implements MailService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommentStatisticService csService;

	@Override
	public void sendMail(String fromAddr, String pwd, Address[] toAddr, Address[] ccAddr, String bccAddr, String title, String content) {
		try {
			MailUtils.sendMail(fromAddr, pwd, toAddr, ccAddr, bccAddr, title, content);
		} catch (MessagingException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public String getMailTitle() {
		String today = DateUtils.dateToString(new Date(), "yyyy年MM月dd日");
		return today+"评论占比统计";
	}

	@Override
	public String getMailContent() {
		Calendar c = Calendar.getInstance();
		Calendar beforeDay = DateUtils.getBeforeDay(c);
		String today = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
//		String today = DateUtils.dateToString(beforeDay.getTime(), "yyyy-MM-dd");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<style> table{border-collapse: collapse; border: none;} th,td { border: solid #000 1px; } </style>");
		sb.append("</head>");
		
		sb.append("<body>");
		
		sb.append("您好：</br>");
		sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;以下是今天("+today+")的评论占比统计结果，见下表:</br></br>");
		
		sb.append("<table border=0 width='95%'");
		sb.append("<thead>");
		sb.append("<tr>");
		sb.append("<th style='text-align:center;'><i class='icon-bullhorn'></i>统计时间</th>");
		sb.append("<th style='text-align:center;'><i class='icon-bullhorn'></i>新闻总数</th>");
		sb.append("<th style='text-align:center;'><i class='icon-bullhorn'></i>自动评论数</th>");
		sb.append("<th style='text-align:center;'><i class='icon-bullhorn'></i>描述</th>");
		sb.append("<th style='text-align:center;' class='hidden-phone'><i class='icon-bookmark'></i> 新华社</th>");
		sb.append("<th style='text-align:center;'><i class='icon-bookmark'></i> 世和</th>");
		sb.append("<th style='text-align:center;'><i class=' icon-edit'></i> 占比</th>");
		sb.append("</tr>");
		sb.append("</thead>");
		
		sb.append("<tbody id='persent'>");
		
		List<Map<String, Object>> list = csService.getPercentByDays(today);
		Map<String, Object> map2 = csService.getCommentStatisticByTime(today);
		if(!CollectionUtils.isEmpty(map2)) {
			list.add(map2);
		}
		if(!CollectionUtils.isEmpty(list)) {
			for (Map<String, Object> map : list) {
				List percentList = (List)map.get("percentList");
				for(int m = 0;m<percentList.size();m++) {
					sb.append("<tr>");
					Map sub = (Map)percentList.get(m);
					if(m==0){
						sb.append("<td align='center' rowspan='2' width='15%'><a href='#'>"+map.get("lastUpdateTime")+"</a></td>");
						sb.append("<td align='center' rowspan='2' width='15%'><a href='#'>"+map.get("articleNum")+"</a></td>");
						sb.append("<td align='center' rowspan='2' width='15%'><a href='#'>"+map.get("autoNum")+"</a></td>");
					}
					sb.append("<td align='center'><a href='#'>"+sub.get("desc")+"</a></td>");
					sb.append("<td align='center'><a href='#'>"+sub.get("xhsSum")+"</a></td>");
					sb.append("<td align='center'><a href='#'>"+sub.get("shincSum")+"</a></td>");
					sb.append("<td align='center'><a href='#'>"+sub.get("percent")+"</a></td>");
					sb.append("</tr>");
				}
			}
		}

		sb.append("</tbody>");
		sb.append("</table>");
		
		sb.append("</br></br>———————-----</br>");
		sb.append("致礼,祝生活工作愉快</br>");
		sb.append("<label style='font-size:16px;font-weight:bold;'>北京世和科技有限公司</label></br>");
		sb.append("地址 : 北京市朝阳区融科望京中心B座1103</br>");
		sb.append("邮编 : 100102</br>");
		sb.append("电话 : 010-64140968</br>");
		sb.append("网址 : <a href='http://www.shinc.net/'>http://www.shinc.net/</a></br>");
		
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
}
