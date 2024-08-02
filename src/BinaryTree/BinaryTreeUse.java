package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
public class BinaryTreeUse {

	
	// TC = O(n)
	// SC = O(n) And no space for stack since not recursive call
	public static List<Integer> MorrisPreorderTraversal(TreeNode root){

		List<Integer> preorder = new ArrayList<>();
		if(root == null)	return preorder;

		TreeNode temp = root;
		while(temp != null) {

			if(temp.left == null) {
				preorder.add(temp.val);
				temp = temp.right;
			}
			else {

				// Go to right most child of left subtree and thread it to temp

				TreeNode prev = temp.left;
				while(prev.right != null && prev.right != temp) {
					prev = prev.right;
				}

				if(prev.right == null) {

					// Reached the right most child
					// Thread it to temp
					prev.right = temp;
					preorder.add(temp.val);
					temp = temp.left;

				}else {

					// It means this portion is already traversed since prev.right == temp
					prev.right = null;
					// Jump to right section
					temp = temp.right;
				}

			}
		}

		return preorder;
	}

	// TC = O(n)
	// SC = O(n) And no space for stack since not recursive call
	public static List<Integer> MorrisInorderTraversal(TreeNode root){

		List<Integer> inorder = new ArrayList<>();
		if(root == null)	return inorder;

		TreeNode temp = root;
		while(temp != null) {

			if(temp.left == null) {
				inorder.add(temp.val);
				temp = temp.right;
			}
			else {

				// Go to right most child of left subtree and thread it to temp

				TreeNode prev = temp.left;
				while(prev.right != null && prev.right != temp) {
					prev = prev.right;
				}

				if(prev.right == null) {

					// Reached the right most child
					// Thread it to temp
					prev.right = temp;
					temp = temp.left;

				}else {

					// It means this portion is already traversed since prev.right == temp
					prev.right = null;
					
					inorder.add(temp.val);
					// Jump to right section
					temp = temp.right;
				}

			}
		}

		return inorder;
	}
	
	
	// Doing level order traversal and storing first element at every level
	
	// TC = O(n)
	// SC = O(n)
	public static ArrayList<Integer> leftView(TreeNode root)
    {
        
        ArrayList<Integer> list = new ArrayList<>();
        
        if(root == null) return list;
      
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()){
            int size = que.size();
            boolean first = true;
            while(size > 0){
            	TreeNode node = que.poll();
                if(first){
                    list.add(node.val);
                    first = false;
                }
                if(node.left != null){
                    que.add(node.left);
                }
                if(node.right != null){
                    que.add(node.right);
                }
                size--;
            }
        }
    
        return list;
    }
	
	// TC = O(n)
	public static List<Integer> leftViewBetter(TreeNode root){
		
		List<Integer> list = new ArrayList<>();
		
		leftViewhelper(root, list, 0);
		
		return list;
	}
	public static void leftViewhelper(TreeNode root, List<Integer> list, int level) {
		if(root == null)	return;
		
		if(list.size() == level) {
			list.add(root.val);
		}
		
		
		leftViewhelper(root.left, list, level+1);
		leftViewhelper(root.right, list, level+1);
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
