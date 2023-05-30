package org.demo.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.demo.TreeNode;

/**
 * 1110. 删点成林
 * https://leetcode.cn/problems/delete-nodes-and-return-forest/
 * 
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * 
 * 示例 1：
 * 输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * 输出：[[1,2,null,4],[6],[7]]
 * 
 * 示例 2：
 * 输入：root = [1,2,4,null,3], to_delete = [3]
 * 输出：[[1,2,4]]
 * 
 * 提示：
 * 树中的节点数最大为 1000。
 * 每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
 * to_delete.length <= 1000
 * to_delete 包含一些从 1 到 1000、各不相同的值。
 */
public class Q1110 {

	private int[] mToDelete;
	private int mToDeleteLength;

	private List<TreeNode> mResult;

	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		mToDelete = to_delete;
		mToDeleteLength = mToDelete.length;
		mResult = new ArrayList<>();
		if (0 == mToDeleteLength) {
			mResult.add(root);
		} else {
			if (!delNodeInternal(root)) {
				mResult.add(root);
			}
		}
		return mResult;
	}

	private boolean delNodeInternal(TreeNode node) {
		if (null == node)
			return false;

		boolean isDelete = isDelete(node.val);

		final TreeNode leftNode = node.left;
		final boolean isDeleteLeft = delNodeInternal(leftNode);
		if (isDeleteLeft) {
			node.left = null;
		}

		final TreeNode rightNode = node.right;
		final boolean isDeleteRight = delNodeInternal(rightNode);
		if (isDeleteRight) {
			node.right = null;
		}

		if (isDelete) {
			if (null != leftNode && !isDeleteLeft)
				mResult.add(leftNode);
			if (null != rightNode && !isDeleteRight)
				mResult.add(rightNode);
		}
		return isDelete;
	}

	private boolean isDelete(int value) {
		for (int i = 0, j = mToDeleteLength - 1; i <= j; i++, j--) {
			if (value == mToDelete[i] || value == mToDelete[j]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// [[1,2,null,4],[6],[7]]
		 Integer[] nodes = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		 int[] to_delete = { 3, 5 };
		// [[1,2,4]]
//		 Integer[] nodes = new Integer[] { 1, 2, 4, null, 3 };
//		 int[] to_delete = { 3 };
		// [[1],[4]]
//		Integer[] nodes = new Integer[] { 1, 2, null, 4, 3 };
//		int[] to_delete = { 2, 3 };

		TreeNode root = TreeNode.sInitBinaryTree(nodes);
		System.out.println("Nodes:" + Arrays.toString(nodes) + ", to_delete:" + Arrays.toString(to_delete));
		Q1110 solution = new Q1110();
		long start = System.currentTimeMillis();
		List<TreeNode> result = solution.delNodes(root, to_delete);
		System.out.println("Time:" + (System.currentTimeMillis() - start) + ", Result:");
		for (TreeNode node : result) {
			TreeNode.sPrintTree2(node);
		}
	}
}
