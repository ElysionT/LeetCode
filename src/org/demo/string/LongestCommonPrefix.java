package org.demo.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 最长公共前缀
 * https://leetcode.cn/leetbook/read/array-and-string/ceda1/
 * 
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * 
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class LongestCommonPrefix {
	private static final boolean DEBUG = true;

	public String longestCommonPrefix2(String[] strs) {
		int length = strs.length;
		if (0 == length) {
			return "";
		}

		String target = strs[0];
		if (1 == length) {
			return target;
		}

		int strLength = target.length();
		int max = -1;
		String str;
		for (int j = 0; j < strLength; j++) {
			char c = target.charAt(j);
			boolean isMatch = true;
			for (int k = 1; k < length; k++) {
				str = strs[k];
				if (str.length() <= j || c != str.charAt(j)) {
					isMatch = false;
					break;
				}
			}
			if (isMatch) {
				max = j;
			} else {
				break;
			}
		}
		if (-1 == max) {
			return "";
		} else {
			return target.substring(0, max + 1);
		}
	}

	public String longestCommonPrefix(String[] strs) {
		int length = strs.length;
		if (1 == length) {
			return strs[0];
		}
		Map<String, Integer> prefixs = new HashMap<>();
		for (int i = 0; i < length; i++) {
			String str = strs[i];
			int strLength = str.length();
			String prefix;
			for (int j = 0; j < strLength; j++) {
				prefix = str.substring(0, j + 1);
				if (prefixs.containsKey(prefix)) {
					continue;
				}
				int count = 0;
				for (int k = i + 1; k < length; k++) {
					if (strs[k].startsWith(prefix)) {
						count++;
					}
				}

				prefixs.put(prefix, count);
			}
		}
		if (DEBUG)
			System.out.println("prefixs:" + prefixs);
		String result = "";
		int max = 0;
		for (String prefix : prefixs.keySet()) {
			int count = prefixs.get(prefix);
			if (count > max) {
				max = count;
				result = prefix;
			} else if (count == max && 0 != count) {
				if (prefix.length() > result.length()) {
					max = count;
					result = prefix;
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		// "fl"
		// String[] strs = { "flower", "flow", "flight" };
		// ""
		// String[] strs = { "dog", "racecar", "car" };
		// "a"
		// String[] strs = { "a" };
		// ""
		// String[] strs = { "reflower", "flow", "flight" };
		// "a"
		String[] strs = { "ab", "a" };

		System.out.println("strs:" + Arrays.toString(strs));
		LongestCommonPrefix solution = new LongestCommonPrefix();
		long start = System.currentTimeMillis();
		String result = solution.longestCommonPrefix2(strs);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Result:" + result);

	}

}
