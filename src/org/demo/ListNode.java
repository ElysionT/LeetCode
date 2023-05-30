package org.demo;

import java.util.Arrays;

//Definition for singly-linked list.
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public static ListNode sInitListNode(int[] values) {
		System.out.println("Values:" + Arrays.toString(values));
		int length = values.length;
		if (0 == length)
			return null;

		int value = values[0];
		ListNode first = new ListNode(value);
		ListNode previous = first;
		for (int i = 1; i < length; i++) {
			value = values[i];
			previous.next = new ListNode(value);
			previous = previous.next;
		}
		return first;
	}

	public static void sPrintList(ListNode first) {
		ListNode node = first;
		System.out.print("[");
		while (null != node) {
			System.out.print(node.val);
			node = node.next;
			if (null != node)
				System.out.print(",");
		}
		System.out.println("]");
	}
}
