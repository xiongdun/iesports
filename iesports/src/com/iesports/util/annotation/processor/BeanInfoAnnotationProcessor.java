/**
 * 
 */
package com.iesports.util.annotation.processor;

import java.beans.Introspector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic.Kind;

import org.apache.log4j.Logger;

import com.iesports.util.annotation.Property;

/**
 * 描述：这个类使用来解析注解属性的
 * @author xiongdun
 * @created 2016年11月9日 下午3:14:53
 * @since 
 */
@SupportedAnnotationTypes("sourceAnnotations.Property")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class BeanInfoAnnotationProcessor extends AbstractProcessor {

	private static Logger logger = Logger.getLogger(BeanInfoAnnotationProcessor.class);
	
	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		
		for (TypeElement typeElement : annotations) {
			Map props = new LinkedHashMap();
			String beanClassName = null;
			for (Element e : roundEnv.getElementsAnnotatedWith(typeElement)) {
				String mName = e.getSimpleName().toString();
				String[] prefixes = { "get", "set", "is" };
				boolean found = false;
				for (int i = 0; !found & i < prefixes.length; i++) {
					if (mName.startsWith(prefixes[i])) {
						found = true;
						int start = prefixes[i].length();
						String name = Introspector.decapitalize(mName.substring(start));
						props.put(name, e.getAnnotation(Property.class));
					}
				}
				if (!found) {
					processingEnv.getMessager().printMessage(Kind.ERROR, "@Propery must be applied to getXXX,setXXX, or isXXX Method", e);
					
				} else if (beanClassName == null) {
					beanClassName = ((TypeElement) e.getEnclosedElements()).getQualifiedName().toString();
				}
			}
			try {
				if (beanClassName != null ) {
					writeBeanInfoFile(beanClassName, props);
				}
			} catch (IOException e) {
				logger.error(e);
			} 
			
		}
		
		return true;
	}
	
	/**
	 * 描述：
	 * @author xiongdun
	 * @created 2016年11月9日 下午3:54:42
	 * @since 
	 * @param beanClassName
	 * @param props
	 * @throws IOException 
	 */
	private void writeBeanInfoFile(String beanClassName, Map props) throws IOException {
		JavaFileObject sourceObject = processingEnv.getFiler().createSourceFile(beanClassName + "BeanInfo");
		PrintWriter out = new PrintWriter(sourceObject.openWriter());
		int i = beanClassName.lastIndexOf(".");
		if (i > 0) {
			out.print("package ");
			out.print(beanClassName.substring(0, i));
			out.print(";");
		}
		
	}
	
}
