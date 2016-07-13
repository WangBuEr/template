package cn.buer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {
	private  int  counter = 0;
	@RequestMapping("addCounter")
	@ResponseBody
	public int addCounter(){
		return ++counter;
	}
}
