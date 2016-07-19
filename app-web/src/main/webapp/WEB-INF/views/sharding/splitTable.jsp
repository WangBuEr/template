<%@include file="../common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>  
<div id="pjax-container" class="pjax-container container">
	<div class="container panel panel-default ">
		<form action="" class="form-horizontal panel-body">
			<fieldset>
				<legend>初始化</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label">数据仓储：</label>
					<div class="input-group">  
						<input type="text" class="form-control" placeholder="数据仓储为2^X的数据填充，数据产生的方式为Twitter-Snowflake">
						<span class="input-group-btn">  
		                    <button id="initDataBtn" type="button" class="btn btn-primary">初始化</button>
		                </span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div id="rawDataDiv" class="collapse" >
					  	adfsdf
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>分　表</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label">分表数量：</label>
					<div class="input-group">  
						<input type="text" class="form-control" placeholder="分表数量" disabled="disabled">
						 <span class="input-group-btn">  
		                    <button type="button" class="btn btn-primary" disabled="disabled">分　表</button>
		                </span>
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>验　证</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="input-group">  
		                <input type="text" class="form-control" placeholder="查询你想要的" disabled="disabled">  
		                <span class="input-group-btn">  
		                    <button class="btn btn-primary" type="button" disabled="disabled">查　询</button>  
		                </span>  
		            </div>  
				</div>
			</fieldset>
		</form>
	</div>
</div>
<script type="text/javascript">
	require(["sharding"],function(sharding){
		$("#initDataBtn").on("click",function(){
			$(this).text("初始化中...").attr("disabled",true);
			sharding.initDataBase(6,function(rawData){
				$("#rawDataDiv").text(rawData.allData.join(","));
			});
		});
		
	});
</script>