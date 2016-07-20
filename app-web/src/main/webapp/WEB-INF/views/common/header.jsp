<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:if test="${renderHeader}">
	<title>Template</title>
	<link href="//apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="//cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
	<link href="//cdn.bootcss.com/nprogress/0.2.0/nprogress.min.css" rel="stylesheet">
	<link href="${contextPath}/resources/styles/style.css" rel="stylesheet">
	<script src="${contextPath}/resources/scripts/lib/require.min.js"></script>
	<script type="text/javascript">
		var appPath = "${contextPath}";
		requirejs.config({
		    baseUrl: appPath + "/resources/scripts/app",
		    paths:{
		    	"jquery":"../lib/jquery.min",
		    	"bootstrap":"../lib/bootstrap.min",
		    	"pjax":"../lib/jquery.pjax.min",
		    	"NProgress":"../lib/nprogress.min"
		    },
		    shim:{
		    	pjax:["jquery"],
		    	bootstrap:["jquery"]
		    }
		});
		require(["header","bootstrap"],function($){});
	</script>
	<header class="header">
		<nav class="navbar">
			<div class="container">
				<div class="navbar-header">
					<a href="${contextPath}/index" class="navbar-brand logo" data-pjax>Template</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li><a href="${contextPath}/index" data-pjax>首页</a></li>
						<li><a href="${contextPath}/sharding/splitTable" data-pjax>分表</a></li>
<%-- 						<li><a href="${contextPath}/qiniu/index" data-pjax>多库</a></li> --%>
<%-- 						<li><a href="${contextPath}/qiniu/index" data-pjax>多库多表</a></li> --%>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</c:if>