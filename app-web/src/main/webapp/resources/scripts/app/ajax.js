define(["jquery","tipsModal"],function($,modal){
	return {
		jsonAjax:function(url,param,successCallBack,errorCallBack){
			var result = null;
			$.ajax({
				url:url,
				method:"post",
				data:param,
				dataType:"json",
				async:true,
				cache:false,
				success:function(data){
					if(data.code == "10000"){
						successCallBack(data.data);
					}else if(errorCallBack){
						errorCallBack(data);
					}else{
						var body = null;
						if(data.code == "10001"){
							body = data.message?data.message:"服务器开会小差，请稍后重试！";
						}else if(data.code = "10002"){
							body = data.message?data.message:"非法参数！";
						}
						modal.errorOpen(body);
					}
				},
				error:function(req, textStatus, errorThrown){
					//TODO  404 500 等错误统一处理
					console.error(req);
				}
			});
		}
	}
});