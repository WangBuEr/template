<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<title>Template</title>
<c:if test="${renderHeader}">
	
		<header class="header">
			<nav class="navbar">
				<div class="container">
					<div class="navbar-header">
						<a href="${contextPath}/index" class="navbar-brand logo" data-pjax>Template</a>
					</div>
					<div>
						<ul class="nav navbar-nav">
							<li><a href="${contextPath}/index" data-pjax>首页</a></li>
							<li><a href="${contextPath}/add" data-pjax>分表</a></li>
	<%-- 						<li><a href="${contextPath}/qiniu/index" data-pjax>多库</a></li> --%>
	<%-- 						<li><a href="${contextPath}/qiniu/index" data-pjax>多库多表</a></li> --%>
						</ul>
					</div>
				</div>
			</nav>
		</header>
	
	<script type="text/javascript">
		$(function(){
			$(document).pjax("a[data-pjax]","#pjax-container",{fragment:"#pjax-container"});
			$(document).on('pjax:start', function() { NProgress.start();});
			$(document).on('pjax:end',   function() { NProgress.done();});
			
			//设置当前页的选中效果
			var pathName = window.location.pathname;
			$(".navbar-nav li a[data-pjax]").each(function(i, element) {
				if($(element).attr("href") == pathName){
					$(element).addClass("navbarActive");
					return;
				}
			});
			//导航选中效果
			$(".navbar-nav li a").bind("click",function(){
				$(".navbarActive").removeClass("navbarActive");
				$(this).addClass("navbarActive");
			});
		});
</script>
</c:if>