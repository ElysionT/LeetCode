package org.demo.medium;

import java.util.Arrays;

import org.demo.TreeNode;

/**
 * 1080. 根到叶路径上的不足节点
 * 
 * 给你二叉树的根节点 root 和一个整数 limit ，请你同时删除树中所有 不足节点 ，并返回最终二叉树的根节点。
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为 不足节点 ，需要被删除。
 * 叶子节点，就是没有子节点的节点。
 * 
 * 示例 1：
 * 输入：root = [1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14], limit = 1
 * 输出：[1,2,3,4,null,null,7,8,9,null,14]
 * 
 * 示例 2：
 * 输入：root = [5,4,8,11,null,17,4,7,1,null,null,5,3], limit = 22
 * 输出：[5,4,8,11,null,17,4,7,null,null,null,5]
 * 
 * 示例 3：
 * 输入：root = [1,2,-3,-5,null,4,null], limit = -1
 * 输出：[1,null,-3,4]
 * 
 * 提示：
 * 树中节点数目在范围 [1, 5000] 内
 * -105 <= Node.val <= 105
 * -109 <= limit <= 109  
 *
 */
public class Q1080 {

	private int mLimit;

	public TreeNode sufficientSubset(TreeNode root, int limit) {
		mLimit = limit;

		if (!sufficientSubsetInernal(root, 0))
			return null;

		return root;
	}

	private boolean sufficientSubsetInernal(TreeNode node, int parentSum) {
		if (null == node)
			return false;

		int sum = parentSum + node.val;
		if (null == node.left && null == node.right) {
			return sum >= mLimit;
		}

		boolean leftCheck = sufficientSubsetInernal(node.left, sum);
		if (!leftCheck) {
			node.left = null;
		}
		boolean rightCheck = sufficientSubsetInernal(node.right, sum);
		if (!rightCheck) {
			node.right = null;
		}
		return leftCheck || rightCheck;
	}

	public static void main(String[] args) {
		Integer[] nodeValues = new Integer[] { 1, 2, 3, 4, -99, -99, 7, 8, 9, -99, -99, 12, 13, -99, 14 };
		int limit = 1;
		// Integer[] nodeValues = new Integer[] { 5, 4, 8, 11, null, 17, 4, 7, 1, null, null, 5, 3 };
		// int limit = 22;
		// Integer[] nodeValues = new Integer[] { 1, 2, -3, -5, null, 4, null };
		// int limit = -1;

		TreeNode root = TreeNode.sInitBinaryTree2(nodeValues);
		Q1080 solution = new Q1080();
		System.out.println("nodeValues:" + Arrays.toString(nodeValues) + ", limit:" + limit);
		TreeNode result = solution.sufficientSubset(root, limit);
		System.out.print("result:");
		TreeNode.sPrintTree(result);
	}
}
