package com.howard.datasource.common.aop;

import java.lang.reflect.Method;


import com.howard.datasource.common.DatabaseContextHolder;
import com.howard.datasource.common.DatabaseType;
import com.howard.datasource.common.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


/**
 * 数据源拦截
 * @author yaohongwu
 *
 */
@Aspect
@Component
public class DataSourceAspect {

	/**
	 使用空方法定义切点表达式
	*/
	@Pointcut("execution(public * com.howard.datasource.service.impl.*.*(..))")
	public void declareJointPointExpression() {
	}

	/**
	 * 拦截目标方法,通过注解来获取数据源名称key值，设置到ThreadLocal中
	 * @param joinPoint
	 */
	@Before("declareJointPointExpression()")
	public  void intercept(JoinPoint joinPoint) {
		Class<?> target = joinPoint.getTarget().getClass();
	    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	    // 使用目标注解类型，如果没有，则使用其接口指定类型
	    for (Class<?> clazz : target.getInterfaces()) {
	    	resolveDataSource(clazz, signature.getMethod());
	    }
	    resolveDataSource(target, signature.getMethod());

	}
	
	/**
     * 获取目标对象方法注解和类型注解中的注解
     */
	private void resolveDataSource(Class<?> clazz, Method method) {

		//标记是否有自定义注解数据源标识
		boolean flag = false;
	    try {
	      Class<?>[] types = method.getParameterTypes();
	      // 默认使用类型注解
	      if (clazz.isAnnotationPresent(DataSource.class)) {
	    	  DataSource ds = clazz.getAnnotation(DataSource.class);
			  DatabaseContextHolder.setDatabaseType(Enum.valueOf(DatabaseType.class,ds.value()));
	          flag = true;
	      }
	      // 方法注解覆盖，以方法注解为最后值
	      Method m = clazz.getMethod(method.getName(), types);
	      if (m != null && m.isAnnotationPresent(DataSource.class)) {
	    	  DataSource ds = m.getAnnotation(DataSource.class);
			  DatabaseContextHolder.setDatabaseType(Enum.valueOf(DatabaseType.class,ds.value()));
	    	  flag = true;
	      }

	      //没有自定义注解 默认数据源标识
	      if (!flag) {
			  DatabaseContextHolder.setDatabaseType(DatabaseType.datasource1);
	      }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
