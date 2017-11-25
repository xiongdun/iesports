/**
 * 
 */
package com.iesports.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述：事件监听
 * @author xiongdun
 * @created 2016年10月28日 上午9:23:24
 * @since 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ActionListenerFor {
	
	String source();
	
	String assingedTo() default "[none]";
	
	int serverity() default 0;
	
	enum Status {
		UNCONFIRMED,
		CONFIRMED,
		FIXED,
		NOTBUG
	}
	
	boolean showStopper() default false;
	
	Class<?> testCase() default Void.class;
	
	Status status() default Status.FIXED;
	
	String[] reportedBy();
	
	
}
