package org.demo.binaryarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.demo.TreeNode;

/**
 * 二叉树的层序遍历
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xefh1i/
 * 
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[9,20],[15,7]]
 * 
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 * 
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 * 
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 * 
 * 广度优先搜索是一种广泛运用在树或图这类数据结构中，遍历或搜索的算法。 
 * 该算法从一个根节点开始，首先访问节点本身。 然后遍历它的相邻节点，其次遍历它的二级邻节点、三级邻节点，以此类推。
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xej9yc/
 */
public class LevelOrder {

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (null != root) {
			final List<TreeNode> nextLevelNodes = new ArrayList<>(1);
			nextLevelNodes.add(root);

			levelOrderInternal(result, nextLevelNodes);
		}
		return result;
	}

	public void levelOrderInternal(List<List<Integer>> nodes, List<TreeNode> levelNodes) {
		int size = levelNodes.size();
		if (0 == size)
			return;

		final List<Integer> levelNodeValues = new ArrayList<>(size);
		final List<TreeNode> nextLevelNodes = new ArrayList<>(size * 2);
		for (TreeNode node : levelNodes) {
			levelNodeValues.add(node.val);

			if (null != node.left)
				nextLevelNodes.add(node.left);

			if (null != node.right)
				nextLevelNodes.add(node.right);
		}
		nodes.add(levelNodeValues);

		levelOrderInternal(nodes, nextLevelNodes);
	}

	public static void main(String[] args) {
		Integer[] nodes = new Integer[] { 3, 9, 20, null, null, 15, 7 };
//		Integer[] nodes = new Integer[] { 1 };
//		Integer[] nodes = new Integer[] {};
		TreeNode root = TreeNode.sInitBinaryTree(nodes);

		LevelOrder levelOrder = new LevelOrder();
		List<List<Integer>> result = levelOrder.levelOrder(root);
		System.out.println("Nodes:" + Arrays.toString(nodes));
		System.out.println("LevelOrder result:" + result);

	}

}
