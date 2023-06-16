package org.demo.medium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.demo.QUtils;

/**
 * 1177. 构建回文串检测
 * https://leetcode.cn/problems/can-make-palindrome-from-substring/
 * 
 * 给你一个字符串 s，请你对 s 的子串进行检测。
 * 每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多 k 项替换成任何小写英文字母。
 * 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
 * 返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。
 * 注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k = 2，我们只能替换其中的两个字母。
 * （另外，任何检测都不会修改原始字符串s，可以认为每次检测都是独立的）
 * 
 * 示例：
 * 输入：s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
 * 输出：[true,false,false,true,true]
 * 解释：
 * queries[0] : 子串 = "d"，回文。
 * queries[1] : 子串 = "bc"，不是回文。
 * queries[2] : 子串 = "abcd"，只替换 1 个字符是变不成回文串的。
 * queries[3] : 子串 = "abcd"，可以变成回文的 "abba"。 也可以变成 "baab"，先重新排序变成 "bacd"，然后把 "cd" 替换为 "ab"。
 * queries[4] : 子串 = "abcda"，可以变成回文的 "abcba"。
 * 
 * 提示：
 * 1 <= s.length, queries.length <= 10^5
 * 0 <= queries[i][0] <= queries[i][1] < s.length
 * 0 <= queries[i][2] <= s.length
 * s 中只有小写英文字母
 */
public class Q1177_CanMakePalindromeQueries {
	private static final Boolean DEBUG = false;

	// Number of lowercase English alphabet.
	private static final int SIZE = 26;
	// 97 is the value of 'a' in ASCII
	private static final int OFFSET = 97;

	private int[][] mCharacterCountArray;

	public List<Boolean> canMakePaliQueries4(String s, int[][] queries) {
		int length = s.length();
		mCharacterCountArray = new int[length][SIZE];
		int[] characterCount = new int[SIZE];
		int index;
		long start1 = 0;
		if (DEBUG)
			start1 = System.currentTimeMillis();

		for (int i = 0; i < length; i++) {
			characterCount = Arrays.copyOf(characterCount, SIZE);

			index = s.charAt(i) - OFFSET;
			characterCount[index] += 1;

			mCharacterCountArray[i] = characterCount;
		}
		if (DEBUG)
			System.out.println("Time1:" + (System.currentTimeMillis() - start1));

		List<Boolean> result = new ArrayList<>(queries.length);
		boolean isPlural;
		int singularCount;
		int start, end, k;
		if (DEBUG)
			start1 = System.currentTimeMillis();
		for (int[] temp_ : queries) {
			start = temp_[0];
			end = temp_[1];
			k = temp_[2];
			isPlural = (0 == ((end - start + 1) % 2));
			singularCount = canMakePalindromeQueriesInternal4(start, end);

			if (DEBUG)
				System.out.println("start:" + start + ", end:" + end + ", k:" + k + ", isPlural:" + isPlural
						+ ", singularCount:" + singularCount);

			result.add(((singularCount - (isPlural ? 0 : 1)) / 2) <= k);
		}
		if (DEBUG)
			System.out.println("Time2:" + (System.currentTimeMillis() - start1));

		return result;
	}

	private int canMakePalindromeQueriesInternal4(int start, int end) {
		int length = end - start + 1;
		int[] exclude = (0 == start ? new int[26] : mCharacterCountArray[start - 1]);
		int[] all = mCharacterCountArray[end];

		int singularCount = 0;
		for (int i = 0, value = 0, valueA = 0, count = 0; i < SIZE && count <= length; i++) {
			valueA = all[i];
			if (0 == valueA)
				continue;

			value = valueA - exclude[i];
			if (0 != value) {
				count++;
				if (0 != value % 2) {
					singularCount++;
				}
			}
		}

		return singularCount;
	}

	private static final Integer ZERO = new Integer(0);

	private List<Map<Character, Integer>> mCharacterCount;

