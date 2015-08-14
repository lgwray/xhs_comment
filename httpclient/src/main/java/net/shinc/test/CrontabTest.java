package net.shinc.test;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.http.NameValuePair;

import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.utils.HttpClient;
import net.shinc.utils.HttpXmlClient;
import net.shinc.utils.ParamUtils;

public class CrontabTest {
	public static void main(String[] args) {
		String str = "热";
		News news = new News();
		news.setId("214033");
		int count = 0;
		boolean flag = true;
		for(int i=1; i<10 ;i++){
			if(count >= 10){
				break;
			}
			count = i;
			try {
				news.setContent(str+i);
				Random random = new Random();
				Thread.sleep(1000*random.nextInt(10));
			} catch (InterruptedException e) {
				e.printStackTrace(); 
			}
			List<NameValuePair> list = ParamUtils.getDiscussParamList(news, "0","jack");
			HttpClient.post("http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment", list);
			System.out.println(count);
		}
	}
}
