package net.shinc.quartz.task.xhscomment;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.shinc.InfoMgmtApplication;
import net.shinc.orm.mybatis.bean.common.SameWord;
import net.shinc.service.common.SameWordService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.CollectionUtils;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoMgmtApplication.class)
@WebAppConfiguration
public class GenerateCommentJobTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SameWordService sameWordService;

	@Autowired
	@Qualifier("sqlSession")
	private SqlSessionTemplate sqlSession;
	@Test
	public void getSameword() {
		List list = sameWordService.getAll();
		System.out.println(list);
	}

	@Test
	public void testGenerate() throws IOException {
		List<SameWord> swList = sameWordService.getAll();
		Map swMap = new HashMap();
		for(SameWord sw : swList) {
			swMap.put(sw.getWord(), sw.getWordLike());
		}
		String aid = "259947";
		Map map = new HashMap();
		map.put("id", aid);
		List<Map> list = this.sqlSession.selectList("selectxhsCommentByArticleId",map);
		int size = list.size();
		Random random = new Random(100000);
		for(Map<String,String> tmp : list) {
			String nick = tmp.get("nick");
			String content = tmp.get("content");
			
			String newNick = getNickRandom(random,list);
			String newContent = dealContent(content, swMap);
			
			Gson g = new Gson();
			Map gm = new HashMap();
			gm.put("content",content);
			gm.put("newContent",newContent);
			gm.put("newNick",newNick);
			gm.put("nick",nick);
			
			System.out.println(g.toJson(gm));
//			System.out.println(String.format("nick   =%-50scontent   =%s%nnewNick=%-50snewContent=%s", nick,content,newNick,newContent));
		}
		
	}
	public static String getNickRandom(Random random,List<Map> list) {
		
		int tmp = random.nextInt(list.size());
		return (String)list.get(tmp).get("nick");
	}
	public static String dealContent(String content,Map swMap) throws IOException {
		Random random = new Random(100000);
		List<String> commaList = new ArrayList();
		commaList.add("~");
		commaList.add("~~");
		commaList.add("~~~");
		commaList.add("`");
		commaList.add("'");
		commaList.add("-");
		commaList.add("-");
		commaList.add(" ");
		commaList.add("  ");
		int commaSize = commaList.size();
		random.nextBoolean();
		IKSegmenter  s = new org.wltea.analyzer.core.IKSegmenter(new StringReader(content),true);
		Lexeme l;
		StringBuilder sb = new StringBuilder();
		while(( l = s.next()) != null) {
			String text = l.getLexemeText();
			
			List same = (List)swMap.get(text);
			if(!CollectionUtils.isEmpty(same)) {
				if(random.nextBoolean()) {//处理同义词
					if(same.size() > 1) {
						sb.append(same.get(random.nextInt(same.size())));
					} else {
						sb.append(same.get(0));
					}
				} else {
					sb.append(text);
				}
			} else {
				sb.append(text);
			}
			
			if(random.nextBoolean()) {//处理标点
				sb.append(commaList.get(random.nextInt(commaSize)));
			}
		}
		return sb.toString();
	}
}
