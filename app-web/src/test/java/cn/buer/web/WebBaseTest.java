package cn.buer.web;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
	@ContextConfiguration(name = "parent",locations = "classpath:spring/spring-service.xml"),
	@ContextConfiguration(name = "child",locations = "classpath:spring/spring-mvc.xml")
})
public class WebBaseTest {
	@Resource
	protected WebApplicationContext wac;  
	protected MockMvc mockMvc;
	@Before
	public void before(){
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
}
