define(["jquery","NProgress","pjax"],function($,NProgress,pjax){
	$(function(){
		$(document).pjax("a[data-pjax]","#pjax-container",{fragment:"#pjax-container",cache:false});
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
});
