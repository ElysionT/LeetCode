package org.demo.binaryarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 填充每个节点的下一个右侧节点指针
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xoo0ts/
 * 
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *   
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 *   
 * 示例 2:
 * 输入：root = []
 * 输出：[]
 *   
 * 提示：
 * 树中节点的数量在 [0, 212 - 1] 范围内
 * -1000 <= node.val <= 1000
 *******************************************************************************
 * 填充每个节点的下一个右侧节点指针 II
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xorvud/
 * 
 * Imperfect binary tree
 * 
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 * 
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 
 * 提示：
 * 树中的节点数在范围 [0, 6000] 内
 * -100 <= Node.val <= 100
 */
public class PerfectBinaryTree {

	public Node connect(Node root) {
		final List<Node> nextLevelNodes = new LinkedList<>();
		nextLevelNodes.add(root);
		connectInternal(nextLevelNodes);
		return root;
	}

	public void connectInternal(List<Node> levelNodes) {
		int size = levelNodes.size();
		final List<Node> nextLevelNodes = new LinkedList<>();
		Node node = null;
		Node nextNode = null;
		for (int i = size - 1; i >= 0; i--) {
			node = levelNodes.get(i);
			if (null != node) {
				node.next = nextNode;
				if (null != node.right)
					nextLevelNodes.add(0, node.right);
				if (null != node.left)
					nextLevelNodes.add(0, node.left);
				nextNode = node;
			}
		}

		if (nextLevelNodes.size() > 0) {
			connectInternal(nextLevelNodes);
		}
	}

	public static void main(String[] args) {
		// Integer[] nodes = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		// Imperfect binary tree
		// Integer[] nodes = new Integer[] { 1, 2, 3, 4, 5, null, 7 };
		// Node root = Node.sInitBinaryTree(nodes);
		Node root = Node.sInitBinaryTree(sNodes);

		long start = System.currentTimeMillis();
		PerfectBinaryTree perfectBinaryTree = new PerfectBinaryTree();
		perfectBinaryTree.connect(root);
		System.out.println("Time:" + (System.currentTimeMillis() - start));

		// System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.print("Length:" + sNodes.length);
		System.out.println(", Nodes:" + Arrays.toString(sNodes));
		System.out.print("Connect result:");
		Node.sPrintTree(root);
	}

