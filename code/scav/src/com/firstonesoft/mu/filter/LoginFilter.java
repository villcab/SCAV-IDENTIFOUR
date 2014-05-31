package com.firstonesoft.mu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.firstonesoft.util.Parameters;

public class LoginFilter implements Filter {
	
	private static String systemName = Parameters.system_name;
	
	private static final String pathRaiz = "/" + systemName + "/";
	private static final String pathLogin = "/" + systemName + "/view/mu_login.xhtml";
	public static final String rederingMenu = "/" + systemName + "/view/mu_index.xhtml";

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//HttpSession session = ((HttpServletRequest) request).getSession();
		HttpServletRequest req = (HttpServletRequest) request;

		String path = req.getRequestURI();
		String usuario = (String) req.getSession().getAttribute("TEMP$USER_NAME");
		
		if (path.equals(pathRaiz)) {
			if (usuario == null)
				chain.doFilter(request, response);
			else {
				HttpServletResponse hres = (HttpServletResponse) response;
				hres.sendRedirect(rederingMenu);
			}
			return;
		}

		if (path.equals(pathLogin)) {
			if (request.getContentLength() != -1)
				chain.doFilter(request, response);
			else {
				HttpServletResponse hres = (HttpServletResponse) response;
				hres.sendRedirect(pathRaiz);
			}
			return;
		}
		
		if (usuario == null) {
			HttpServletResponse hres = (HttpServletResponse) response;
			hres.sendRedirect(pathRaiz);
		} else {
			chain.doFilter(request, response);
		}

	}

}
