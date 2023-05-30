package org.demo.medium;

import org.demo.ListNode;

/**
 * 2. 两数相加
 * https://leetcode.cn/problems/add-two-numbers/
 * 
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * 
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class Q2 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		final ListNode result;
		int temp = l1.val + l2.val;
		if (temp < 10) {
			result = new ListNode(temp);
			result.next = addTwoNumbersInternal(l1.next, l2.next, 0);
		} else {
			int a1 = temp - 10;
			result = new ListNode(a1);
			result.next = addTwoNumbersInternal(l1.next, l2.next, 1);
		}
		return result;
	}

	private ListNode addTwoNumbersInternal(ListNode l1, ListNode l2, int a1) {
		if (null == l1 && null == l2) {
			return (0 == a1) ? null : new ListNode(a1);
		} else {
			final ListNode result;
			int temp = (null == l1 ? 0 : l1.val) + (null == l2 ? 0 : l2.val) + a1;
			if (temp < 10) {
				result = new ListNode(temp);
				result.next = addTwoNumbersInternal((null == l1 ? null : l1.next), (null == l2 ? null : l2.next), 0);
			} else {
				int a2 = temp - 10;
				result = new ListNode(a2);
				result.next = addTwoNumbersInternal((null == l1 ? null : l1.next), (null == l2 ? null : l2.next), 1);
			}
			return result;
		}
	}

	public static void main(String[] args) {
		// // [7,0,8]
		// int[] lv1 = { 2, 4, 3 };
		// int[] lv2 = { 5, 6, 4 };//
		// // [0]
		// int[] lv1 = { 0 };
		// int[] lv2 = { 0 };
		// [8,9,9,9,0,0,0,1]
		int[] lv1 = { 9, 9, 9, 9, 9, 9, 9 };
		int[] lv2 = { 9, 9, 9, 9 };

		ListNode l1 = ListNode.sInitListNode(lv1);
		System.out.print("l1:");
		ListNode.sPrintList(l1);
		ListNode l2 = ListNode.sInitListNode(lv2);
		System.out.print("l2:");
		ListNode.sPrintList(l2);

		Q2 solution = new Q2();
		long start = System.currentTimeMillis();
		ListNode result = solution.addTwoNumbers(l1, l2);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", Result:");
		ListNode.sPrintList(result);
	}
}