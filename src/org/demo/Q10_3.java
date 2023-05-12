package org.demo;

import org.demo.Q10.Solution;

/**
 * 10. 正则表达式匹配
 * 
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 '.' 匹配任意单个字符 '*'
 * 匹配零个或多个前面的那一个元素 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 */
public class Q10_3 {
	static class Solution {

		int[] starArray;
		int starCount = 0;

		String ss;
		String pp;
		char[] pcArray;;

		public boolean isMatch(String s, String p) {
			starArray = null;
			starCount = 0;
			pcArray = null;
			ss = new String(s);
			pp = new String(p);
			
			if (null == s || null == p) {
				return false;
			}

			if (p.equals(".*")) {
				return true;
			}

			int pLength = p.length();
			if (0 == pLength) {
				return false;
			}

			for (int i = 0; i < pLength; i++) {
				if ('*' == p.charAt(i)) {
					starCount++;
				}
			}

			int sLength = s.length();
			if (0 == starCount) {
				if(sLength != pLength) {
					return false;
				}else {
					return match(p);
				}
			}
			
			

			int max = sLength - (pLength - starCount * 2);
			starArray = new int[starCount];

			return isMatchSub(0, max);

		}

		boolean isMatchSub(int index, int max) {
			if (starArray.length - 1 == index) {
				starArray[index] = max;
				return doFillAndMatch();
			} else {
				for (int i = 0; i <= max; i++) {
					starArray[index] = i;
					if (isMatchSub(index + 1, max - i)) {
						return true;
					}
				}
				return false;
			}
		}

		boolean doFillAndMatch() {
			pcArray = new char[ss.length()+pp.length()];
			for (int i = 0, j = 0, k = 0, length = pp.length(); i < length;) {
				char c = pp.charAt(i);
				if ('*' != c) {
					pcArray[j++] = c;
					i++;
				} else {
					int starFillSize = starArray[k++];
					if (0 == starFillSize) {
						pcArray[--j]=' ';
					} else {
						char temp = pcArray[j - 1];
						for (int m = 0; m < starFillSize - 1; m++) {
							pcArray[j++] = temp;
						}
					}
					i++;
				}
			}
//			String temp = new String(pcArray);
//			System.out.println("temp:"+temp);
			return match(new String(pcArray).trim());
		}

		boolean match(String temp) {
			System.out.println("temp:[" + temp+"] ");
			if (temp.length() != ss.length()) {
				return false;
			}
			for (int i = 0, length = ss.length(); i < length; i++) {
				char c = temp.charAt(i);
				if (c != ss.charAt(i) && '.' != c) {
					return false;
				}
			}
			return true;

		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println("(\"aa\", \"a*\") true  1:" + solution.isMatch("aa", "a*"));
		System.out.println("(\"aab\", \"c*a*b\") true  2:" + solution.isMatch("aab", "c*a*b"));
		System.out.println(
				"(\"mississippi\", \"mis*is*ip*.\") ture  3:" + solution.isMatch("mississippi", "mis*is*ip*."));
		System.out
				.println("(\"mississippi\", \"mis*is*p*.\") false  4:" + solution.isMatch("mississippi", "mis*is*p*."));
		System.out.println("(\"ab\", \".*c\") false 5:" + solution.isMatch("ab", ".*c"));
		System.out.println("(\"aaa\", \"a*a\") true  6:" + solution.isMatch("aaa", "a*a"));
		System.out.println("(\"aaa\", \"ab*a\") false 7:" + solution.isMatch("aaa", "ab*a"));
		System.out.println("(\"aa\", \"ab*\") false 8:" + solution.isMatch("aa", "ab*"));
		System.out.println("(\"aaa\", \"aaaa\") false 9:" + solution.isMatch("aaa", "aaaa"));
		System.out.println("(\"aaa\", \"ab*ac*a\") true 10:" + solution.isMatch("aaa", "ab*ac*a"));
		System.out.println("(\"aaa\", \"ab*a*c*a\") true 11:" + solution.isMatch("aaa", "ab*a*c*a"));
		System.out.println("(\"a\", \"ab*\") true 12:" + solution.isMatch("a", "ab*"));
		System.out.println("(\"ab\", \".*..\") true 13:" + solution.isMatch("ab", ".*.."));
		System.out.println("(\"aasdfasdfasdfasdfas\", \"aasdf.*asdf.*asdf.*asdf.*s\") true 14:"
				+ solution.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
		System.out.println("(\"aa\", \"aa\") true 14:" + solution.isMatch("aa", "aa"));
		System.out.println("(\"bbab\", \"b*a*\") false 15:" + solution.isMatch("bbab", "b*a*"));
		
	}

}
