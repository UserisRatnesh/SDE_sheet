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


	// TC = O(n^2)
	// SC = O(1)

	public boolean isBalanced(TreeNode root) 
	{
		if(root == null)
		{
			return true;
		}
		if(root.left == null && root.right == null)
		{
			return true;
		}

		if(Math.abs(height(root.right)-height(root.left)) > 1)
		{
			return false;
		}
		boolean left = isBalanced(root.left);
		boolean right = isBalanced(root.right);
		if(left && right)
		{
			return true;
		}
		return false;


	}

	public int height(TreeNode root)
	{
		if(root == null)
		{
			return 0;
		}
		if(root.left == null && root.right == null)
		{
			return 1;
		}
		int leftH = height(root.left);
		int rightH = height(root.right);
		int h = Math.max(leftH, rightH);
		return 1+h;
	}

	// TC = O(n)
	// SC = O(1)
	// Using height function to find the balance starting from bottom
	public boolean isBalancedBetter(TreeNode root) 
	{
		return heightBetter(root) != -1;
	}

	public int heightBetter(TreeNode root)
	{
		if(root == null)
		{
			return 0;
		}
		if(root.left == null && root.right == null)
		{
			return 1;
		}
		int leftH = heightBetter(root.left);

		if(leftH == -1){
			return -1;
		}
		int rightH = heightBetter(root.right);

		if(rightH == -1){
			return -1;
		}

		if(Math.abs(leftH - rightH) > 1){
			return -1;
		}
		int h = Math.max(leftH, rightH);
		return 1+h;
	}




	public static void main(String[] args) {

	}

}
