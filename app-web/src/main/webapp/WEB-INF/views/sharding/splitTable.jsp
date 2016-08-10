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
						<input id="power" name="power" type="text" class="form-control" placeholder="数据仓储为2^X的数据填充，数据产生的方式为Twitter-Snowflake,值为1~12"
						 	data-bv-notempty data-bv-notempty-message="不能为空">
						<span class="input-group-btn">  
		                    <button id="initDataBtn" type="button" data-toggle="collapse" data-target="rawDataPanel" class="btn btn-primary">初始化</button>
		                </span>
					</div>
				</div>
<!-- 				错误提示 -->
				<div class="form-group">
					<label class="col-sm-2 control-label">&nbsp;</label>
					<div class="input-group">  
						<span id="powerError"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div id="rawDataPanel" class="panel panel-default col-sm-10 hide">
						<div class="panel-body" id="rawDataPanelBody" style="max-height: 200px;overflow: auto;"></div>
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>分　表</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label">分表数量：</label>
					<div class="input-group">  
						<input id="splitTableNum" name="splitTableNum" disabled="disabled" type="text" class="form-control" placeholder="分表数量,值为1~10" >
						 <span class="input-group-btn">  
		                    <button id="splitTableBtn" type="button"  class="btn btn-primary" disabled="disabled" >分　表</button>
		                </span>
					</div>
				</div>
				<!-- 				错误提示 -->
				<div class="form-group">
					<label class="col-sm-2 control-label">&nbsp;</label>
					<div class="input-group">  
						<span id="splitTableNumError"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">&nbsp;</label>
					<div class="col-sm-10" id="tableList" style="padding:0px;">
						
					</div>
				</div>
			</fieldset>
			<fieldset>
				<legend>验　证</legend>
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="input-group">  
		                <input id="searchInput" type="text" class="form-control" placeholder="查询你想要的" disabled="disabled">  
		                <span class="input-group-btn">  
		                    <button id="searchBtn" class="btn btn-primary" type="button" disabled="disabled">查　询</button>  
		                </span>  
		            </div>  
				</div>
				
				<div class="form-group">
					<label class="col-sm-2 control-label"></label>
					<div class="panel panel-default col-sm-10 hide" id="searchResultPanel">
						<div  class="panel-body hide center"></div>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
	<script type="text/javascript">
		require(["splitTable"],function(splitTable){
			splitTable.initPage();
		});
	</script>
</div>