	public List<Boolean> canMakePaliQueries3(String s, int[][] queries) {
		int length = s.length();
		mCharacterCount = new ArrayList<>(length);
		Map<Character, Integer> temp = new HashMap<>();
		Character key;
		Integer value;
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < length; i++) {
			key = Character.valueOf(s.charAt(i));
			value = temp.get(key);
			if (null == value) {
				temp.put(key, 1);
			} else {
				temp.replace(key, ++value);
			}
			mCharacterCount.add(new HashMap(temp));
		}
		System.out.println("Time1:" + (System.currentTimeMillis() - start1));

		List<Boolean> result = new ArrayList<>(queries.length);
		boolean isPlural;
		int singularCount;
		int start, end, k;
		start1 = System.currentTimeMillis();
		for (int[] temp_ : queries) {
			start = temp_[0];
			end = temp_[1];
			k = temp_[2];
			isPlural = (0 == ((end - start + 1) % 2));
			singularCount = canMakePalindromeQueriesInternal3(start, end);

			if (DEBUG)
				System.out.println("start:" + start + ", end:" + end + ", k:" + k + ", isPlural:" + isPlural
						+ ", singularCount:" + singularCount);

			result.add(((singularCount - (isPlural ? 0 : 1)) / 2) <= k);
		}
		System.out.println("Time2:" + (System.currentTimeMillis() - start1));

