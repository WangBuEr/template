package cn.buer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Ueditor
* @ClassName: UeditorController
* @Description: 
* @author WangBuEr
* @date 2016年4月15日 下午1:54:58
*
 */
@RequestMapping("ueditor")
@Controller
public class UeditorController {
	@RequestMapping("index")
	public String index(){
		return "/ueditor/index";
	}
	@RequestMapping("ueditor")
	public String ueditor(){
		return "/ueditor/controller";
	}
}
