package net.shinc.controller.xhscomment;

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
public class MatchNewsControllerTest {

	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;
    private ResultHandler handler;
    
    @Before  
    public void init() {  
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); 
    	handler = MockMvcResultHandlers.print();
    } 
    
    /**
     * 新华社新闻id匹配全网新闻列表
     */
    @Test
    @WithMockUser(username="admin",password="admin")
    public void getMatchNewsListByArticleId(){
    	try {
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/matchNews/getMatchNewsListByArticleId")
    				.param("articleId", "260524");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    @WithMockUser(username="admin",password="admin")
    public void getMatchCommentsByMatchNewsId(){
    	try {
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/matchNews/getMatchCommentsByMatchNewsId")
    				.param("matchNewsId", "376")
    				.param("page", "1")
    				.param("num", "50");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    @WithMockUser(username="admin",password="admin")
    public void getMatchCommentsByArticleId(){
    	try {
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/matchNews/getMatchCommentsByArticleId")
    				.param("articleId", "260524")
    				.param("page", "1")
    				.param("num", "50");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
}
