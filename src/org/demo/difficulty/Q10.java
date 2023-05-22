package org.demo.difficulty;

/**
 * 10. 正则表达式匹配
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
 * 
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * 
 * https://leetcode.cn/problems/regular-expression-matching/
 */
public class Q10 {
	static class Solution {
		public boolean isMatch(String s, String p) {
			if (null == s || null == p) {
				return false;
			}

			if (p.equals(".*")) {
				return true;
			}

			int sL = s.length();
			int pL = p.length();
			
			int si = 0, pi = 0;
			boolean flag = false;
			for (; si < sL && pi < pL; si++, pi++) {
				if (s.charAt(si) == p.charAt(pi) || '.' == p.charAt(pi)) {
					if (pi < pL - 1 && '*' != p.charAt(pi + 1)) {
						flag = true;
						continue;
					}
				}
				break;
			}
			if (flag) {
				s = s.substring(si);
				sL = s.length();
				p = p.substring(pi);
				pL = p.length();
			}
			System.out.println("1s:" + s);
			System.out.println("1p:" + p);

			si = sL - 1;
			pi = pL - 1;
			flag = false;
			for (; si > 0L && pi > 0; si--, pi--) {
				if (s.charAt(si) == p.charAt(pi) || '.' == p.charAt(pi)) {
					flag = true;
					continue;
				}
				break;
			}
			if (flag) {
				sL = si + 1;
				s = s.substring(0, sL);
				pL = pi + 1;
				p = p.substring(0, pL);
			}
			System.out.println("2s:" + s);
			System.out.println("2p:" + p);

			int pLength = p.length();
			int sLength = s.length();
			if (pLength == 0) {
				return false;
			}
			
			if(sLength == 0) {
				return isMatchSub(0, p);
			}

			pi = 0;
			si = 0;
			for (; si < sLength;) {
				if (pi >= pLength) {
					return false;
				}

				char sc = s.charAt(si);
				char pc = p.charAt(pi);

				if ('*' == pc) {
					if (pi + 1 != pLength) {
						char pc_ = p.charAt(pi + 1);
						if (sc == pc_ || '.' == pc_) {
							si++;
							pi += 2;
							continue;
						}
					}
					char pc_ = p.charAt(pi - 1);
					if (sc == pc_ || '.' == pc_) {
						si++;
						continue;
					}
				}

				if (sc == pc || '.' == pc) {
					si++;
				}
				pi++;
			}
			
			if (pi >= pLength - 1) {
				return true;
			} else {
				return isMatchSub(pi, p);
			}
		}
		
		public boolean isMatchSub(int pi, String p) {
			int pLength = p.length();
			for (; pi < pLength;) {
				if (pi == pLength - 1) {
					return false;
				}
				if ('*' == p.charAt(pi)) {
					pi++;
					continue;
				}
				if ('*' != p.charAt(pi) && '*' == p.charAt(pi + 1)) {
					pi += 2;
					continue;
				}
				return false;
			}
			return true;
		}
	}
	

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println("(\"aa\", \"a*\") true  1:" + solution.isMatch("aa", "a*"));
		System.out.println("(\"aab\", \"c*a*b\") true  2:" + solution.isMatch("aab", "c*a*b"));
		System.out.println("(\"mississippi\", \"mis*is*ip*.\") ture  3:" + solution.isMatch("mississippi", "mis*is*ip*."));
		System.out.println("(\"mississippi\", \"mis*is*p*.\") false  4:" + solution.isMatch("mississippi", "mis*is*p*."));
		System.out.println("(\"ab\", \".*c\") false 5:" + solution.isMatch("ab", ".*c"));
		System.out.println("(\"aaa\", \"a*a\") true  6:" + solution.isMatch("aaa", "a*a"));
		System.out.println("(\"aaa\", \"ab*a\") false 7:" + solution.isMatch("aaa", "ab*a"));
		System.out.println("(\"aa\", \"ab*\") false 8:" + solution.isMatch("aa", "ab*"));
		System.out.println("(\"aaa\", \"aaaa\") false 9:" + solution.isMatch("aaa", "aaaa"));
		System.out.println("(\"aaa\", \"ab*ac*a\") true 10:" + solution.isMatch("aaa", "ab*ac*a"));
		System.out.println("(\"aaa\", \"ab*a*c*a\") true 11:" + solution.isMatch("aaa", "ab*a*c*a"));
		System.out.println("(\"a\", \"ab*\") true 12:" + solution.isMatch("a", "ab*"));
		System.out.println("(\"ab\", \".*..\") true 12:" + solution.isMatch("ab", ".*.."));
		System.out.println("(\"aasdfasdfasdfasdfas\", \"aasdf.*asdf.*asdf.*asdf.*s\") true 12:" + solution.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
		
		
	}

}
