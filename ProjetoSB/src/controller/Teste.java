package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Teste {
	public static void main(String[] args) {
		List<Integer> ids = new ArrayList<Integer>();
		
		ids.add(1);
		
		if (ids.contains(1))
			System.out.println(ids.indexOf(1));
		else
			System.out.println("N");
	}
}