		return result;
	}

	private int canMakePalindromeQueriesInternal3(int start, int end) {
		Map<Character, Integer> exclude;
		if (0 == start) {
			exclude = new HashMap<>(0);
		} else {
			exclude = mCharacterCount.get(start - 1);
		}
		Map<Character, Integer> all = mCharacterCount.get(end);

		int singularCount = 0;
		int value;
		for (Character c : all.keySet()) {
			value = all.getOrDefault(c, ZERO) - exclude.getOrDefault(c, ZERO);
			if (0 != value && 0 != value % 2) {
				singularCount++;
			}
		}
		return singularCount;
	}

	private long mTime1 = 0, mTime2 = 0, mTime3 = 0, mTime4 = 0;

	@Deprecated
	public List<Boolean> canMakePaliQueries2(String s, int[][] queries) {
		List<Boolean> result = new ArrayList<>(queries.length);

		boolean isPlural;
		int singularCount;
		int start, end, k;
		for (int[] temp : queries) {
			start = temp[0];
			end = temp[1];
			k = temp[2];
			isPlural = (0 == ((end - start + 1) % 2));
			singularCount = canMakePalindromeQueriesInternal2(s, start, end);

			if (DEBUG)
				System.out.println("start:" + start + ", end:" + end + ", k:" + k + ", isPlural:" + isPlural
						+ ", singularCount:" + singularCount);

			result.add(((singularCount - (isPlural ? 0 : 1)) / 2) <= k);
		}
		if (DEBUG)
			System.out.println("mTime1:" + mTime1 + ", mTime2:" + mTime2 + ", mTime3:" + mTime3 + ", mTime4:" + mTime4);
		return result;
	}

	@Deprecated
	private int canMakePalindromeQueriesInternal2(String s, int start, int end) {
		Map<Character, Integer> temp = new HashMap<>();
		Character key;
		Integer value;
		for (int i = start; i <= end; i++) {
			key = Character.valueOf(s.charAt(i));
			value = temp.get(key);
			if (null == value) {
				temp.put(key, 1);
			} else {
				temp.replace(key, ++value);
			}
			if (DEBUG)
				System.out.println("key:" + key + ", value:" + value + ", temp:" + temp);
		}

		int singularCount = 0;
		for (Character c : temp.keySet()) {
			if (0 != temp.get(c) % 2) {
				singularCount++;
			}
		}
		return singularCount;
	}

	@Deprecated
	public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
		int length = queries.length;
		List<Boolean> result = new ArrayList<>(length);

		String subString;
		boolean isPlural;
		int singularCount;
		int k;
		for (int[] temp : queries) {
			subString = s.substring(temp[0], temp[1] + 1);
			isPlural = (0 == subString.length() % 2);
			singularCount = canMakePalindromeQueriesInternal(subString);
			k = temp[2];

			if (DEBUG)
				System.out.println("subString:" + subString + ", isPlural:" + isPlural + ", singularCount:"
						+ singularCount + ", k:" + k);

			result.add(((singularCount - (isPlural ? 0 : 1)) / 2) <= k);
		}

		return result;
	}

	@Deprecated
	private int canMakePalindromeQueriesInternal(String s) {
		int length = s.length();
		char[] sArray = s.toCharArray();
		Map<Character, Integer> temp = new HashMap<>();
		char a;
		for (int i = 0; i < length; i++) {
			a = sArray[i];
			if (temp.containsKey(a)) {
				temp.put(a, temp.get(a) + 1);
			} else {
				temp.put(a, 1);
			}
		}

		int singularCount = 0;
		for (Character c : temp.keySet()) {
			if (0 != temp.get(c) % 2) {
				singularCount++;
			}
		}
		return singularCount;
	}

	public static void main(String[] args) throws IOException {
		// [true, false, false, true, true]
		String s1 = "abcda";
		int[][] queries1 = { { 3, 3, 0 }, { 1, 2, 0 }, { 0, 3, 1 }, { 0, 3, 2 }, { 0, 4, 1 } };

		Q1177_CanMakePalindromeQueries solution = new Q1177_CanMakePalindromeQueries();
		System.out.println("s1:" + s1 + ", queries1:" + Arrays.deepToString(queries1));
		long start = System.currentTimeMillis();
		List<Boolean> result = solution.canMakePaliQueries4(s1, queries1);
		System.out.println("Time:" + (System.currentTimeMillis() - start)
				+ ", [true, false, false, true, true] - Result:" + result);

		String s2 = QUtils.getStringFromFile("Q1177_S");
		final String[] stringArray = QUtils.getStringArrayFromFile("Q1177_Queries", "\\],\\[");
		int length = stringArray.length;
		int[][] queries2 = new int[length][3];
		String[] temp;
		for (int i = 0; i < length; i++) {
			temp = stringArray[i].split(",");
			queries2[i] = new int[] { Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Integer.valueOf(temp[2]) };
		}
		System.out.println("s2:" + s2); // + ", queries2:" + Arrays.deepToString(queries2));
		start = System.currentTimeMillis();
		result = solution.canMakePaliQueries4(s2, queries2);
		System.out.println("Time:" + (System.currentTimeMillis() - start)); // + ", ? - Result:" + result);

		// [true, true, true] 
		String s3 = "pqtadspgvinafefk";
		int[][] queries3 = { { 1, 14, 5 }, { 14, 15, 1 }, { 15, 15, 1 } };
		System.out.println("s3:" + s3 + ", queries3:" + Arrays.deepToString(queries3));
		start = System.currentTimeMillis();
		result = solution.canMakePaliQueries4(s3, queries3);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", [true, true, true] - Result:" + result);

		// [false, true, true, true, false, true, true, true]
		String s4 = "rkzavgdmdgt";
		int[][] queries4 = { { 5, 8, 0 }, { 7, 9, 1 }, { 3, 6, 4 }, { 5, 5, 1 }, { 8, 10, 0 }, { 3, 9, 5 },
				{ 0, 10, 10 }, { 6, 8, 3 } };
		System.out.println("s4:" + s4 + ", queries4:" + Arrays.deepToString(queries4));
		start = System.currentTimeMillis();
		result = solution.canMakePaliQueries4(s4, queries4);
		System.out.println("Time:" + (System.currentTimeMillis() - start)
				+ ", [false, true, true, true, false, true, true, true] - Result:" + result);
	}

}

class KeyValue2 {
	public char key;
	public int value;

	public KeyValue2(char key, int value) {
		this.key = key;
		this.value = value;
	}

	public void increase() {
		value++;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof KeyValue2) && ((KeyValue2) obj).key == key;
	}

}
