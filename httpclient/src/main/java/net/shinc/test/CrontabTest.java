package net.shinc.test;

import java.util.Map;
import java.util.Random;

import net.shinc.orm.mybatis.bean.common.News;
import net.shinc.service.impl.NewsServiceImpl;
import net.shinc.utils.HttpXmlClient;

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
			Map params = NewsServiceImpl.getDiscussParamMap(news, "0");
			HttpXmlClient.post("http://xhpfm.mobile.zhongguowangshi.com:8091/v200/user/comment", params);
			System.out.println(count);
		}
	}
}
