package org.demo.binaryarray;

import java.util.Arrays;

import org.demo.TreeNode;

/**
 * 二叉树的最大深度
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xoh1zg/
 * 
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 * 
 * 运用递归解决树的问题 * 
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xefb4e/
 */
public class FindMaximumDepth {

	private int mMaximumDepth;

	public int maxDepth(TreeNode root) {
		return maximumDepth(root, true);
	}

	public int maximumDepth(TreeNode root, boolean isTopDown) {
		if (isTopDown) {
			mMaximumDepth = 0;
			topDown(root, 1);
		} else
			mMaximumDepth = buttomUp(root);
		return mMaximumDepth;
	}

	private void topDown(TreeNode node, int depth) {
		if (null == node)
			return;

		if (null == node.left && null == node.right)
			mMaximumDepth = Math.max(mMaximumDepth, depth);

		topDown(node.left, depth + 1);
		topDown(node.right, depth + 1);
	}

	private int buttomUp(TreeNode node) {
		if (null == node)
			return 0;

		return Math.max(buttomUp(node.left), buttomUp(node.right)) + 1;
	}

	public static void main(String[] args) {
		Integer[] nodes = new Integer[] { 0, 1, 2, 3, 4, null, 6, null, null, 9 };
//		Integer[] nodes = new Integer[] {};
//		Integer[] nodes = new Integer[] { 1 };
		TreeNode root = TreeNode.sInitBinaryTree(nodes);

		FindMaximumDepth solution = new FindMaximumDepth();
		int maximumDepth = solution.maximumDepth(root, true);
		System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.println("Maximum depth(Top down):" + maximumDepth);
		maximumDepth = solution.maximumDepth(root, false);
		System.out.println("Maximum depth(Buttom up):" + maximumDepth);
	}
}
