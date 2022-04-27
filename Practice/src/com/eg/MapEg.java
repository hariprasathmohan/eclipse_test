//$Id$
package com.eg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapEg {
	public static void main(String[] args) {
		Map<String,String> mbl=new HashMap<>();
		mbl.put("mi", "hari");
		mbl.put("mac", "hari1");
		mbl.put("oneplus", "hari2");
		mbl.put("moto", "hari3");
		
		Set<String> keys =mbl.keySet();
		for(String s:keys) System.out.println(s+" = "+mbl.get(s));
		
		Set<Map.Entry<String, String>> values=mbl.entrySet();
		for(Map.Entry<String, String> s:values) System.out.println(s.getKey()+" = "+s.getValue());
	}
}
