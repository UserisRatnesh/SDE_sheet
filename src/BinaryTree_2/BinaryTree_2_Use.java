package BinaryTree_2;

import java.util.ArrayList;
import java.util.List;

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

		if(Math.abs(getHeight(root.right)-getHeight(root.left)) > 1)
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


	// TC = O(n)
	// SC = O(1)
	// Using height function to find the balance starting from bottom
	public boolean isBalancedBetter(TreeNode root) 
	{
		return heightBetter(root) != -1;
	}

	// Finding height and checking if tree is balanced or not in the same one go
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


	// Brute force of finding height of binary tree
	// TC = O(n^2)

	public int diameterOfBinaryTree(TreeNode root) 
	{
		if(root == null)
		{
			return 0;
		}

		int left = getHeight(root.left);
		int right = getHeight(root.right);
		int d = left+right;
		int leftD = diameterOfBinaryTree(root.left);
		int rightD = diameterOfBinaryTree(root.right);

		return Math.max(Math.max(leftD, rightD), d);

	}

	// Better approach 
	// While calculating height of tree try to find width of that node there only
	// Now max[0] will return the diameter of binary tree

	// TC = O(n)
	public int diameterOfBinaryTreeBetter(TreeNode root, int[] max) 
	{
		if(root == null)
		{
			return 0;
		}

		int left = diameterOfBinaryTreeBetter(root.left, max);
		int right = diameterOfBinaryTreeBetter(root.right, max);

		max[0] = Math.max(max[0], left+right);

		return 1+ Math.max(left, right);

	}

	// TC = O(n)
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null)
		{
			return true;
		}
		if(p == null || q == null)
		{
			return false;
		}

		// if both are not null
		return ((p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));

	}

	// TC = O(n)
	// SC = O(2*n)
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		List<TreeNode> pathP = new ArrayList<>();
		List<TreeNode> pathQ = new ArrayList<>();

		boolean isPathP = getPath(root, p, pathP);
		boolean isPathQ = getPath(root, q, pathQ);

		TreeNode ans = null;
		if(isPathP && isPathQ){

			int minL = Math.min(pathP.size(), pathQ.size());
			for(int i=0; i<minL; i++){
				if(pathP.get(i).val == pathQ.get(i).val)
				{
					ans = pathP.get(i);
				}
				else{
					break;
				}
			}
		}

		return ans;


	}

	// TC = O(n)
	public boolean getPath(TreeNode root, TreeNode dest, List<TreeNode> path){
		if(root == null){
			return false;
		}

		if(root.val == dest.val){
			path.add(root);
			return true;
		}

		path.add(root);

		if(getPath(root.left, dest, path))
		{
			return true;
		}

		if(getPath(root.right, dest, path))
		{
			return true;
		}
		path.remove(path.size()-1);

		return false;
	}
	
	
	// TC = O(n)
    // SC = O(1)
    public TreeNode lowestCommonAncestorOptimal(TreeNode root, TreeNode p, TreeNode q) {
        
        if(root == null || root == p || root == q)
        {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null){
            return right;
        }else if(right == null){
            return left;
        }
        else{
            return root;
        }

    }


	public static void main(String[] args) {

	}

}
