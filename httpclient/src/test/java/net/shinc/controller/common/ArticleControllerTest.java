package net.shinc.controller.common;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import net.shinc.InfoMgmtApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoMgmtApplication.class)
@WebAppConfiguration
public class ArticleControllerTest {
	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;
    private ResultHandler handler;
    
    @Before  
    public void init(){  
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).apply(springSecurity()).build(); 
    	handler = MockMvcResultHandlers.print();
    } 
    
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    @Test
    public void refreshArticleList() {
    	//要闻
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "470").param("ctype", "4001");
    	
    	//体育
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "461").param("ctype", "4002");
    	
    	//国际
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "462").param("ctype", "4002");
    	
    	//财经
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "463").param("ctype", "4002");
    	
    	//军事
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "464").param("ctype", "4002");
    	
    	//汽车
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "477").param("ctype", "4002");
    	
    	//图片
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "479").param("ctype", "4002");
    	
    	//视频
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "480").param("ctype", "4002");
    	
    	//评论
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "235").param("ctype", "4003");
    	
    	//社会
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "3172").param("ctype", "4003");
    	
    	//科技
//    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "3173").param("ctype", "4003");
    	
    	//动新闻
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/refreshArticleList").param("cid", "3174").param("ctype", "4003");
    	
		try {
			mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    @Test
    public void getArticleListByDate() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/getArticleListByDate").param("publisDate", "2015-08-18");
    	try {
    		mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    @Test
    public void autoRefreshArticleList() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/autoRefreshArticleList").param("cid", "470").param("ctype", "4001");
    	try {
    		mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    public void testgetNewsListByTitle() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/getNewsListByTitle")
    			.param("type", "news")
    			.param("title", "奥迪300万")
    			.param("page", "1")
    			.param("num", "10");
		try {
			mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Test
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    public void testgetCommentsByNews() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/article/getCommentsByNews")
    			.param("type", "news")
    			.param("newsid", "86829")
    			.param("page", "1")
    			.param("num", "50");
    	try {
    		mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
