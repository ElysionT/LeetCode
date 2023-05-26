package org.demo.binaryarray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.demo.QUtils;
import org.demo.TreeNode;

/**
 * 二叉树的最近公共祖先
 * https://leetcode.cn/leetbook/read/data-structure-binary-tree/xopaih/
 * 
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 
 * 示例 3：
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * 
 * 提示：
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 */
public class LowestCommonAncestor {
	private static final boolean DEBUG = true;
	private List<TreeNode> mInorder;
	private List<TreeNode> mPostorder;

	private int mPIndex;
	private int mQIndex;

	private TreeNode mP;
	private TreeNode mQ;

	/**
	 * Solution: Inorder
	 * Tree: { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
	 * 
	 * 1.
	 * p = 5, q = 1 
	 * Inorder 		{6, 5, 7, 2, 4, 3, 0, 1, 8}
	 * 					^p			^root ^q
	 * Postorder	{6, 7, 4, 2, 5, 0, 8, 1, 3}
	 * 										 ^root
	 * {(index) p < root < q}
	 * return root
	 * 
	 * 2.
	 * p = 5, q = 4
	 * Inorder 		{6, 5, 7, 2, 4, 3, 0, 1, 8}
	 * 					^p		 ^q	^root 
	 * Postorder	{6, 7, 4, 2, 5, 0, 8, 1, 3}
	 * 										 ^root
	 * 2.1
	 * {(index) p < root && q < root}
	 * SubInorder 	{6, 5, 7, 2, 4}
	 * 					^p		 ^q
	 * 					^root 
	 * SubPostorder	{6, 7, 4, 2, 5}
	 * 							 ^root
	 * {(index) root == p || root == q}
	 * return root(p)
	 * 
	 * 3.
	 * p = 1, q = 8
	 * Inorder 		{6, 5, 7, 2, 4, 3, 0, 1, 8}
	 * 							 	^root ^p ^q
	 * Postorder	{6, 7, 4, 2, 5, 0, 8, 1, 3}
	 * 										 ^root
	 * 
	 * 3.1
	 * {(index) p > root && q > root}
	 * SubInorder 	{0, 1, 8}
	 * 					^p ^q
	 * 					^root 
	 * SubPostorder	{0, 8, 1}
	 * 					   ^root
	 * {(index) root == p || root == q}
	 * return root(p)
	 * 
	 *  
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root.val == p.val || root.val == q.val) {
			return root;
		}

		long start = System.currentTimeMillis();
		inorderTraversal(root);
		if (DEBUG)
			System.out.println("mInorder:" + mInorder + ", Time:" + (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		postorderTraversal(root);
		if (DEBUG)
			System.out.println("mPostorder:" + mPostorder + ", Time:" + (System.currentTimeMillis() - start));

		mP = p;
		mQ = q;
		mPIndex = findIndex(0, mInorder.size() - 1, mP.val);
		mQIndex = findIndex(0, mInorder.size() - 1, mQ.val);

		// if (DEBUG)
		// System.out.println("mPIndex:" + mPIndex + ", mQIndex: " + mQIndex);

		return lowestCommonAncestorInternal(root, 0, mInorder.size() - 1, 0, mPostorder.size() - 1);
	}

	/**
	 * 执行用时：345 ms, 在所有 Java 提交中击败了5.36%的用户
	 * 内存消耗：42.8 MB, 在所有 Java 提交中击败了31.19%的用户
	 */
	public TreeNode lowestCommonAncestorInternal(TreeNode root, int inorderStart, int inorderEnd, int postorderStart,
			int postorderEnd) {

		// if (DEBUG)
		// System.out.println("root" + root + ", inorderStart: " + inorderStart + ",
		// inorderEnd: " + inorderEnd
		// + ", postorderStart: " + postorderStart + ", postorderEnd: " + postorderEnd);

		if (root.val == mP.val || root.val == mQ.val) {
			return root;
		}

		int rootIndex = findIndex(inorderStart, inorderEnd, root.val);

		// if (DEBUG)
		// System.out.println("rootIndex:" + rootIndex);

		if ((mPIndex < rootIndex && rootIndex < mQIndex) || (mQIndex < rootIndex && rootIndex < mPIndex)) {
			return root;
		}

		if (mPIndex < rootIndex && mQIndex < rootIndex) {
			int length = rootIndex - inorderStart;
			int nextPostorderEnd = postorderStart + length - 1;
			TreeNode nextRoot = mPostorder.get(nextPostorderEnd);
			return lowestCommonAncestorInternal(nextRoot, inorderStart, rootIndex - 1, postorderStart,
					nextPostorderEnd);
		}

		if (rootIndex < mPIndex && rootIndex < mQIndex) {
			int length = inorderEnd - rootIndex;
			int nextPostorderEnd = postorderEnd - 1;
			int nextPostorderStart = nextPostorderEnd - (length - 1);
			TreeNode nextRoot = mPostorder.get(nextPostorderEnd);
			return lowestCommonAncestorInternal(nextRoot, rootIndex + 1, inorderEnd, nextPostorderStart,
					nextPostorderEnd);
		}

		throw new RuntimeException("rootIndex:" + rootIndex + ", mPIndex:" + mPIndex + ", mQIndex:" + mQIndex);
	}

	private void inorderTraversal(TreeNode root) {
		mInorder = new ArrayList<>();
		inorderTraversalInternal(mInorder, root);
	}

	private void inorderTraversalInternal(List<TreeNode> nodes, TreeNode node) {
		if (null == node)
			return;

		inorderTraversalInternal(nodes, node.left);
		nodes.add(node);
		inorderTraversalInternal(nodes, node.right);
	}

	private void postorderTraversal(TreeNode root) {
		mPostorder = new ArrayList<>();
		postorderTraversalInternal(mPostorder, root);
	}

	private void postorderTraversalInternal(List<TreeNode> nodes, TreeNode node) {
		if (null == node)
			return;

		postorderTraversalInternal(nodes, node.left);
		postorderTraversalInternal(nodes, node.right);
		nodes.add(node);
	}

	private int findIndex(int start, int end, int target) {
		int index = (end + start) / 2;
		for (int i = index, j = index + 1; i >= start || j <= end; i--, j++) {
			if (i >= start && mInorder.get(i).val == target) {
				return i;
			}
			if (j <= end && mInorder.get(j).val == target) {
				return j;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		// Integer[] nodes = new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
		// int p = 5, q = 1;
		// int p = 5, q = 4;
		// Integer[] nodes = new Integer[] { 1, 2 };
		// int p = 1, q = 2;
		// Integer[] nodes = new Integer[] { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
		// int p = 0, q = 8;
		// TreeNode root = TreeNode.sInitBinaryTree(nodes);

		String[] stringArray = QUtils.getStringArrayFromFile("LowestCommonAncestor");
		final Integer[] nodes = new Integer[stringArray.length];
		for (int i = 0, length = nodes.length; i < length; i++) {
			String temp = stringArray[i];
			if ("null".equals(temp)) {
				nodes[i] = null;
			} else {
				nodes[i] = Integer.valueOf(stringArray[i]);
			}
		}
		int p = 9998, q = 9999;
		TreeNode root = TreeNode.sInitBinaryTree2(nodes);

		// System.out.println("Nodes:" + Arrays.toString(nodes));
		// TreeNode.sPrintTree(root);

		LowestCommonAncestor solution = new LowestCommonAncestor();
		TreeNode node = solution.lowestCommonAncestor(root, new TreeNode(p), new TreeNode(q));

		System.out.println("p:" + p + ", q:" + q);
		System.out.println("Node:" + node.val);
	}
}
