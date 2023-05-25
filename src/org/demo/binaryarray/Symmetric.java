package org.demo.binaryarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.demo.TreeNode;

/**
 * 对称二叉树
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xoxzgv/
 * 
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * 
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 * 
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 */
public class Symmetric {
	private static final boolean DEBUG = true;

	public boolean isSymmetric(TreeNode root) {
		if (null == root)
			return false;

		final List<TreeNode> nextLevelNodes = new ArrayList<>(2);
		nextLevelNodes.add(root.left);
		nextLevelNodes.add(root.right);

		return isSymmetricInternal(nextLevelNodes);
	}

	private boolean isSymmetricInternal(List<TreeNode> levelNodes) {
		if (DEBUG)
			System.out.println("levelNodes:" + levelNodes);
		int size = levelNodes.size();
		if (0 == size)
			return true;

//		if (size % 2 != 0)
//			return false;
		final List<TreeNode> nextLevelNodes = new LinkedList<>();

		TreeNode iNode = null;
		TreeNode jNode = null;
		int j = size / 2;
		for (int i = j - 1; i >= 0 && j < size; i--, j++) {
			iNode = levelNodes.get(i);
			jNode = levelNodes.get(j);

			if (null == iNode && null == jNode)
				continue;
			if ((null == iNode && null != jNode) || (null != iNode && null == jNode))
				return false;
			if (levelNodes.get(i).val != levelNodes.get(j).val)
				return false;

			if (null != iNode) {
				nextLevelNodes.add(0, iNode.right);
				nextLevelNodes.add(0, iNode.left);
			}
			if (null != jNode) {
				nextLevelNodes.add(jNode.left);
				nextLevelNodes.add(jNode.right);
			}
		}
		return isSymmetricInternal(nextLevelNodes);
	}

	public static void main(String[] args) {
		Integer[] nodes = new Integer[] { 1, 2, 2, 3, 4, 4, 3 };
//		Integer[] nodes = new Integer[] { 1, 2, 2, null, 3, null, 3 };
		TreeNode root = TreeNode.sInitBinaryTree(nodes);

		Symmetric symmetric = new Symmetric();
		boolean result = symmetric.isSymmetric(root);
		System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.println("isSymmetric:" + result);
	}
}
