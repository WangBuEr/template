define(["jquery","bootstrap"],function($){
	return {
		error:"错误",
		errorOpen:function(body){
			this.open(this.error,body);
		},
		open:function(title,body){
			if(!title){
				title = "提示";
			}
			$("#tipsModalTitle").text(title);
			$("#tipsModalBody").text(body);
			$("#tipsModal").modal("show");
		}
	}
});