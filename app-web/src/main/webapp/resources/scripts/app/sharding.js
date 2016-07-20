define(["jquery","ajax"],function($,ajax){
	return {
		initDataBase:function(power,callBack){
			ajax.jsonAjax(appPath + "/sharding/initData/" + power,null,function(data){
				callBack(data);
			});
		},
		splitTable:function(splitSum,callBack){
			ajax.jsonAjax(appPath + "/sharding/splitTable/" + splitSum,null,function(data){
				callBack(data);
			});
		},
		validationData:function(data,callBack){
			ajax.jsonAjax(appPath + "/sharding/validationData/" + data,null,function(data){
				callBack(data);
			});
		}
	}
});