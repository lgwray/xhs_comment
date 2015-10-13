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
public class XhsNavServiceTest {

	@Autowired
	private XhsNavService service;
	
	@Test
	public void fetchLocalArticleList() {
		service.fetchLocalArticleList();
	}
	
	@Test
	@Transactional
	public void getXhsNavList() {
		service.getXhsNavList();
	}
	
	@Test
//	@Transactional
	public void addXhsNavBatch() {
		Integer num = service.addXhsNavBatch();
		System.out.println(num);
	}
	
}
