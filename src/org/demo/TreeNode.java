package org.demo;

import java.util.ArrayList;
import java.util.Arrays;
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
}
