package com.supinfo.supcommerce.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/auth/*")
public class AuthentificateFilter implements Filter{

	private FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		if(req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) req;
			HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
			
			if(httpServletRequest.getSession().getAttribute("username") == null) {
				httpServletResponse.sendRedirect(config.getServletContext().getContextPath() + "/login.html");
				return;
			}
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		this.config = null;
	}
	
}
