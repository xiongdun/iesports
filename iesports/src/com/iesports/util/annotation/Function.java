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
 * 描述：功能号描述
 * @author xiongdun
 * @created 2016年10月28日 上午9:23:24
 * @since 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Function {
	
	public String value() default "";
}
