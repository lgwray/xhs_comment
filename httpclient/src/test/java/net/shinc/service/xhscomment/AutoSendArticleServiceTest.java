package net.shinc.service.xhscomment;

import net.shinc.InfoMgmtApplication;

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
public class AutoSendArticleServiceTest {

	@Autowired
	private AutoSendArticleService service;
	
	@Test
	@Transactional
	public void hasAutoSendArticle() {
		Boolean b = service.hasAutoSendArticle(2697571);
		System.out.println(b);
	}
	
}
