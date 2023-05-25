package org.demo.binaryarray;

import java.util.Arrays;

import org.demo.TreeNode;

/**
 * 路径总和
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xo566j/
 * 
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，
 * 这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * 
 * 叶子节点 是指没有子节点的节点。
 * 
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 * 
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 解释：树中存在两条根节点到叶子节点的路径：
 * (1 --> 2): 和为 3
 * (1 --> 3): 和为 4
 * 不存在 sum = 5 的根节点到叶子节点的路径。
 * 
 * 示例 3：
 * 输入：root = [], targetSum = 0
 * 输出：false
 * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
 * 
 * 提示：
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class PathSum {
	private int mTargetSum = -1;
	private boolean mHasPathSum = false;

	public boolean hasPathSum(TreeNode root, int targetSum) {
		mTargetSum = targetSum;
		mHasPathSum = false;

		topDown(root, 0);

		return mHasPathSum;
	}

	private void topDown(TreeNode node, int pathSum) {
		if (mHasPathSum)
			return;

		if (null == node)
			return;

		pathSum += node.val;
		if (null == node.left && null == node.right) {
			mHasPathSum = mTargetSum == pathSum;
			return;
		}

		topDown(node.left, pathSum);
		topDown(node.right, pathSum);
	}

	public static void main(String[] args) {
//		Integer[] nodes = new Integer[] { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1 };
//		int targetSum = 22;
//		Integer[] nodes = new Integer[] { 1, 2, 3 };
//		int targetSum = 5;
		Integer[] nodes = new Integer[] {};
		int targetSum = 0;

		TreeNode root = TreeNode.sInitBinaryTree(nodes);
		PathSum pathSum = new PathSum();
		boolean result = pathSum.hasPathSum(root, targetSum);
		System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.println("hasPathSum(" + targetSum + "):" + result);

	}

}
