//$Id$
package com.eg;

import java.util.HashMap;
import java.util.Map;

public class NAmeCheck {

	public static void main(String[] args) {
		Map<Integer, Integer> test=new HashMap<Integer, Integer>();
		test.put(1, 1);
		test.put(1,5);
		System.out.println("ok"+test.get(1));
	}

}
