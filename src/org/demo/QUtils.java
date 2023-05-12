package org.demo;

public class QUtils {

	public static void arrayToString(int[] nums) {
		System.out.print("[");
		for (int i = 0, limit = nums.length - 1; i <= limit; i++) {
			System.out.print(nums[i]);
			if (i != limit) {
				System.out.print(",");
			}
		}
		System.out.println("]");
	}
	
	

}
