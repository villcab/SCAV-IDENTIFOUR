package com.firstonesoft.mu.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.firstonesoft.mu.controller.TimeOutController;
import com.firstonesoft.mu.ed.NodoCliente;
import com.firstonesoft.util.Parameters;

public class LoginFilter implements Filter {
	
	private static String systemName = Parameters.system_name;

	private FilterConfig filterConfig;
	
	private static final String pathRaiz = "/" + systemName + "/";
	private static final String pathLogin = "/" + systemName + "/view/mu_login.xhtml";
	private static final String rederingMenu = "/" + systemName + "/view/mu_menu.xhtml";
	
	@Inject
	private TimeOutController timeOutController;

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
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

		if (usuario != null) {
			String addressIP = request.getRemoteAddr();
			String addressUser = timeOutController.getDireccionIp(usuario);

			if (!addressIP.equals(addressUser)) {
				session.setAttribute("TEMP$USER_NAME", "");
				session.setAttribute("TEMP$GROUP", "");
				session.invalidate();
				HttpServletResponse hres = (HttpServletResponse) response;
				hres.sendRedirect(pathRaiz);
			} else {
				NodoCliente nd = timeOutController.getNodoCliente(usuario);
				String pageRequest = path;
				int k = pageRequest.lastIndexOf("/");
				String strPg = pageRequest.substring(k + 1);
				if (nd.existeUrl(strPg)) {
					long tp = session.getLastAccessedTime();
					timeOutController.setDatos(usuario, tp);
					chain.doFilter(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(pathRaiz);
					dispatcher.forward(request, response);
				}
			}

		} else {
			long timeMax = session.getMaxInactiveInterval() * 1000;
			timeOutController.registerOutTime(timeMax);
			session.setAttribute("TEMP$ACTION_MESSAGE_ID", "");
			session.setAttribute("TEMP$USER_NAME", "");
			session.setAttribute("TEMP$GROUP", "");
			session.invalidate();
			HttpServletResponse hres = (HttpServletResponse) response;
			hres.sendRedirect(pathRaiz);
		}

	}
	
	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("LoginFilter()");
		}
		StringBuffer sb = new StringBuffer("LoginFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

}