	private static final Integer[] sNodes = { 1, 5, 8, 9, 7, 7, 8, 1, 4, 8, 1, 9, 0, 8, 7, 1, 7, 4, 2, 9, 8, 2, 4, null,
			null, 9, null, null, null, 6, 0, 9, 4, 1, 0, 1, 8, 9, 0, 1, 8, 9, 1, 0, 9, 6, 2, 5, null, 2, 3, 0, 2, 4, 8,
			8, 8, 5, 0, 0, 9, 4, 9, 1, null, 0, 7, 2, 2, 3, null, 6, 1, 0, 8, 9, 9, 9, 4, 8, 4, 3, 4, 4, 0, null, null,
			8, 3, 8, null, null, 0, null, 0, 4, 9, 1, 2, null, 4, 4, 0, 4, 3, 5, 5, 7, 4, 1, 6, null, 1, 0, null, null,
			null, 2, 8, 7, 7, null, null, 0, 2, 5, 5, 9, 3, 3, null, 7, 6, 6, 7, 9, 8, 1, 7, 7, 7, 2, 6, null, 7, null,
			4, 6, 4, 6, null, null, 9, 1, null, null, null, 5, 5, 5, 4, 2, 2, 8, 5, 1, 1, 3, 1, 3, 7, null, 2, null, 9,
			1, 4, 4, 7, 7, null, 1, 5, 6, 2, 7, 3, null, 9, 1, null, 2, 4, 4, 8, null, null, 7, null, 6, null, 7, 4, 3,
			5, 8, 4, 8, 5, null, null, 8, null, null, null, 4, 4, null, null, null, null, 8, 3, 5, 5, null, null, null,
			1, 2, 0, null, null, 9, 3, null, 8, 3, 7, 1, 8, 9, 0, 1, 8, 2, null, 4, null, null, 8, null, null, null,
			null, 2, null, 4, 8, 5, 5, 3, 1, null, null, 6, null, 1, null, null, 6, null, null, null, null, 7, 3, null,
			null, null, 8, 6, 4, null, 6, 9, 0, 7, 8, null, null, 0, 6, 7, null, null, 0, 0, 7, 2, 3, 2, null, 0, 2, 3,
			null, 0, 1, 7, 9, 0, 7, null, null, null, null, 5, 8, 2, 6, 3, 2, 0, 4, null, null, 0, 9, 1, 1, 1, null, 1,
			3, null, 7, 9, 1, 3, 3, 8, null, null, null, null, 6, null, null, null, null, 9, 8, 1, 3, 8, 3, 0, 6, null,
			null, 8, 5, 6, 5, 2, 1, null, 5, null, 7, 0, 0, null, 9, 3, 9, null, 3, 0, 0, 9, 1, 7, 0, 2, null, 6, 8, 5,
			null, null, null, null, null, 7, null, 2, 5, null, null, 9, null, null, null, null, null, null, null, null,
			null, null, null, 4, 1, null, 3, 6, 6, 2, 5, 5, 9, null, null, 7, 8, null, null, 2, 7, 3, 7, 2, 5, null, 1,
			3, 4, null, null, 8, 3, 6, 9, null, 1, null, null, null, null, 9, 7, 5, 2, null, 5, null, 6, 4, 5, null, 1,
			2, 0, 6, null, 1, 6, null, null, 5, null, 7, 8, 4, 7, 8, 6, 4, null, 5, 6, 7, 9, 1, 0, 4, null, null, null,
			6, 4, 8, 4, 5, null, 0, 4, 4, 0, 1, 7, 1, null, 1, null, 3, 6, null, null, null, null, 8, null, 5, 0, 7, 5,
			null, null, 5, 8, null, null, 3, null, null, 8, null, 2, 4, null, null, null, null, null, null, null, 9,
			null, 9, null, 9, null, null, null, null, 7, 1, null, null, 2, null, null, 5, 5, 5, 5, 6, 4, null, null, 1,
			6, 4, 0, null, 0, 6, 3, 0, null, 5, 5, null, null, null, null, 2, null, 3, 6, null, 3, 0, 5, 0, 1, 0, 3, 4,
			9, 9, 2, 7, 3, 8, 6, 9, null, 5, 8, null, null, null, null, 9, 8, 0, 7, null, null, 8, 8, 6, 6, 0, 2, 7, 4,
			2, 3, 8, 6, 4, null, 8, null, null, null, 2, 0, null, 1, 3, 5, 4, 2, 2, 5, 8, 8, null, 3, 0, null, 1, 6, 0,
			null, null, 9, null, 2, null, 6, 8, 2, null, null, 5, null, null, null, 9, 6, 6, 4, 2, 0, null, null, 1,
			null, 0, null, null, null, 6, 6, null, null, null, 4, 7, 9, null, 0, 1, null, null, 9, null, null, null, 4,
			null, 8, null, null, null, null, null, null, 4, null, 6, null, 3, null, null, 5, 1, 2, 5, null, 0, 7, 8,
			null, 7, null, null, 4, null, 4, 4, null, 2, null, 6, null, null, null, 7, null, null, null, null, 6, 4,
			null, 6, null, 6, 9, null, null, null, 9, 6, null, 9, null, 3, null, 2, null, 7, 7, null, null, 0, null, 6,
			3, null, null, null, null, null, null, 1, null, null, null, 6, 9, 7, null, 7, null, 9, 3, 3, null, null,
			null, null, 4, null, null, 3, null, null, null, 3, 9, null, 0, 3, 1, 9, 6, 7, 9, 4, 8, null, null, 6, null,
			1, 3, 7, null, null, null, 3, null, 2, null, 8, 1, 1, null, null, 6, null, 7, 3, 5, null, 6, 3, 4, null,
			null, 5, 7, 1, null, null, 6, 4, 6, null, null, null, null, 5, 7, 0, 7, 0, null, 5, 8, 5, 5, 4, 5, null,
			null, null, null, null, null, 1, 7, null, null, 7, null, 9, 9, 6, 4, null, null, 3, 2, 1, null, 0, null, 0,
			6, null, null, null, 1, 5, null, null, null, 8, null, null, null, null, 3, 4, 8, null, null, 9, 6, 4, null,
			null, null, null, 8, 9, null, 1, null, null, null, 7, null, null, null, null, null, 9, null, null, null, 4,
			1, 6, 7, 0, null, null, null, 7, null, null, 8, null, null, null, null, null, null, null, 4, null, 9, null,
			null, null, null, 3, 0, 6, null, 5, null, 9, 9, null, null, 4, 3, 4, null, null, null, null, 8, null, 5,
			null, null, null, null, 5, 2, null, null, null, null, null, null, null, 2, null, null, 2, 1, 8, 5, null, 0,
			null, 0, 3, 2, 4, 5, null, null, null, null, null, 7, null, null, 0, null, 0, null, null, null, 0, 3, 9,
			null, null, null, null, 5, null, null, 0, 5, 0, 0, null, 9, null, null, null, null, null, null, null, null,
			8, null, 9, 3, 5, 9, 0, 5, 9, null, null, 9, 4, null, 0, 2, 0, null, null, 7, null, 7, null, 5, 7, 8, 7,
			null, null, null, 3, 0, 3, null, null, null, null, null, 4, 5, null, null, 2, 3, null, 2, null, null, 7,
			null, null, 9, null, null, 9, 7, 1, null, null, 1, 6, 1, 8, null, null, 5, null, null, 3, 7, 9, 6, null,
			null, null, null, 1, null, null, null, 3, 7, 3, 2, 3, 3, null, 1, null, null, null, 1, null, null, 4, 3, 4,
			8, 7, null, 0, 3, 0, null, 1, 1, null, null, null, null, null, 5, null, 6, 0, null, 3, 1, null, 6, null,
			null, 4, 0, 1, null, 6, 1, null, null, 9, 6, 4, 9, 0, 8, 9, 3, 3, 6, null, null, null, null, null, null,
			null, null, null, null, null, null, 2, null, null, null, null, null, 8, 5, 8, 3, 5, 4, null, 6, null, 0,
			null, null, 6, null, 4, 3, null, null, null, null, null, null, null, null, null, null, null, null, null,
			null, 7, 3, null, null, 1, null, 2, 4, null, null, null, 6, null, null, null, 6, null, 5, null, null, null,
			null, 1, null, null, 3, null, 1, null, 7, 1, null, null, 7, 1, 3, 4, 8, null, null, null, null, null, 4,
			null, null, 4, null, null, null, 7, null, 6, null, null, 1, null, null, null, 7, 3, 3, null, null, null,
			null, 3, 0, null, null, 4, null, null, null, null, null, null, null, null, null, null, 8, null, null, 9,
			null, null, 6, 6, 5, 2, null, 8, 3, 8, null, null, null, null, 6, 7, 0, null, null, null, null, 1, 1, 5,
			null, 0, 5, null, 5, null, null, null, 1, 2, null, 2, 9, 1, null, 2, 4, 1, null, null, null, 1, 8, 4, 4, 5,
			2, null, null, 6, 4, 7, 5, 2, 9, null, 4, null, null, null, null, null, 3, null, null, 5, 9, null, null,
			null, null, 9, null, 9, null, null, null, 2, null, 1, 9, null, null, null, null, null, 1, 9, 3, null, null,
			1, 9, null, 5, 2, 1, 0, null, null, 1, 9, 8, 4, 7, null, null, 5, 7, null, null, null, null, 1, 2, 8, null,
			6, 0, null, null, null, null, 0, null, null, null, 6, null, 2, 3, 0, 9, null, null, 1, 4, 6, null, 8, null,
			null, 5, null, 3, 0, null, 6, null, null, null, null, null, 2, null, null, null, null, null, null, 2, 5, 8,
			6, 9, null, null, null, 8, null, null, 9, 6, null, null, null, null, 3, null, null, null, null, 9, null,
			null, 2, null, null, null, null, null, null, 8, 8, null, null, null, null, null, 9, null, 6, null, 2, 5,
			null, null, 1, 2, null, 4, null, null, 4, null, null, 3, 5, null, 3, 3, null, null, 1, null, null, null,
			null, 4, null, 2, 3, null, 4, 5, 3, null, 7, null, null, null, 7, 6, null, null, 1, 3, null, 4, 9, 8, null,
			null, 0, null, 3, 4, null, 8, null, 1, null, null, 2, 2, null, null, 4, null, null, null, 3, null, null, 2,
			null, null, null, 4, null, 5, null, null, null, null, 2, null, 5, null, null, null, null, null, null, 2, 7,
			5, null, 6, null, null, null, null, 2, null, 0, null, 3, null, 1, null, 9, 4, null, 3, null, null, null,
			null, null, null, null, 5, 5, 7, null, null, 1, null, 4, 6, null, null, null, 2, null, 5, 9, 0, 6, 2, null,
			null, null, null, null, null, null, null, null, null, null, null, 5, null, 7, null, 2, 9, null, null, 1,
			null, null, null, 1, 6, null, 6, null, null, 0, 8, null, 4, null, null, null, null, 4, null, null, 0, null,
			6, 0, null, null, null, 4, null, null, null, null, null, 0, null, null, null, null, null, null, null, null,
			null, null, null, null, 0, 5, 4, 2, 6, 4, 5, 3, 4, null, null, 5, null, null, null, null, 4, null, null, 3,
			6, 2, 0, null, 6, 6, null, null, null, null, 0, 6, null, null, null, 3, 9, 4, null, null, null, null, null,
			0, null, null, 6, 7, 0, null, 9, 2, null, 3, 3, null, null, 8, null, 3, null, null, null, 8, 5, 3, null, 2,
			4, null, 9, 6, 9, null, null, null, null, 6, null, 6, null, 5, 3, null, null, null, null, 4, null, null,
			null, 9, 0, 9, 7, 1, 1, null, 1, null, 1, 6, null, 5, null, 6, null, null, 1, null, null, null, null, null,
			null, 5, null, null, null, null, null, 3, null, 6, 1, null, 0, 2, null, null, 0, null, null, 0, null, null,
			null, null, null, 3, null, null, 8, null, null, 5, 3, 3, null, null, null, null, null, null, null, 3, null,
			null, 0, 8, 7, null, null, 8, 1, null, null, null, null, null, null, 7, null, null, null, null, null, null,
			null, null, null, null, null, 5, 2, null, 2, 6, null, null, null, null, null, null, null, 1, 5, 0, null,
			null, 2, null, 7, null, null, 6, null, null, null, null, null, null, null, null, null, null, null, null,
			null, 8, null, null, null, null, 3, null, null, 4, null, null, 2, null, null, null, null, 0, 3, null, null,
			null, null, null, 7, null, 8, null, null, null, null, 8, 5, null, 3, 4, null, null, null, 8, null, null,
			null, null, null, null, null, null, null, 3, 7, null, null, null, 4, 0, 3, null, null, 6, null, null, null,
			null, null, null, null, null, null, null, null, null, 8, null, null, null, null, null, 2, null, null, null,
			null, null, null, null, null, null, 0, null, null, null, 2, null, null, null, 8, 2, null, null, null, null,
			null, null, null, 8, null, null, null, null, null, null, null, null, null, null, 2, null, null, null, 2, 5,
			null, null, null, null, null, null, null, null, null, null, null, 2, null, null, null, null, null, 8, null,
			null, null, null, null, null, null, null, null, null, 0, 5 };
}

