package controller;

import java.util.ArrayList;
import java.util.List;

public class Teste {
	public static void main(String[] args) {
		List<String> ids = new ArrayList<String>();
		ids.add("1");
		ids.add("2");
		ids.add("3");
		
		String idsS = ids.toString();
		
		System.out.println(ids);
		
		String v = idsS.substring(1, idsS.length()-1);
		String[] vs = v.split(",");
		
		System.out.println(vs[0]);
		System.out.println(vs[1].charAt(1));
		System.out.println(vs[2].charAt(1));
		
	}
}
