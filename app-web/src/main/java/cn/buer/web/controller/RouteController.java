package cn.buer.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
	@RequestMapping("index")
	public String index(HttpServletRequest request){
		System.out.println("132");
		return "index";
	}
	@RequestMapping("home")
	public String home(){
		return "home";
	}
	@RequestMapping("add")
	public String add(HttpServletRequest request){
		return "add";
	}
	@RequestMapping("list")
	public String list(){
		return "list";
	}
}
