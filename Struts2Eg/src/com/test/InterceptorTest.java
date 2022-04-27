//$Id$
package com.test;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class InterceptorTest extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Thread.sleep(3000);
		
		String output = "Pre-Processing"; 
	      System.out.println(output);
	      
	      Thread.sleep(3000);

	      String result = invocation.invoke();
	      System.out.println(result);	
	      
	      Thread.sleep(8000);

	      output = "Post-Processing";
	      System.out.println(output);

	      return "input";
	}

}
