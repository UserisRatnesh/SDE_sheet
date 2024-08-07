package BinaryTree_3;


class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

public class BinaryTree_3_Use {
	
	
	// TC = O(n)
	// SC = O(n) recursion stack
	public int maxPathSum(TreeNode root) {

        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        helper(root, max); // Bottom up approach
        return max[0];

    }

    public int helper(TreeNode root, int[] max){
        if(root == null) return 0;

        int maxL = Math.max(0, helper(root.left, max));
        int maxR = Math.max(0, helper(root.right, max));

        max[0] = Math.max(max[0], root.val + maxL + maxR);

        // return the path having maxSum
        return root.val + Math.max(maxL, maxR);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
