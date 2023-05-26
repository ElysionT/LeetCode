package org.demo.binaryarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.demo.TreeNode;

/**
 * 二叉树的后序遍历
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xebrb2/
 * 
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 * 
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[3,2,1]
 * 
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * 
 * 提示：
 * 树中节点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * 
 * 后序遍历是先遍历左子树，然后遍历右子树，最后访问树的根节点
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xe17x7/
 */
public class Postorder {

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		postorderTraversalInternal(result, root);
		return result;
	}

	private void postorderTraversalInternal(List<Integer> nodes, TreeNode node) {
		if (null == node)
			return;

		postorderTraversalInternal(nodes, node.left);
		postorderTraversalInternal(nodes, node.right);
		nodes.add(node.val);
	}

	public static void main(String[] args) {
//		Integer[] nodes = new Integer[] { 1, null, 2, null, null, 3 };
//		Integer[] nodes = new Integer[] {};
//		Integer[] nodes = new Integer[] { 1 };
//		Integer[] nodes = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		Integer[] nodes = new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
		TreeNode root = TreeNode.sInitBinaryTree(nodes);

		Postorder postorder = new Postorder();
		List<Integer> result = postorder.postorderTraversal(root);
		System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.println("Postorder result:" + result);
	}
}
