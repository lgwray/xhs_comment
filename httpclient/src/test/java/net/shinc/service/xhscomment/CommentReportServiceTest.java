package net.shinc.service.xhscomment;

import java.util.List;

import net.shinc.InfoMgmtApplication;
import net.shinc.utils.DateUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoMgmtApplication.class)
@WebAppConfiguration
public class CommentReportServiceTest {

	@Autowired
	private CommentReportService service;
	
	@Test
//	@Transactional
	public void generateTodayReport() {
		service.generateTodayReport();
	}
	
	@Test
//	@Transactional
	public void generateReportByDate() {
		List<String> date = DateUtils.getBeforeFewsDate(30, "yyyy-MM-dd");
		for (String string : date) {
			service.generateReportByDate(string);
		}
	}
	
	@Test
	public void deleteReportByDate() {
		Integer num = service.deleteReportByDate("2015-10-10");
		System.out.println(num);
	}
	
	
	
}
