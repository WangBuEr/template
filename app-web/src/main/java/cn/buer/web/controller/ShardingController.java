package cn.buer.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.buer.web.service.ShardingService;
import cn.buer.web.vo.AjaxDataResult;

@Controller
@RequestMapping("sharding")
public class ShardingController {
	@Resource
	private ShardingService splitTableService;
	@RequestMapping("index")
	private String index(HttpSession session){
		return "/sharding/index";
	}
	@RequestMapping("splitTable")
	public String splitTable(){
		return "/sharding/splitTable";
	}
	@RequestMapping("initData/{pow}")
	@ResponseBody
	public AjaxDataResult initData(@PathVariable("pow")Integer pow){
		if(1 > pow && 12 > pow){
			return AjaxDataResult.paramError(null);
		}
		return AjaxDataResult.success(splitTableService.initData(pow));
	}
	@RequestMapping("splitTable/{splitTableNum}")
	@ResponseBody
	public AjaxDataResult splitTable(@PathVariable("splitTableNum")Integer splitSum){
		if(1 > splitSum && 12 > splitSum){
			return AjaxDataResult.paramError(null);
		}  
		return AjaxDataResult.success(splitTableService.splitTable(splitSum));
	}
	@RequestMapping("validationData/{data}")
	@ResponseBody
	public AjaxDataResult validationData(@PathVariable("data")String data){
		return AjaxDataResult.success(splitTableService.validationData(data));
	}
}
