package BST;

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

class Output{
	int max;
	int min;
	boolean isBST;
	public Output(int max, int min, boolean isBST){
		this.max = max;
		this.min = min;
		this.isBST = isBST;
	}
}

public class BST_Use {


	// TC = O(n^2)
	public static TreeNode bstFromPreorder(int[] preorder) {
		int n = preorder.length;
		if(n == 0){
			return null;
		}

		return helperbstFromPreorder(preorder, 0, n-1);
	}

	public static TreeNode helperbstFromPreorder(int[] preorder, int si, int ei){
		if(si > ei){
			return null;
		}

		int n = preorder.length;

		int rootData = preorder[si];
		TreeNode root = new TreeNode(rootData);

		// find the index where ele is greater than rootData
		int i = si+1;
		while(i < n && preorder[i] < preorder[si])
		{
			i++;
		}

		int siL = si+1;
		int eiL = i-1;
		int siR = i;
		int eiR = ei;


		root.left = helperbstFromPreorder(preorder, siL, eiL);

		if(i == n){
			root.right = null;
		}else{
			root.right = helperbstFromPreorder(preorder, siR, eiR);
		}


		return root;
	}


	// TC = O(n)
	// Using extra output class
	public static boolean isValidBST(TreeNode root) 
	{
		if(root == null){
			return true;
		}
		Output ans = helperisValidBST(root);
		return ans.isBST;
	}

	public static Output helperisValidBST(TreeNode root)
	{
		if(root.left == null && root.right == null){
			return new Output(root.val, root.val, true);
		}

		int min = root.val;
		int max = root.val;
		boolean isBST = true;

		if(root.left != null){
			Output left = helperisValidBST(root.left);
			min = Math.min(min, left.min);
			max = Math.max(max, left.max);

			if(root.val <= left.max || !left.isBST){
				isBST = false;
			}
		}

		if(root.right != null){
			Output right = helperisValidBST(root.right);
			min = Math.min(min, right.min);
			max = Math.max(max, right.max);

			if(root.val >= right.min || !right.isBST){
				isBST = false;
			}
		}

		return new Output(max, min, isBST);

	}


	// TC = O(n)
	// SC = O(n)
	public static boolean isValidBSTBetter(TreeNode root) {

		List<Integer> inorder = new ArrayList<>();

		inorderTraversal(root, inorder);

		for(int i=1; i<inorder.size(); i++) {
			if(inorder.get(i) <= inorder.get(i-1))
			{
				return false;
			}
		}

		return true;
	}

	public static void inorderTraversal(TreeNode root, List<Integer> list) {
		if(root == null) {
			return ;
		}

		inorderTraversal(root.left, list);
		list.add(root.val);
		inorderTraversal(root.right, list);
	}


	// TC = O(n)
	public boolean isValidBSTOptimal(TreeNode root) 
	{
		return helperIsValidBSTOptimal(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean helperIsValidBSTOptimal(TreeNode root, long min, long max){
		if(root == null){
			return true;
		}

		if(root.val <= min || root.val >= max ){
			return false;
		}

		boolean left = helperIsValidBSTOptimal(root.left, min, root.val);

		boolean right = helperIsValidBSTOptimal(root.right, root.val, max);

		return left && right;
	}
	
	
	// TC = O(n)
	// Simple In-order traversal
	public static void findPreSuc(TreeNode root, TreeNode[] pre, TreeNode[] suc, int key) {

		if(root == null){
			return;
		}

		findPreSuc(root.left, pre, suc, key);
		if(root.val < key){
			pre[0] = root;
		}else if(root.val > key && suc[0] == null){
			suc[0] = root;
		}

		findPreSuc(root.right, pre, suc, key);
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
