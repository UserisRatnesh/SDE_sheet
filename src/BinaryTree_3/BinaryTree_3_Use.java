package BinaryTree_3;

import java.util.ArrayList;
import java.util.HashMap;

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
    
    
    // TC = O(n^2)
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTreeHelper(preorder, 0, n-1, inorder, 0, n-1);
    }

    public static TreeNode buildTreeHelper(int[] preorder, int sP, int eP, int[] inorder, int sI, int eI){
        if(sP > eP) return null;

        // root node
        TreeNode root = new TreeNode(preorder[sP]);

        // find the index of root in inorder
        int rootIndex = -1;
        for(int i=sI; i<=eI; i++){
            if(preorder[sP] == inorder[i])
            {
                rootIndex = i;
                break;
            }
        }

        int lsP = sP + 1;
        int reP = eP;

        int lsI = sI;
        int leI = rootIndex - 1;
        int rsI = rootIndex + 1;
        int reI = eI;

        int leP = lsP + (leI - lsI);
        int rsP = leP + 1;

        root.left = buildTreeHelper(preorder, lsP, leP, inorder, lsI, leI);
        root.right = buildTreeHelper(preorder, rsP, reP, inorder, rsI, reI);

        return root;

    }
    
    // TC = O(n)
    // SC = O(n)
    public static TreeNode buildTreeBetter(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++){
            map.put(inorder[i], i);
        }
        return buildTreeHelperBetter(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
    }

    public static TreeNode buildTreeHelperBetter(int[] preorder,int siP, int eiP, int[] inorder, int siI, int eiI, HashMap<Integer, Integer> map) 
    {
        if(siP > eiP)
        {
            return null;
        }
        int rootData = preorder[siP];
        TreeNode root = new TreeNode(rootData);
        
        // use map to get the root index since values are unique
        int rootIndex = map.get(rootData);

        int siPLeft = siP +1;
        int eiPRight = eiP;
        int siILeft = siI;
        int eiILeft = rootIndex-1;
        int siIRight = rootIndex +1;
        int eiIRight = eiI;

        int lengthOfLeftSubtree = eiILeft - siILeft +1;

        int eiPLeft = siPLeft + lengthOfLeftSubtree - 1; // conceptual
        int siPRight = eiPLeft+1; // conceptual

        root.left = buildTreeHelperBetter(preorder, siPLeft, eiPLeft, inorder, siILeft, eiILeft, map);
        root.right = buildTreeHelperBetter(preorder, siPRight, eiPRight, inorder, siIRight, eiIRight, map);
        return root;

    }
    
    
    // TC = O(n)
    // SC = O(n)
    public static TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = inorder.length;
        for(int i=0; i<n; i++){
            map.put(inorder[i], i);
        }

        return buildTreeInPostHelper(inorder, 0, n-1, postorder, 0, n-1, map);
    }

    public static TreeNode buildTreeInPostHelper(int[] inorder, int sI, int eI, int[] postorder, int sP, int eP, HashMap<Integer, Integer> map)
    {
        if(sI > eI){
            return null;
        }

        int rootData = postorder[eP];
        TreeNode root = new TreeNode(rootData);

        int rootIndex = map.get(rootData);

        int lsP = sP;
        int reP = eP-1;

        int lsI = sI;
        int leI = rootIndex - 1;
        int rsI = rootIndex + 1;
        int reI = eI;

        int leP = lsP + (leI - lsI);
        int rsP = leP + 1;

        root.left = buildTreeInPostHelper(inorder, lsI, leI, postorder, lsP, leP, map);
        root.right = buildTreeInPostHelper(inorder, rsI, reI, postorder, rsP, reP, map);

        return root;
    }
    
    
    // flatten the binary tree
    // TC = O(n)
    // SC = O(n)
    public static void flatten(TreeNode root) 
    {
        ArrayList<TreeNode> arr = preorder(root, new ArrayList<TreeNode>());
        for(int i=0; i<arr.size()-1; i++)
        {
            arr.get(i).right = arr.get(i+1);
            arr.get(i).left = null;
        }
        
    }

    public static ArrayList<TreeNode> preorder(TreeNode root, ArrayList<TreeNode> arr)
    {
        if(root == null)
        {
            return arr;
        }
        if(root.left == null && root.right == null)
        {
            arr.add(root);
            return arr;
        }

        arr.add(root);
        arr = preorder(root.left, arr);
        arr = preorder(root.right, arr);
        return arr;
    }
    
    // TC = O(n)
    public void flattenBetter(TreeNode root) 
    {
        TreeNode[] prev = new TreeNode[1];
        prev[0] = null;
        flattenHelper(root, prev);
        
    }

    public void flattenHelper(TreeNode root, TreeNode[] prev){
        if(root == null)    return;

        flattenHelper(root.right, prev);
        flattenHelper(root.left, prev);

        root.right = prev[0];
        root.left = null;
        prev[0] = root;

    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
