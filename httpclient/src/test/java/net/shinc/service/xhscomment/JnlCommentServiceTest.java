package net.shinc.service.xhscomment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.shinc.InfoMgmtApplication;
import net.shinc.orm.mybatis.bean.xhscomment.JnlComment;
import net.shinc.orm.mybatis.bean.xhscomment.JnlComment.SendFlag;

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
public class JnlCommentServiceTest {

	@Autowired
	private JnlCommentService jcs;
	
	@Test
	@Transactional
	public void putComment() {
		List list = new ArrayList();
		JnlComment c = new JnlComment();
		c.setAddDate(new Date());
		c.setArticleId("1");
		c.setContent("test1");
		c.setSendFlag("0");
		c.setUserId(-1);
		
		for(int i=0; i<1005; i++) {
			list.add(c);
		}
		jcs.putComment(list);
	}
	
	@Test
	public void getCommentBySendFlag() {
		System.out.println(jcs.getCommentBySendFlag(SendFlag.nosend, 10));
	}
	
	@Test
	public void updateCommentSendFlag() {
		List list = new ArrayList();
		JnlComment c = new JnlComment();
		c.setId(2000);
		c.setSendFlag("2");
		
		list.add(c);
		list.add(c);
		list.add(c);
		list.add(c);
		list.add(c);
		list.add(c);
		jcs.updateCommentSendFlag(list);
	}
}
