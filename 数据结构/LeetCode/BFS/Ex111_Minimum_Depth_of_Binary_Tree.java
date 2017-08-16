package LeetCode.BFS;

import java.util.Stack;

/**
 Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node
 down to the nearest leaf node.
 */
//宽度优先搜索
public class Ex111_Minimum_Depth_of_Binary_Tree {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> stack  = new Stack<TreeNode>();

        TreeNode cur = null;
        int minH = 1;
        stack.push(root);


        while(!stack.isEmpty()){
            cur = stack.pop();
            if(cur.left != null){
                stack.push(cur.left);
            }else {
                return minH;
            }
            if(cur.right != null){
                stack.push(cur.right);
            }else {
                return minH;
            }
        }
        return minH;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
