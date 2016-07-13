package cn.buer.util.Spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class BeanUtil implements ApplicationContextAware {
	private static ApplicationContext context;
	public static Object getBean(String beanName){
		return context.getBean(beanName);
	}
	public static <T> T getBean(Class<T> clazs){
		return context.getBean(clazs);
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		BeanUtil.context = applicationContext;
	}

}
