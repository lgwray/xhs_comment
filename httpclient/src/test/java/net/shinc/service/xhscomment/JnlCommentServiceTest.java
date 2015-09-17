package net.shinc.service.xhscomment;

import java.io.IOException;
import java.io.StringReader;
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
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

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
	
	@Test
	public void IKAnalyze() {
		
	}
	public static void main(String [] args) throws IOException {
		String text="使用者第一步最有可能的是想要体验了解庖丁的分词效果。考虑到这样的需求，庖丁提供了一个shell文件，使用者不必写任何代码就可以获得这样的信息。进入Paoding-Analysis分发包，在命令行模式下执行analyzer.bat(windows)或analyzer.sh(linux)即可。下以windows为例： u       显示帮助 E:/Paoding-Analysis>analyzer.bat ?  u       分词对话 当没有在命令行参数种输入分词内容或待分词的文章时，analyzer.bat进入分词对话模式，使用者可以多次输入或粘贴不同的文字内容，查看分词效果，如： E:/Paoding-Analysis>analyzer.bat paoding> | 此时使用者可以在光标所在所在位置(|)输入或粘贴待分词的内容(以分号结束)，按下Enter键换行，analyzer.bat便可以输出分词结果。比如： paoding> 中文分词; 1:      中文/分词/ 分词器net.paoding.analysis.analyzer.PaodingAnalyzer 内容长度 4字符， 分 2个词 分词耗时 0ms -------------------------------------------------- 分词完毕后，又会进入以上对话模式。 键入:e或:q退出会话 (包括:符号) 。 键入:?显示帮助(包括:符号) 。 u       对文件进行分词 analyzer.bat允许对指定的文件进行分词体验。文件以路径名给出可以使绝对地址、相对当前目录的地址，或以classpath:为前缀的类路径地址。示例如下:";  
//        //创建分词对象  
//		Analyzer anal=new IKAnalyzer(true);       
//        StringReader reader=new StringReader(text);  
//        //分词  
//        TokenStream ts=anal.tokenStream("", reader);  
//        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);  
//        //遍历分词数据  
//        while(ts.incrementToken()){  
//            System.out.print(term.toString()+"|");  
//        }  
//        reader.close();  
//        System.out.println();
		IKSegmenter  s = new org.wltea.analyzer.core.IKSegmenter(new StringReader(text),true);
		Lexeme l;
		while(( l = s.next()) != null) {
			System.out.println(l.getLexemeText());
		}
	}
}
