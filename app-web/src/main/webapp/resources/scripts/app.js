requirejs.config({
    baseUrl: appPath + "/resources/scripts/app",
    paths:{
    	"jquery":"../lib/jquery.min",
    	"bootstrap":"../lib/bootstrap.min",
    	"pjax":"../lib/jquery.pjax.min",
    	"NProgress":"../lib/nprogress.min"
    },
    shim:{
    	pjax:["jquery"]
    }
});
require(["header"],function($){});
