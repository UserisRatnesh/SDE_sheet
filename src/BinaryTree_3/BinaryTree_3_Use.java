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
	
	
	//Function to check whether all nodes of a tree have the value 
    //equal to the sum of their child nodes.
	// TC = O(n)
    public static int isSumProperty(TreeNode root)
    {
        if(root == null || (root.left == null && root.right == null)){
            return 1;
        }
        
        int rootData = root.val;
        int rootRightData = root.right == null ? 0 : root.right.val;
        int rootLeftData = root.left == null ? 0 : root.left.val;
        
        if(rootData == (rootRightData + rootLeftData) && isSumProperty(root.left) == 1 && isSumProperty(root.right) == 1){
            return 1;
        }
        
        return 0;
        
    }
    
    // Function to make the binary tree follow sumProperty
    public static void convertTreeToFollowSumProperty(TreeNode root)
    {
        if(root == null){
            return;
        }
        
        int childSum = 0;
        childSum += root.left == null ? 0 : root.left.val;
        childSum += root.right == null ? 0 : root.right.val;
        if(childSum >= root.val) {
        	root.val = childSum;
        }
        else {
        	// make both child equal to sum
        	if(root.left != null)	root.left.val = root.val;
        	if(root.right != null)	root.right.val = root.val;
        }
        
        convertTreeToFollowSumProperty(root.left);
        convertTreeToFollowSumProperty(root.right);
        
        // Now update the root while tracking back
        childSum = 0;
        childSum += root.left == null ? 0 : root.left.val;
        childSum += root.right == null ? 0 : root.right.val;
        
        // Since we have increased the child values it is not possible to have root.val < childSum
        // It can be greater or equal only
        if(root.left != null || root.right != null) {
        	root.val = childSum;
        }
        // because if both are null then it is a leaf node 
        // And we will end up assigning zero to every leaf node
        // But we do not need to do anything with leaf node because it always satisfies the condition whatever the value it has
        
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
