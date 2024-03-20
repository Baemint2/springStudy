package com.gdu.prj.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class ContactFilter
 */
@WebFilter("*.do")
public class ContactFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	  HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    
    req.setCharacterEncoding("UTF-8");
    
    // 요청 방식 확인 + 요청 주소 확인
    System.out.print(String.format("%-4s", req.getMethod()));
    System.out.print(" | ");
    System.out.println(req.getRequestURI());
    
    // 요청 파라미터 확인
    Map<String, String[]> params = req.getParameterMap();
    for(Map.Entry<String, String[]> param : params.entrySet()) {
      System.out.print(String.format("%7s", " "));
      System.out.print(String.format("%-10s", param.getKey()) + " : ");
      System.out.println(Arrays.toString(param.getValue()));
    }
    
    chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */


}
