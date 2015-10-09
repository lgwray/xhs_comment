package net.shinc.service.xhscomment;

import java.util.List;

import net.shinc.InfoMgmtApplication;
import net.shinc.orm.mybatis.bean.xhscomment.Nick;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoMgmtApplication.class)
@WebAppConfiguration
public class NickServiceTest {

	@Autowired
	private NickService service;
	
	@Test
	@Transactional
	public void getNicksRandom() {
		List<Nick> list = service.getNicksRandom(100);
		System.out.println(list.toString());
	}

	@Test
	@Transactional
	public void getNickListByPage2() {
		PageBounds pageBounds = new PageBounds(Integer.parseInt("1"), Integer.parseInt("50"));
		service.getNickListByPage2("0", pageBounds);
	}
	
}
