package org.demo.medium;

import java.util.ArrayList;
import java.util.List;

import org.demo.ListNode;

/**
 * 445. 两数相加 II
 * https://leetcode.cn/problems/add-two-numbers-ii/
 * 
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 
 * 示例1：
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 
 * 示例2：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * 
 * 示例3：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 
 * 提示：
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 */
public class Q445_AddTwoNumbers {
	private static final boolean DEBUG = false;

	/*
	 * 执行用时：2 ms, 在所有 Java 提交中击败了72.20%的用户
	 * 内存消耗：41.8 MB, 在所有 Java 提交中击败了60.36%的用户
	 * 通过测试用例：1563 / 1563
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		List<Integer> l1List = new ArrayList<>();
		do {
			l1List.add(0, l1.val);
			l1 = l1.next;
		} while (null != l1);
		if (DEBUG)
			System.out.println("l1List:" + l1List);

		List<Integer> l2List = new ArrayList<>();
		do {
			l2List.add(0, l2.val);
			l2 = l2.next;
		} while (null != l2);
		if (DEBUG)
			System.out.println("l2List:" + l2List);

		ListNode next = null;
		int offset = 0;
		for (int i = 0, j = 0, l1Size = l1List.size(), l2Size = l2List.size(); i < l1Size || j < l2Size; i++, j++) {
			int a = 0;
			if (i < l1Size) {
				a = l1List.get(i);
			}
			int b = 0;
			if (j < l2Size) {
				b = l2List.get(j);
			}
			int c = a + b + offset;
			if (c >= 10) {
				offset = 1;
				next = new ListNode(c - 10, next);
			} else {
				offset = 0;
				next = new ListNode(c, next);
			}
		}
		if (offset > 0) {
			next = new ListNode(offset, next);
		}

		return next;
	}

	public static void main(String[] args) {
		// [7,8,0,7]
		ListNode l1_1 = ListNode.sInitListNode(new int[] { 7, 2, 4, 3 }),
				l2_1 = ListNode.sInitListNode(new int[] { 5, 6, 4 });
		// [8,0,7]
		ListNode l1_2 = ListNode.sInitListNode(new int[] { 2, 4, 3 }),
				l2_2 = ListNode.sInitListNode(new int[] { 5, 6, 4 });
		// [0]
		ListNode l1_3 = ListNode.sInitListNode(new int[] { 0 }), l2_3 = ListNode.sInitListNode(new int[] { 0 });
		// [1,0]
		ListNode l1_4 = ListNode.sInitListNode(new int[] { 5 }), l2_4 = ListNode.sInitListNode(new int[] { 5 });

		long start;
		ListNode result;
		Q445_AddTwoNumbers solution = new Q445_AddTwoNumbers();

		System.out.print("l1_1:");
		ListNode.sPrintList(l1_1);
		System.out.print("l2_1:");
		ListNode.sPrintList(l2_1);
		start = System.currentTimeMillis();
		result = solution.addTwoNumbers(l1_1, l2_1);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [7,8,0,7] - Result:");
		ListNode.sPrintList(result);
		System.out.println("\n");

		System.out.print("l1_2:");
		ListNode.sPrintList(l1_2);
		System.out.print("l2_2:");
		ListNode.sPrintList(l2_2);
		start = System.currentTimeMillis();
		result = solution.addTwoNumbers(l1_2, l2_2);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [8,0,7] - Result:");
		ListNode.sPrintList(result);
		System.out.println("\n");

		System.out.print("l1_3:");
		ListNode.sPrintList(l1_3);
		System.out.print("l2_3:");
		ListNode.sPrintList(l2_3);
		start = System.currentTimeMillis();
		result = solution.addTwoNumbers(l1_3, l2_3);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [0] - Result:");
		ListNode.sPrintList(result);
		System.out.println("\n");

		System.out.print("l1_4:");
		ListNode.sPrintList(l1_4);
		System.out.print("l2_4:");
		ListNode.sPrintList(l2_4);
		start = System.currentTimeMillis();
		result = solution.addTwoNumbers(l1_4, l2_4);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [1,0] - Result:");
		ListNode.sPrintList(result);
		System.out.println("\n");

	}
}
