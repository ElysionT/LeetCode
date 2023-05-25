package org.demo.binaryarray;

import java.util.Arrays;

import org.demo.TreeNode;

/**
 * 从中序与后序遍历序列构造二叉树
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xo98qt/
 * 
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * 
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 * 
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 * 
 * 示例 3:
 * 输入：inorder = [8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15], postorder = [8, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1]
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
 * 
 * 提示:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder 和 postorder 都由 不同 的值组成
 * postorder 中每一个值都在 inorder 中
 * inorder 保证是树的中序遍历
 * postorder 保证是树的后序遍历
 */
public class BuildTree {

	private int[] mInorder;
	private int[] mPostorder;

	/*
	 * (1).
	 * postorder = [8, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1]
	 * 																^root
	 * inorder   = [8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15]
	 * 				     left sub tree  <--^--> right sub tree
	 * (2).
	 * left sub_l tree inorder = 	[8, 4, 9, 2, 10, 5, 11],
	 * 								leftInorderLength = 7
	 * 
	 * left sub_l tree postorder = 	[8, 9, 4, 10, 11, 5, 2], 
	 * 								 ^leftPostorderStart ^subLeftTreeRoot/leftPostorderEnd
	 * 
	 * 								leftPostorderLength = leftInorderLength, 
	 * 								(index) leftPostorderStart = 0, 
	 * 								(index) leftPostorderEnd = (leftPostorderStart + leftPostorderLength -1)
	 * 
	 * right sub_r tree inorder = 	[12, 6, 13, 3, 14, 7, 15],
	 * 								rightInorderLength = 7
	 * 
	 * right sub_r tree postorder = [12, 13, 6, 14, 15, 7, 3],  
	 *                               ^rightPostorderStart  ^subRightTreeRoot/rightPostorderEnd
	 *                                                     
	 *                              rightPostorderLength = rightInorderLength, 
	 *                              (index) rightPostorderStart = (leftPostorderEnd + 1), 
	 *                              (index) rightPostorderEnd = (rightPostorderStart + rightPostorderLength -1) or (parentTreePostorder.length -1 -1)
	 * 
	 * (3). [Repeat (2)]
	 * left sub_l_l tree inorder = 		[8, 4, 9],
	 * 									leftInorderLength = 3
	 * 
	 * left sub_l_l tree postorder = 	[8, 9, 4],
	 * 								 	^leftPostorderStart
	 *                                      	^subLeftTreeRoot/leftPostorderEnd		 
	 * right sub_l_r tree inorder = 	[10, 5, 11],
	 * 									rightInorderLength = 3
	 * 
	 * right sub_l_r tree postorder = 	[10, 11, 5],
	 * 								 	^rightPostorderStart
	 *                                      	 ^subRightTreeRoot/rightPostorderEnd                            
	 * 							   		  
	 * 
	 * left sub_r_l tree inorder = 		[12, 6, 13],
	 * 									leftInorderLength = 3
	 * 
	 * left sub_r_l tree postorder = 	[12, 13, 6],
	 * 								 	^leftPostorderStart
	 *                                      	^subLeftTreeRoot/leftPostorderEnd		 
	 * right sub_r_r tree inorder = 	[14, 7, 15],
	 * 									rightInorderLength = 3
	 * 
	 * right sub_r_r tree postorder = 	[14, 15, 7],
	 * 								 	^rightPostorderStart
	 *                                      	 ^subRightTreeRoot/rightPostorderEnd   
	 * 
	 * (4). [Repeat (2)]
	 * left sub_l_l_l tree inorder = 	[8],
	 * 									leftInorderLength = 1
	 * left sub_l_l_l tree postorder = 	[8],
	 * 
	 * right sub_l_l_r tree inorder = 	[9],
	 * 									rightInorderLength = 1 
	 * right sub_l_l_r tree postorder = [9],
	 * 
	 * left sub_l_r_l tree inorder = 	[10],
	 * 									rightInorderLength = 1 
	 * left sub_l_r_l tree postorder = 	[10],
	 * 
	 * right sub_l_r_r tree inorder = 	[11],
	 * 									rightInorderLength = 1 
	 * right sub_l_r_r tree postorder = [11],
	 * ...
	 * 
	 * (5).
	 * if the length of order(inorder/postorder) = 1
	 * return new TreeNode(in/postorder[0])
	 */
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		mInorder = inorder;
		mPostorder = postorder;
		return buildTreeInternal(0, mInorder.length - 1, 0, mPostorder.length - 1);
	}

	private TreeNode buildTreeInternal(int inStart, int inEnd, int postStart, int postEnd) {
		final int nodeValue = mPostorder[postEnd];
		final TreeNode node = new TreeNode(nodeValue);

		if (postStart == postEnd) {
			return node;
		}

		// The index of nodeValue in inroder.
		final int index = findIndex(inStart, inEnd, nodeValue);
		if (inStart == index) {
			node.right = buildTreeInternal(inStart + 1, inEnd, postStart, postEnd - 1);
		} else if (inEnd == index) {
			node.left = buildTreeInternal(inStart, inEnd - 1, postStart, postEnd - 1);
		} else {
			int leftSubInorderSize = index - inStart; // index - 1 - inStart + 1;
			int rightSubPostorderIndex = postStart + leftSubInorderSize;
			node.left = buildTreeInternal(inStart, index - 1, postStart, rightSubPostorderIndex - 1);
			node.right = buildTreeInternal(index + 1, inEnd, rightSubPostorderIndex, postEnd - 1);
		}
		return node;
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

	// Binary search algorithm
//	private int findIndex(int start, int end, int target, int[] order) {
//		if (start > end)
//			return -1;
//
//		int index = (end + start) / 2;
//		int value = order[index];
//		if (value == target)
//			return index;
//
//		int temp = findIndex(start, index - 1, target, order);
//		if (-1 == temp) {
//			temp = findIndex(index + 1, end, target, order);
//		}
//		return temp;
//	}

	public static void main(String[] args) {
		// [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
		int[] inorder = { 8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 13, 3, 14, 7, 15 };
		int[] postorder = { 8, 9, 4, 10, 11, 5, 2, 12, 13, 6, 14, 15, 7, 3, 1 };
		// [3, 9, 20, null, null, 15, 7]
//		int[] inorder = { 9, 3, 15, 20, 7 };
//		int[] postorder = { 9, 15, 7, 20, 3 };
		// [ -1 ]
//		int[] inorder = { -1 };
//		int[] postorder = { -1 };
		// [1, 2]
//		int[] inorder = { 2, 1 };
//		int[] postorder = { 2, 1 };
		// [1, null, 2]
//		int[] inorder = { 1, 2 };
//		int[] postorder = { 2, 1 };
		// [1, 2, null, 3
//		int[] inorder = { 3, 2, 1 };
//		int[] postorder = { 3, 2, 1 };
		// [2, 1, 3, null, null, null, 4]
//		int[] inorder = { 1, 2, 3, 4 };
//		int[] postorder = { 1, 4, 3, 2 };
		// [3,1,4,null,2,null,5]
//		int[] inorder = { 1, 2, 3, 4, 5 };
//		int[] postorder = { 2, 1, 5, 4, 3 };

		BuildTree buildTree = new BuildTree();
		TreeNode root = buildTree.buildTree(inorder, postorder);
		System.out.println("Inorder:" + Arrays.toString(inorder));
		System.out.println("Postorder:" + Arrays.toString(postorder));
		TreeNode.sPrintTree(root);
	}

}
