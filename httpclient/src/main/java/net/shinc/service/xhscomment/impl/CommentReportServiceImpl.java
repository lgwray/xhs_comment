package net.shinc.service.xhscomment.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shinc.common.ShincUtil;
import net.shinc.orm.mybatis.bean.common.AdminUser;
import net.shinc.orm.mybatis.bean.common.Company;
import net.shinc.orm.mybatis.bean.xhscomment.CommentReport;
import net.shinc.orm.mybatis.mappers.xhscomment.CommentReportMapper;
import net.shinc.service.common.AdminUserService;
import net.shinc.service.common.impl.JnlServiceImpl;
import net.shinc.service.xhscomment.CommentReportService;
import net.shinc.service.xhscomment.CountService;
import net.shinc.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class CommentReportServiceImpl implements CommentReportService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AdminUserService adminService;
	
	@Autowired
	private CountService countService;
	
	@Autowired
	private JnlServiceImpl jnlServiceImpl;
	
	@Autowired
	private CommentReportMapper crMapper;
	public static String pattern = "yyyy-MM-dd HH:mm:ss";
	
	@Override
	@Transactional
	public void generateTodayReport() {
		String today = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
		generateReportByDate(today);
	}
	
	@Override
	@Transactional
	public void generateReportByDate(String today) {
		Integer sum = 0;
		deleteReportByDate(today);
		List<AdminUser> userList = adminService.getAdminUserList(new Company(1));
		if(!CollectionUtils.isEmpty(userList)){
			for (AdminUser adminUser : userList) {
				Integer userId = adminUser.getId();
				Integer autoSum = countService.getCommentNumByUserId(today, String.valueOf(userId), "2");
				Integer handSum = countService.getCommentNumByUserId(today, String.valueOf(userId), "1");
				
				Map paramMap = new HashMap();
				paramMap.put("userId", userId);
				paramMap.put("addDate", today);
				Integer total = jnlServiceImpl.selectCommentJnlCount(paramMap);
				
				CommentReport cr = new CommentReport();
				cr.setAdminUserId(userId);
				
//				cr.setCreateTime(ShincUtil.formatDate(new Date(), pattern));
				cr.setCreateTime(today);
				cr.setAutoSum(autoSum);
				cr.setHandSum(handSum);
				cr.setTotal(total);
				int i = crMapper.insertSelective(cr);
				sum += i;
			}
		}
		logger.info("日期:"+today+"\t更新报表条数==>"+sum+"条");
	}

	@Override
	public Integer deleteReportByDate(String date) {
		if(StringUtils.isEmpty(date)) {
			return 0;
		}
		return crMapper.deleteReportByDate(date);
	}

	@Override
	public List<Map> getReportByDate(String date) {
		if(StringUtils.isEmpty(date)) {
			return null;
		}
		List<Map> list = crMapper.getReportByDate(date);
		return list;
	}

}
