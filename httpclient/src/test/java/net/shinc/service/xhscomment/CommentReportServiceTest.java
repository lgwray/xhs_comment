package net.shinc.service.xhscomment;

import net.shinc.InfoMgmtApplication;

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
	public void deleteReportByDate() {
		Integer num = service.deleteReportByDate("2015-10-10");
		System.out.println(num);
	}
	
}
