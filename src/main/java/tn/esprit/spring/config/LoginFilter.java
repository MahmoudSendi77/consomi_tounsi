package tn.esprit.spring.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import tn.esprit.spring.control.AuthController;


public class LoginFilter implements Filter{


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
	FilterChain filterChain) throws IOException, ServletException {
		
		
		System.err.println("aaaaa");
		
		
		
	HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
	HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
	
	//httpServletResponse.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + AuthController.token);
	System.err.println("aaaaa");
	
	AuthController employeController = (AuthController)
	httpServletRequest.getSession().getAttribute("AuthController");
	 filterChain.doFilter(servletRequest, servletResponse);
	httpServletResponse.sendRedirect(httpServletRequest.getContextPath() +
	"/login.jsf");}
	}

