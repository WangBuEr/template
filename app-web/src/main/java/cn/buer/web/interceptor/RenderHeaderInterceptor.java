package cn.buer.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.dubbo.common.utils.StringUtils;
/**
 * 是否渲染头部拦截器
 * @author WangBuEr
 *
 */
public class RenderHeaderInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(StringUtils.isEmpty(request.getHeader("X-PJAX"))){
			request.setAttribute("renderHeader", true);
		}
	}
	
}
