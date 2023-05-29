package org.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	public static TreeNode sInitBinaryTree(Integer[] nodeValues) {
		final TreeNode root = sInitBinaryTreeInternal(nodeValues, 0);
		// TODO: Log root
		return root;
	}

	private static TreeNode sInitBinaryTreeInternal(Integer[] nodeValues, int index) {
		if (index >= nodeValues.length)
			return null;

		Integer value = nodeValues[index];
		if (null == value)
			return null;

		return new TreeNode(value, sInitBinaryTreeInternal(nodeValues, 2 * index + 1),
				sInitBinaryTreeInternal(nodeValues, 2 * index + 2));
	}

	// Test: Integer[] nodes = new Integer[] { 1, 2, 3, null, null, 4, 5, 6 };
	public static TreeNode sInitBinaryTree2(Integer[] nodeValues) {
		int length = nodeValues.length;
		if (0 == length)
			return null;

		final TreeNode root = new TreeNode(nodeValues[0]);
		List<TreeNode> parentNodes = new LinkedList<>();
		parentNodes.add(root);

		boolean isLeft = true;
		TreeNode parent = null;
		Integer value = null;
		for (int i = 1; i < length; i++) {
			if (isLeft)
				parent = parentNodes.remove(0);

			value = nodeValues[i];
			if (null != value) {
				TreeNode node = new TreeNode(value);
				if (isLeft) {
					parent.left = node;
				} else {
					parent.right = node;
				}
				parentNodes.add(node);
			}
			isLeft = !isLeft;

		}

		// sInitBinaryTreeInternal2(nodeValues, parentNodes, 1);

		// TODO: Log root
		return root;
	}

	// java.lang.StackOverflowError
	private static void sInitBinaryTreeInternal2(Integer[] nodeValues, List<TreeNode> parentNodes, int index) {
		// System.out.println(
		// "nodeValues:" + Arrays.toString(nodeValues) + ", parentNodes:" + parentNodes
		// + ", index:" + index);

		int length = nodeValues.length;
		if (index >= length)
			return;

		int size = parentNodes.size();
		List<TreeNode> nodes = new ArrayList<>(size * 2);

		Integer value = null;
		TreeNode parent = null;
		for (int j = 0; index < length && j < size; j++) {
			parent = parentNodes.get(j);
			value = nodeValues[index++];
			if (null != value) {
				TreeNode node = new TreeNode(value);
				parent.left = node;
				nodes.add(node);
			}

			if (index >= length)
				break;

			value = nodeValues[index++];
			if (null != value) {
				TreeNode node = new TreeNode(value);
				parent.right = node;
				nodes.add(node);
			}
		}

		if (nodes.size() > 0) {
			// java.lang.StackOverflowError
			sInitBinaryTreeInternal2(nodeValues, nodes, index);
		}
	}

	public static void sPrintTree(TreeNode root) {
		final List<Integer> result = new ArrayList<>();
		final List<TreeNode> nextLevelNodes = new ArrayList<>(1);
		nextLevelNodes.add(root);

		sPrintTreeInternal(result, nextLevelNodes);
		System.out.println("Tree:" + Arrays.toString(result.toArray(new Integer[] {})));
	}

	private static void sPrintTreeInternal(List<Integer> result, List<TreeNode> levelNodes) {
		boolean doNext = false;

		int size = levelNodes.size();
		List<Integer> result_ = new ArrayList<>(size);
		final List<TreeNode> nextLevelNodes = new ArrayList<>(size * 2);
		for (TreeNode node : levelNodes) {
			if (null == node) {
				result_.add(null);
				nextLevelNodes.add(null);
				nextLevelNodes.add(null);

			} else {
				result_.add(node.val);
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

	public static void sPrintTree2(TreeNode root) {
		final List<Integer> result = new ArrayList<>();
		final List<TreeNode> nextLevelNodes = new ArrayList<>(1);
		nextLevelNodes.add(root);

		sPrintTreeInternal2(result, nextLevelNodes);
		System.out.println("Tree:" + Arrays.toString(result.toArray(new Integer[] {})));
	}

	private static void sPrintTreeInternal2(List<Integer> result, List<TreeNode> levelNodes) {

		TreeNode node = null;
		while (levelNodes.size() > 0) {
			node = levelNodes.remove(0);
			if (null == node) {
				result.add(null);
			} else {
				result.add(node.val);
				levelNodes.add(node.left);
				levelNodes.add(node.right);
			}
		}

	}
}
