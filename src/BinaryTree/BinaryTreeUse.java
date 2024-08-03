package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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

class Pair {
	TreeNode node;
	int level;

	public Pair(TreeNode node, int level) {
		this.node = node;
		this.level = level;
	}
}

class Pair2 {
	TreeNode node;
	int state;

	public Pair2(TreeNode node, int state) {
		this.node = node;
		this.state = state;
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

	// Do level order traversal and update the vertical line value at next level if possible
	// TC = O(n)
	// SC = O(n)
	public static ArrayList<Integer> bottomView(TreeNode root) {

		TreeMap<Integer, Integer> map = new TreeMap<>();

		// Do level order traversal
		Queue<Pair> que = new LinkedList<>();
		que.add(new Pair(root, 0));

		while (!que.isEmpty()) {

			int size = que.size();
			while (size > 0) {
				Pair pair = que.poll();
				TreeNode node = pair.node;
				int level = pair.level;
				map.put(level, node.val);
				if (node.left != null) {
					que.add(new Pair(node.left, level - 1));
				}
				if (node.right != null) {
					que.add(new Pair(node.right, level + 1));
				}

				size--;
			}
		}

		ArrayList<Integer> ans = new ArrayList<>();

		while (!map.isEmpty()) {

			int key = map.firstKey();
			ans.add(map.get(key));
			map.remove(key);
		}

		return ans;
	}


	// Do level order traversal and do not update the vertical line value at next level even if possible
	// TC = O(n)
	// SC = O(n)
	public static ArrayList<Integer> topView(TreeNode root) {

		TreeMap<Integer, Integer> map = new TreeMap<>();

		Queue<Pair> que = new LinkedList<>();
		que.add(new Pair(root, 0));

		while (!que.isEmpty()) {
			int size = que.size();
			while (size > 0) {
				Pair pair = que.poll();
				TreeNode node = pair.node;
				int level = pair.level;
				if (!map.containsKey(level)) {
					map.put(level, node.val);
				}

				if (node.left != null) {
					que.add(new Pair(node.left, level - 1));
				}
				if (node.right != null) {
					que.add(new Pair(node.right, level + 1));
				}

				size--;
			}
		}

		ArrayList<Integer> ans = new ArrayList<>();
		while (!map.isEmpty()) {
			int key = map.firstKey();
			int val = map.get(key);
			ans.add(val);
			map.remove(key);
		}

		return ans;

	}
	
	
	// TC = O(3*n)
    // SC = O(3*n)
    public static List<List<Integer>> prePostInorderTraversal(TreeNode root) {
        // Write your code here.
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();  // 1 
        List<Integer> inor = new ArrayList<>(); // 2
        List<Integer> post = new ArrayList<>(); // 3

        Stack<Pair2> stk = new Stack<>();
        stk.push(new Pair2(root, 1));

        while(!stk.isEmpty()){

            Pair2 pair = stk.peek();
            TreeNode node = pair.node;
            int state = pair.state;
            if(state == 1)
            {
                pre.add(node.val);
                // update its state
                pair.state++;
                if(node.left != null){
                    stk.push(new Pair2(node.left, 1));
                }
            }else if(state == 2){
                pair.state++;
                inor.add(node.val);
                if(node.right != null){
                    stk.push(new Pair2(node.right, 1));
                }
            }else{
                post.add(node.val);
                stk.pop();
            }

        }

        list.add(inor);
        list.add(pre);
        list.add(post);

        return list;
    }
    
    
    // Second approach
    public static List<List<Integer>> prePostInorderTraversalSecond(TreeNode root) {
        // Write your code here.
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<3; i++){
            list.add(new ArrayList<>());
        }
        helper(root, list);
        return list;
    }

    public static void helper(TreeNode root, List<List<Integer>> list){
        if(root == null) return;

        list.get(1).add(root.val);
        helper(root.left, list);
        list.get(0).add(root.val);
        helper(root.right, list);
        list.get(2).add(root.val);
    }
    
    
    
    // TC = O(n)
    // SC = O( memory taken by ans and stack )
    public static ArrayList<ArrayList<Integer>> Paths(TreeNode root) {
        // code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        helperPaths(root, new ArrayList<Integer>(), ans);
        
        return ans;
    }
    
    public static void helperPaths(TreeNode root, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ans){
        if(root == null)   {
            return;
        }
        
        if(root.left == null && root.right == null){
            list.add(root.val);
            ans.add(list);
            return;
        }
        
        list.add(root.val);
        
        ArrayList<Integer> left = new ArrayList<>(list);
        
        helperPaths(root.left, left, ans);
        helperPaths(root.right, list, ans);
        
        
        
    }




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
