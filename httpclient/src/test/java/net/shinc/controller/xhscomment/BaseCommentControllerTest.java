package net.shinc.controller.xhscomment;


import java.util.ArrayList;
import java.util.List;

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

/**
 * @ClassName CourseControllerTest 
 * @Description TODO
 * @author guoshijie 
 * @date 2015年8月3日 下午8:55:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = InfoMgmtApplication.class)
@WebAppConfiguration
public class BaseCommentControllerTest {

	@Autowired  
    private WebApplicationContext wac;  
    private MockMvc mockMvc;
    private ResultHandler handler;
    
    @Before  
    public void init(){  
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build(); 
    	handler = MockMvcResultHandlers.print();
    } 
    
    @Test
    @WithMockUser(username="admin",password="admin")
    public void getCategory(){
    	try {
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/basecomment/getCategory")
    				.param("name", "语文").param("shortName", "语");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    @WithMockUser(username="admin",password="admin")
    public void addCategory(){
    	try {
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/basecomment/addCategory")
    				.param("name","足球")
    				.param("parent","6");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    @Test
    @WithMockUser(username="admin",password="admin")
    public void queryRemoteComment(){
    	try {
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/basecomment/queryRemoteComment")
    				.param("type","weibo")
    				.param("queryType","3")
    				.param("content", "3885183818367115");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    @Test
    @WithMockUser(username="admin",password="admin")
    public void addComment(){
    	try {
    		List list = new ArrayList();
    		list.add("aa");
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/basecomment/addComment")
    				.param("categoryId","1").param("commentList", "a").param("commentList", "b");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    @Test
    @WithMockUser(username="admin",password="admin")
    public void queryBaseComment(){
    	try {
    		List list = new ArrayList();
    		list.add("aa");
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/basecomment/queryBaseComment")
    				.param("categoryId","1").param("num", "1");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    @WithMockUser(username="admin",password="admin")
    public void commentIt(){
    	try {
    		List list = new ArrayList();
    		list.add("aa");
    		RequestBuilder reqbuild = MockMvcRequestBuilders.post("/basecomment/commentIt")
    				.param("articleId","1").param("commentList[0].comment", "%啊").param("commentList[1].comment", "comment1");
    		mockMvc.perform(reqbuild).andDo(handler);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
}
