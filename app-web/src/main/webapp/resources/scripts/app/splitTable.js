define(["sharding","pjax"],function(sharding){
	return {
		initPage:function(){
			//初始化数据仓储事件绑定
			$("#initDataBtn").on("click",function(){
				var power = $("#power").val();
				$(this).text("初始化中...").attr("disabled",true);
				//禁用数据仓库输入框
				$("#power").attr("disabled",true);
				sharding.initDataBase(power,function(rawData){
					var dataHmtl = new Array();
					$(rawData.dataList).each(function(i,data){
						dataHmtl.push("<span>"+data+"</span>")
					});
					$("#rawDataPanelBody").html(dataHmtl.join(",")).addClass("word-wrap");
					$("#rawDataPanel").removeClass("hide").addClass("show");
					$("#initDataBtn").text("隐藏数据").unbind("click").attr("disabled",false).bind("click", function() {
						if($("#rawDataPanel").is(":hidden")){
							$("#rawDataPanel").removeClass("hide").addClass("show");
							$("#initDataBtn").text("隐藏数据");
						}else{
							$("#rawDataPanel").removeClass("show").addClass("hide");
							$("#initDataBtn").text("查看数据");
						}
					});
					//启用分表输入框
					$("#splitTableNum").attr("disabled",false).focus();
					$("#splitTableBtn").attr("disabled",false);
				});
			});
			//分表事件绑定
			$("#splitTableBtn").bind("click",function(){
				var splitTableNum = $("#splitTableNum").val();
				$(this).text("分表中...").attr("disabled",true);
				sharding.splitTable(splitTableNum, function(splitTableList) {
					
					var tabelListHtml = new Array();
					tabelListHtml.push('<div class="panel-group" id="tableListPanel">');
						$(splitTableList).each(function(i, table) {
							tabelListHtml.push('<div class="panel panel-default">');
								tabelListHtml.push('<div class="panel-heading">');
									tabelListHtml.push('<a class="panel-title" data-toggle="collapse"'
											+ 'data-parent="#tableListPanel" href="#'+table.tableName+'">'+table.tableName+'</a>');
								tabelListHtml.push('</div>');
								tabelListHtml.push('<div id="'+table.tableName+'" class="panel-collapse collapse">');
									var dataHmtl = new Array();
									$(table.dataList).each(function(i,data){
										dataHmtl.push("<span>"+data+"</span>")
									});
									tabelListHtml.push('<div class="panel-body word-wrap" style="max-height: 100px;overflow: auto;">'+dataHmtl.join(",")+'</div>');
								tabelListHtml.push('</div>');
							tabelListHtml.push('</div>');
						});
					tabelListHtml.push('</div>');
					$("#tableList").html(tabelListHtml.join(""));
					$("#splitTableBtn").unbind("click").attr("disabled",false).text("隐藏数据").bind("click",function(){
						if($("#tableList").is(":hidden")){
							$("#tableList").removeClass("hide").addClass("show");
							$("#splitTableBtn").text("隐藏数据");
						}else{
							$("#tableList").removeClass("show").addClass("hide");
							$("#splitTableBtn").text("查看数据");
						}
					});
					//禁用分表输入框
					$("#splitTableNum").attr("disabled", true);
					//启用验证输入框
					$("#searchInput").attr("disabled",false).focus();
					$("#searchBtn").attr("disabled",false);
				});
			});
			//验证查询事件绑定
			$("#searchBtn").bind("click",function(){
				var data = $("#searchInput").val();
				$(this).text("查询中").attr("disabled",true);
				sharding.validationData(data,function(result){
					if(result){
						$("#searchResultPanel").text("数据被切分在" + result.tableName);
					}else{
						$("#searchResultPanel").text("查无数据！");
					}
					$("#searchResultPanel").removeClass("hide").addClass("show");
					$("#searchBtn").text("查询").attr("disabled",false);
				});
			});
		}
	}
});