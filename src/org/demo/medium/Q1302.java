package org.demo.medium;

import org.demo.TreeNode;

/**
 * 1302. 层数最深叶子节点的和
 * 
 * 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 * 
 * 示例 1：
 * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * 输出：15
 * 
 * 示例 2：
 * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * 输出：19
 * 
 * 提示：
 * 树中节点数目在范围 [1, 104] 之间。
 * 1 <= Node.val <= 100
 *
 * https://leetcode.cn/problems/deepest-leaves-sum/
 */
public class Q1302 {	
	static class Solution {
	    int deepest = 0;
	    int result = 0;
	    public int deepestLeavesSum(TreeNode root) {
	        findDeepest(root, 0);
	        return result;
	    }

	    private void findDeepest(TreeNode node, int deep){
	        if(null==node){
	            return;
	        }
	        if(deep==deepest){
	            result += node.val;
	        }else if(deep>deepest){
	        	deepest = deep;
	            result = node.val;
	        }

	        findDeepest(node.left, deep+1);
	        findDeepest(node.right, deep+1);
	    }
	}
	
	public static void main(String[] args) {
		int[] values = new int[] {2,3,4,5,-1,6,7,-1,-1,-1,-1,8};
		TreeNode root = new TreeNode(1);
		TreeNode[] parent = new TreeNode[] {root};
		int deep = 1;
		for(int i=0, length = values.length, ni=0 ;i<length;) {
			TreeNode[] childs = new TreeNode[(int)Math.pow(2, deep)];
			for(int j=0,k=0,size=parent.length;j<size;j++) {
				TreeNode temp = parent[j];
				if (null != temp) {
					int value = values[i++];
					TreeNode child = (-1 == value) ? null : new TreeNode(value);
					childs[k++] = child;
					temp.left = child;

					value = values[i++];
					child = (-1 == value) ? null : new TreeNode(value);
					childs[k++] = child;
					temp.right = child;
				}

				if (j == size - 1) {
					parent = childs;
					deep++;
				}
			}
		}
		
		Solution solution = new Solution();
		System.out.println("{1,2,3,4,5,null,6,7,null,null,null,null,8} - 15:"+solution.deepestLeavesSum(root));
		
	}
}
