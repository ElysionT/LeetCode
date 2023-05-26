package org.demo.binaryarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.demo.TreeNode;

/**
 * 二叉树的前序遍历
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xeywh5/
 * 
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
 * 
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 * 
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 * 
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 * 
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * 
 * 前序遍历首先访问根节点，然后遍历左子树，最后遍历右子树。
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xe17x7/
 */
public class Preorder {

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		preorderTraversalInternal(result, root);
		return result;
	}

	private void preorderTraversalInternal(List<Integer> nodes, TreeNode node) {
		if (null == node)
			return;

		nodes.add(node.val);
		preorderTraversalInternal(nodes, node.left);
		preorderTraversalInternal(nodes, node.right);
	}

	public static void main(String[] args) {
		// Integer[] nodes = new Integer[] { 1, null, 2, null, null, 3 };
		// Integer[] nodes = new Integer[] {};
		// Integer[] nodes = new Integer[] { 1 };
		// Integer[] nodes = new Integer[] { 1, 2 };
		// Integer[] nodes = new Integer[] { 1, null, 2 };
		// Integer[] nodes = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
		// 14, 15 };
		Integer[] nodes = new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
		TreeNode root = TreeNode.sInitBinaryTree(nodes);

		Preorder preorder = new Preorder();
		List<Integer> result = preorder.preorderTraversal(root);
		System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.println("Preorder result:" + result);

	}

}
