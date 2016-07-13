package cn.buer.web.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cn.buer.web.WebBaseTest;

public class TestControllerTest extends WebBaseTest{

	@Test
	public void testAddCounter() throws Exception {
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				public void run() {
					MvcResult result;
					try {
						result = mockMvc.perform(MockMvcRequestBuilders.get("/test/addCounter", "")).andReturn();
						System.out.println("counter-->" + result.getResponse().getContentAsString()); 
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}).start();
		}
	}
}
