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
public class CommentControllerTest {
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
    public void selectCommentJnl() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/selectCommentJnl")
    			.param("userId", "47")
    			.param("addDate", "2015-09-23")
    			.param("articleid", "267236")
    			.param("content", "残忍")
    			.param("pageIndex", "0")
    			.param("pageCount", "50");
		try {
			mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    @Test
    public void getLocalEverydayCommentsNums() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/getLocalEverydayCommentsNums");
    	try {
    		mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    @Test
    public void getTodayCommentsNums() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/getTodayCommentsNums");
    	try {
    		mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @WithMockUser(username="admin",password="admin",authorities={"adminUserList"})
    @Test
    public void getTodayxhsNumsByCategory() {
    	RequestBuilder reqbuild = MockMvcRequestBuilders.post("/getTodayxhsNumsByCategory").param("categoryid", "0");
    	try {
    		mockMvc.perform(reqbuild).andDo(MockMvcResultHandlers.print());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
}
