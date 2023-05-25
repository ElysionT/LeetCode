package org.demo.binaryarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.demo.TreeNode;

/**
 * 二叉树的中序遍历
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xecaj6/
 * 
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 * 
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
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
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * 
 * 中序遍历是先遍历左子树，然后访问根节点，然后遍历右子树。
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xe17x7/
 */
public class Inorder {

	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		inorderTraversalInternal(result, root);
		return result;
	}

	private void inorderTraversalInternal(List<Integer> nodes, TreeNode node) {
		if (null == node)
			return;

		inorderTraversalInternal(nodes, node.left);
		nodes.add(node.val);
		inorderTraversalInternal(nodes, node.right);
	}

	public static void main(String[] args) {
//		Integer[] nodes = new Integer[] { 1, null, 2, null, null, 3 };
//		Integer[] nodes = new Integer[] {};
//		Integer[] nodes = new Integer[] { 1 };
		Integer[] nodes = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
		TreeNode root = TreeNode.sInitBinaryTree(nodes);
		
		Inorder inorder = new Inorder();
		List<Integer> result = inorder.inorderTraversal(root);
		System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.println("Inorder result:" + result);

	}

}
