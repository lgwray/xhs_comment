package net.shinc.service.xhscomment;

import java.util.Map;

import net.shinc.InfoMgmtApplication;
import net.shinc.utils.Helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoMgmtApplication.class)
@WebAppConfiguration
public class CountServiceTest {

	@Autowired
	private CountService service;
	
	@Test
	@Transactional
	public void getArticlesNumByDate() {
		String date = "2015-09-16";
		Map articlesNumByDate = service.getArticlesNumByDate(date);
		Helper.iteratorMap(articlesNumByDate);
	}
	
}
