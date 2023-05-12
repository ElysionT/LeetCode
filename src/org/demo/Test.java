package org.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println("----------------");
		System.out.println((System.currentTimeMillis()-start));
		
		Map<String, Integer> cache = new HashMap<>();
		int[] a =  {1,2,3,5};
		
		ArrayList<Integer> result = (ArrayList<Integer>) Arrays.asList(1,3,6,8,4);
		ArrayList<Integer> result11 = (ArrayList<Integer>) Arrays.asList(1,3);
		result.removeAll(result11);
		Collections.sort(result);
		System.out.println(result);
		
		

	}

}
