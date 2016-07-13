package cn.buer.util.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.buer.util.aspect.annotation.ErrorHandling;
/**
 * 
* @ClassName: ErrorHandlingAspect
* @Description: 错误日志切面
* @author WangBuEr
* @date 2016年3月30日 下午1:34:40
*
 */
@Component
@Aspect
public class ErrorHandlingAspect {
	 private static final Logger LOG = LoggerFactory.getLogger(ErrorHandlingAspect.class);
	@Pointcut("@annotation(cn.buer.util.aspect.annotation.ErrorHandling)")
	private void anyMethod() {
	};
	/**
	 * 
	* @Title: afterThrowing 
	* @Description: 错误通知
	* @param @param point
	* @param @param error
	* @param @throws Exception    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@AfterThrowing(pointcut = "anyMethod()", throwing = "error")
	public void afterThrowing(JoinPoint point, Throwable error) throws Exception {
		ErrorHandlingAspect.Log log = getLog(point);
		log.setExceptionCode(error.getClass().getName());
		log.setExceptionDetail(error.getMessage());
		LOG.error("{}",log);
	}
	/**
	 * 
	* @Title: getLog 
	* @Description: 获取日志
	* @param @param point
	* @param @return
	* @param @throws Exception    设定文件 
	* @return ErrorHandlingAspect.Log    返回类型 
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	private ErrorHandlingAspect.Log getLog(JoinPoint point) throws Exception {
		String targetName = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		Class<?> targetClass = Class.forName(targetName);
		Object[] params = point.getArgs();
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == params.length) {
					description = method.getAnnotation(ErrorHandling.class).value();
					break;
				}
			}
		}
		return new Log(description, targetName, methodName, params);
	}
	
	
	
	/**
	 * 
	* @ClassName: Log
	* @Description: 日志
	* @author WangBuEr
	* @date 2016年3月30日 下午1:34:16
	*
	 */
	class Log{
		/**
		 * 描述
		 */
		private String description;
		/**
		 * 类
		 */
		private String clazzs;
		/**
		 * 方法
		 */
		private String method;
		/**
		 * 参数
		 */
		private Object[] params;
		/**
		 * 异常代码
		 */
		private String exceptionCode;
		/**
		 * 异常详细信息
		 */
		private String exceptionDetail;
		
		public Log(String description, String clazzs, String method, Object[] params) {
			super();
			this.description = description;
			this.clazzs = clazzs;
			this.method = method;
			this.params = params;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getClazzs() {
			return clazzs;
		}
		public void setClazzs(String clazzs) {
			this.clazzs = clazzs;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public Object[] getParams() {
			return params;
		}
		public void setParams(Object[] params) {
			this.params = params;
		}
		public String getExceptionCode() {
			return exceptionCode;
		}
		public void setExceptionCode(String exceptionCode) {
			this.exceptionCode = exceptionCode;
		}
		public String getExceptionDetail() {
			return exceptionDetail;
		}
		public void setExceptionDetail(String exceptionDetail) {
			this.exceptionDetail = exceptionDetail;
		}
		@Override
		public String toString() {
			return "Log [description=" + description + ", clazzs=" + clazzs + ", method=" + method + ", params="
					+ Arrays.toString(params) + ", exceptionCode=" + exceptionCode + ", exceptionDetail="
					+ exceptionDetail + "]";
		}
	}
}
