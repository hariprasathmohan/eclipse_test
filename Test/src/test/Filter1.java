//$Id$
package test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter1 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("In filter");
		
		String name=request.getParameter("t1");
		
		if(name.equals("")) {
			response.getWriter().print("must enter value");
		}else
		
		chain.doFilter(request, response);
		
	}

}
