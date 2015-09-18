package net.shinc.service.xhscomment;

import java.util.ArrayList;
import java.util.List;

import net.shinc.InfoMgmtApplication;
import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;

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
public class MatchNewsServiceTest {

	@Autowired
	private MatchNewsService service;
	
	@Test
	@Transactional
	public void getMatchNewsCommentsBatch() {
		List list = new ArrayList();
		list.add(17);
		list.add(18);
		List<MatchComment> batch = service.getMatchNewsCommentsBatch(list);
		System.out.println(batch.size());
	}
	
}