class Node {
	public int val;
	public Node left;
	public Node right;
	public Node next;

	public Node() {
	}

	public Node(int _val) {
		val = _val;
	}

	public Node(int _val, Node _left, Node _right) {
		val = _val;
		left = _left;
		right = _right;
	}

	public Node(int _val, Node _left, Node _right, Node _next) {
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}

	public static Node sInitBinaryTree(Integer[] nodeValues) {
		final Node root = sInitBinaryTreeInternal(nodeValues, 0);
		// TODO: Log root
		return root;
	}

	private static Node sInitBinaryTreeInternal(Integer[] nodeValues, int index) {
		if (index >= nodeValues.length)
			return null;

		Integer value = nodeValues[index];
		if (null == value)
			return null;

		return new Node(value, sInitBinaryTreeInternal(nodeValues, 2 * index + 1),
				sInitBinaryTreeInternal(nodeValues, 2 * index + 2));
	}

	public static void sPrintTree(Node root) {
		final List<Object> result = new ArrayList<>();
		final List<Node> nextLevelNodes = new ArrayList<>(1);
		nextLevelNodes.add(root);

		sPrintTreeInternal(result, nextLevelNodes);
		System.out.println(Arrays.toString(result.toArray(new Object[] {})));
	}

	private static void sPrintTreeInternal(List<Object> result, List<Node> levelNodes) {
		boolean doNext = false;

		int size = levelNodes.size();
		List<Object> result_ = new ArrayList<>(size);
		final List<Node> nextLevelNodes = new ArrayList<>(size * 2);
		for (Node node : levelNodes) {
			if (null == node) {
				result_.add(null);
				nextLevelNodes.add(null);
				nextLevelNodes.add(null);

			} else {
				result_.add(node.val);
				if (null == node.next) {
					result_.add("#");
				}
				nextLevelNodes.add(node.left);
				nextLevelNodes.add(node.right);
				doNext = true;
			}
		}

		if (doNext) {
			result.addAll(result_);
			sPrintTreeInternal(result, nextLevelNodes);
		}
	}
};
