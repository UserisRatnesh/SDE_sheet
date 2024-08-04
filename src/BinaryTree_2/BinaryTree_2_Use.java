package BinaryTree_2;


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

public class BinaryTree_2_Use {
	
	
	// TC = O(n)
	// SC = O(n) stack memory due to recursion
	
	// NOTE = We can also use level order traversal to find the depth i.e height of binary tree
	public static int getHeight(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) return 1;

        int left = 1 + getHeight(root.left);
        int right = 1 + getHeight(root.right);

        return Math.max(left, right);
    }

	public static void main(String[] args) {

	}

}
