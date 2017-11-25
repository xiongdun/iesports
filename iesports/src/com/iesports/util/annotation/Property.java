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
 * ������
 * @author xiongdun
 * @created 2016��10��28�� ����9:23:24
 * @since 
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface Property {
	
	String editor() default "";
}
