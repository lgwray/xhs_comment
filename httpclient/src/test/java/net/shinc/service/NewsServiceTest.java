package net.shinc.service;

import java.util.List;

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
public class NewsServiceTest {

	@Autowired
	private NewsService newsService;
	private String userId = "0";
	private String listUrl = "http://xhpfm.mobile.zhongguowangshi.com:8091/v200/indexlist";
	
	@Test
	public void testGetNewsList(){
		List list = newsService.getNewsList(userId, listUrl);
		System.out.println(list);
	}
}
