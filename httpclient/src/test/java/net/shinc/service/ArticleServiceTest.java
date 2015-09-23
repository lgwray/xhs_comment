package net.shinc.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.shinc.InfoMgmtApplication;
import net.shinc.orm.mybatis.bean.common.AdminUser;
import net.shinc.orm.mybatis.bean.common.Menu;
import net.shinc.orm.mybatis.bean.xhscomment.MatchComment;
import net.shinc.service.common.MenuService;
import net.shinc.utils.Helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoMgmtApplication.class)
@WebAppConfiguration
public class ArticleServiceTest {

	@Autowired
	private ArticleService articleService;
	
	@Test
	public void getArticlesById(){
		Integer articleId = 260245;
		Map map = articleService.getArticlesById(articleId);
		Helper.iteratorMap(map);
	}
	
	@Test
	public void getMatchCommentsByArticleId() {
		Integer articleId = 260015;
		List<MatchComment> list = articleService.getMatchCommentsByArticleId(articleId);
		iterator(list);
	}
	
	@Test
	public void getMatchNewsIdByArticleId(){
		Integer articleId = 264365;
		List<String> list = articleService.getMatchNewsIdByArticleId(articleId);
		System.out.println(list);
	}
	
	public void iterator(Collection<?> c){
		for (Object object : c) {
			System.out.println(object);
		}
	}
}
