define(["jquery"],function($){
	return {
		jsonAjax:function(url,data,successCallBack,errorCallBack){
			var result = null;
			$.ajax({
				url:url,
				method:"post",
				data:data,
				dataType:"JSON",
				async:true,
				cache:false,
				success:function(data){
					if(data.code == "10000"){
						successCallBack(data.data);
					}else if(errorCallBack){
						errorCallBack(data);
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