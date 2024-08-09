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


	// TC = O(n) => reach every child only once
	public static void mirror(TreeNode node) {

		if(node == null){
			return;
		}
		TreeNode newRoot = new TreeNode(node.val);
		newRoot.right = getChild(node.left);
		newRoot.left = getChild(node.right);

		node.left = newRoot.left;
		node.right = newRoot.right;

		return;

	}

	public static TreeNode getChild(TreeNode root){
		if(root == null){
			return null;
		}

		TreeNode newRoot = new TreeNode(root.val);
		newRoot.left = getChild(root.right);
		newRoot.right = getChild(root.left);

		return newRoot;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
