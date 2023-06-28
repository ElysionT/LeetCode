package org.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

	public static String[] getStringArrayFromFile(String fileName, String regex) throws IOException {
		final String[] result = getStringFromFile(fileName).split(regex);
		System.out.println("Length:" + result.length);
		return result;
	}

	public static String getStringFromFile(String fileName) throws IOException {
		String dir = new File("").getAbsolutePath();
		File file = new File(dir, fileName);
		System.out.println("File:" + file);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String str = "";
		try {
			String temp;
			while ((temp = bufferedReader.readLine()) != null) {
				str += temp;
			}
			// System.out.println(str);
		} finally {
			if (null != bufferedReader)
				bufferedReader.close();
		}
		return str;
	}

}
