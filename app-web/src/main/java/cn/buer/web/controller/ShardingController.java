package cn.buer.web.controller;

import javax.annotation.Resource;

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
	@RequestMapping("splitTable")
	public String splitTable(){
		return "/sharding/splitTable";
	}
	@RequestMapping("initData/{pow}")
	@ResponseBody
	public AjaxDataResult initData(@PathVariable("pow")Integer pow){
		return AjaxDataResult.success(splitTableService.initData(pow));
	}
	
}
