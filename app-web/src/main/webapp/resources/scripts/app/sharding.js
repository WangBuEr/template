define(["jquery","ajax"],function($,ajax){
	return {
		initDataBase:function(power,callBack){
			ajax.jsonAjax(appPath + "/sharding/initData/" + power,null,function(data){
				callBack(data);
			});
		}
	}
});