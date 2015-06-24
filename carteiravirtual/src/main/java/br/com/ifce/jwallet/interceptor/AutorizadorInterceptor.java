package br.com.ifce.jwallet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
	throws Exception {
		
		String uri = request.getRequestURI();
		if(uri.endsWith("logon") || uri.endsWith("login") || uri.contains("resource") || uri.contains("novousuario") || uri.contains("usuario/adicionar")|| uri.contains("usuario/adicionado")) {
			return true;
		}
		
		if(request.getSession().getAttribute("usuarioLogado")!=null) {
			return true;
		} else {
			response.sendRedirect("/carteiravirtual/logon");
			
			return false;
		}
		
	}
}
