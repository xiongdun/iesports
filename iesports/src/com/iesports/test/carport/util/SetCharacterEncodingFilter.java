package com.iesports.test.carport.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ÃèÊö ×Ö·û¹ýÂËÆ÷
 * @author zhangyijie
 *
 */
public class SetCharacterEncodingFilter implements Filter {

	private String encoding = null;

	@Override
	public void destroy() {
		 encoding = null;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (encoding != null) {
			req.setCharacterEncoding(encoding);
			resp.setContentType("text/html;charset=" + encoding);
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding"); 
	}

}
