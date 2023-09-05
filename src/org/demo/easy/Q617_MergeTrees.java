package org.demo.easy;

import java.util.Arrays;

import org.demo.TreeNode;

/**
 * 617. 合并二叉树
 * https://leetcode.cn/problems/merge-two-binary-trees/
 * 
 * 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null
 * 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 * 
 * 示例 1：
 * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 * 输出：[3,4,5,5,4,null,7]
 * 
 * 示例 2：
 * 输入：root1 = [1], root2 = [1,2]
 * 输出：[2,2]
 * 
 * 提示：
 * 两棵树中的节点数目在范围 [0, 2000] 内
 * -104 <= Node.val <= 104
 */
public class Q617_MergeTrees {
	/*
	 * LevelOrder
	 * 
	 * 时间: 1ms 击败 12.31%使用 Java 的用户
	 * 内存: 41.71mb 击败 22.20%使用 Java 的用户
	 */
	public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
		if (null == root1 && null == root2) {
			return null;
		}
		int val = ((null == root1) ? 0 : root1.val) + ((null == root2) ? 0 : root2.val);
		TreeNode node = new TreeNode(val);
		node.left = mergeTrees(((null == root1) ? null : root1.left), ((null == root2) ? null : root2.left));
		node.right = mergeTrees(((null == root1) ? null : root1.right), ((null == root2) ? null : root2.right));
		return node;
	}

	public static void main(String[] args) {
		// [3,4,5,5,4,null,7]
		Integer[] nodes1_1 = { 1, 3, 2, 5 }, nodes2_1 = { 2, 1, 3, null, 4, null, 7 };
		// [2,2]
		Integer[] nodes1_2 = { 1 }, nodes2_2 = { 1, 2 };

		Q617_MergeTrees solution = new Q617_MergeTrees();
		long start;
		TreeNode result;

		System.out.println("nodes1_1:" + Arrays.toString(nodes1_1) + ", nodes2_1:" + Arrays.toString(nodes2_1));
		TreeNode root1_1 = TreeNode.sInitBinaryTree(nodes1_1);
		TreeNode root2_1 = TreeNode.sInitBinaryTree(nodes2_1);
		System.out.print("root1_1:");
		TreeNode.sPrintTree(root1_1);
		System.out.print("root2_1:");
		TreeNode.sPrintTree(root2_1);
		start = System.currentTimeMillis();
		result = solution.mergeTrees(root1_1, root2_1);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [3,4,5,5,4,null,7] - Result:");
		TreeNode.sPrintTree(result);
		
		System.out.println("nodes1_2:" + Arrays.toString(nodes1_2) + ", nodes2_2:" + Arrays.toString(nodes2_2));
		TreeNode root1_2 = TreeNode.sInitBinaryTree(nodes1_2);
		TreeNode root2_2 = TreeNode.sInitBinaryTree(nodes2_2);
		System.out.print("root1_2:");
		TreeNode.sPrintTree(root1_2);
		System.out.print("root2_2:");
		TreeNode.sPrintTree(root2_2);
		start = System.currentTimeMillis();
		result = solution.mergeTrees(root1_2, root2_2);
		System.out.print("Time:" + (System.currentTimeMillis() - start) + ", [2,2] - Result:");
		TreeNode.sPrintTree(result);
	}
}
