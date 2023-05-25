package org.demo.binaryarray;

import java.util.Arrays;

import org.demo.TreeNode;

/**
 * 从前序与中序遍历序列构造二叉树
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xoei3r/
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 * 
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 */
public class BuildTree2 {
	private int[] mPreorder;
	private int[] mInorder;

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		mPreorder = preorder;
		mInorder = inorder;
		return buildTreeInternal(0, mInorder.length - 1, 0, mPreorder.length - 1);
	}

	private int findIndex(int start, int end, int target) {
		int index = (end + start) / 2;
		for (int i = index, j = index + 1; i >= start || j <= end; i--, j++) {
			if (i >= start && mInorder[i] == target) {
				return i;
			}
			if (j <= end && mInorder[j] == target) {
				return j;
			}
		}
		return -1;
	}

	private TreeNode buildTreeInternal(int inStart, int inEnd, int preStart, int preEnd) {
		final int nodeValue = mPreorder[preStart];
		final TreeNode node = new TreeNode(nodeValue);

		if (preStart == preEnd) {
			return node;
		}

		// The index of nodeValue in inroder.
		final int index = findIndex(inStart, inEnd, nodeValue);
		if (inStart == index) {
			node.right = buildTreeInternal(inStart + 1, inEnd, preStart + 1, preEnd);
		} else if (inEnd == index) {
			node.left = buildTreeInternal(inStart, inEnd - 1, preStart + 1, preEnd);
		} else {
			int leftSubInorderSize = index - inStart; // index - 1 - inStart + 1;
			int rightSubPrestorderIndex = preStart + 1 + leftSubInorderSize;
			node.left = buildTreeInternal(inStart, index - 1, preStart + 1, rightSubPrestorderIndex - 1);
			node.right = buildTreeInternal(index + 1, inEnd, rightSubPrestorderIndex, preEnd);
		}
		return node;
	}

	public static void main(String[] args) {
		// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
		int[] preorder = { 1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 13, 7, 14, 15 };
		int[] inorder = { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };

		BuildTree2 buildTree2 = new BuildTree2();
		TreeNode root = buildTree2.buildTree(preorder, inorder);
		System.out.println("preorder:" + Arrays.toString(preorder));
		System.out.println("inorder:" + Arrays.toString(inorder));
		TreeNode.sPrintTree(root);

	}

}
