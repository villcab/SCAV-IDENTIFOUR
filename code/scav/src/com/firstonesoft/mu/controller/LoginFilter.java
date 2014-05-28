package com.firstonesoft.mu.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter implements Filter {
	
//	private String encoding;
//	
//	private static String systemName = Parameters.system_name;
//
//	private FilterConfig filterConfig;
//	
//	private static final String pathRaiz = "/" + systemName + "/";
//	private static final String pathLogin = "/" + systemName + "/bean/Login.xhtml";
//	private static final String rederingMenu = "/" + systemName + "/bean/Menu.xhtml";
//	private static final String pathPrincipal = "/" + systemName + "/bean/principal.xhtml";

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
//		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

}
