/**
 * 
 */
package com.carport.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * ��������¼���ˣ�����ƽ̨��¼���û������¼���ܷ���web��Ŀ
 * @author zhangyijie
 * @created 2016��12��14�� ����9:40:50
 * @since 
 */
public class LoginFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(Logger.class);

	@Override
	public void destroy() {
		logger.info("***************��¼��������******************");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		/** 
		 * 1,doFilter�����ĵ�һ������ΪServletRequest���󡣴˶�����������ṩ�˶Խ������Ϣ������* 
		 * �����ݡ�cookie��HTTP����ͷ������ȫ���ʡ��ڶ�������ΪServletResponse��ͨ���ڼ򵥵Ĺ�* 
		 * �����к��Դ˲��������һ������ΪFilterChain���˲�����������servlet��JSPҳ�� 
		 */
		HttpServletRequest request = (HttpServletRequest) servletRequest;  
        /** 
         * �������HTTP���󣬲�����Ҫ��������getHeader��getCookies����ServletRequest��* 
         * �޷��õ��ķ�������Ҫ�Ѵ�request�������HttpServletRequest 
         */  
        HttpServletResponse response = (HttpServletResponse) servletResponse;  
        String currentURL = request.getRequestURI();  
        // ȡ�ø�Ŀ¼����Ӧ�ľ���·��:  
        String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());  
        // ��ȡ����ǰ�ļ������ڱȽ�  
        HttpSession session = request.getSession(false);  
        if (!"/user/tologin".equals(targetURL)) {
        	// �жϵ�ǰҳ�Ƿ����ض����Ժ�ĵ�¼ҳ��ҳ�棬����ǾͲ���session���жϣ���ֹ������ѭ��  
            if (session == null || session.getAttribute("user") == null) {  
                // *�û���¼�Ժ����ֶ����session  
                logger.info("request.getContextPath()=" + request.getContextPath());  
                response.sendRedirect(request.getContextPath() + "/user/tologin");  
                // ���sessionΪ�ձ�ʾ�û�û�е�¼���ض���/user/tologinҳ��  
                return;  
            }  
        }
        // ����filter����������ִ��
		chain.doFilter(servletRequest, servletResponse);
		/** 
         * ����FilterChain�����doFilter������Filter�ӿڵ�doFilter����ȡһ��FilterChain������* Ϊ�� 
         * ��һ���������ڵ��ô˶����doFilter����ʱ��������һ����صĹ����������û����* 
         * һ����������servlet��JSPҳ���������servlet��JSPҳ�汻��� 
         */
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info("***************��¼���س�ʼ��******************");
	}
}
