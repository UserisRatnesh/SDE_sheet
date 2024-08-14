package BST_2;

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


public class BST_2_Use {


	// In order traversal
	// TC = O(n)
	// SC = O(n) -> for stack tree
	public static void findFloorInBST(TreeNode root, int x, int[] ans){
		if(root == null)    return;


		findFloorInBST(root.left, x, ans);

		if(root.val <= x){
			ans[0] = root.val;
		}
		findFloorInBST(root.right, x, ans);
	}

	// TC = O(n)
	// SC = O(1)
	public static int findFloorInBSTBetter(TreeNode root, int X) {
		int ans = -1;
		TreeNode temp = root;
		while(temp != null){

			if(temp.val == X){
				ans = temp.val;
				break;
			}
			if(temp.val > X){
				temp = temp.left;
			}
			else{
				ans = Math.max(ans, temp.val);
				temp = temp.right;
			}
		}

		return ans;
	}


	public  static int findCeil(TreeNode node, int x) {

		if(node == null)    return -1;

		int ans = Integer.MAX_VALUE;
		TreeNode temp = node;
		while(temp != null){
			if(temp.val == x){
				ans = temp.val;
				break;
			}

			if(temp.val < x){
				temp = temp.right;
			}else{
				ans = Math.min(ans, temp.val);
				temp = temp.left;
			}
		}

		if(ans == Integer.MAX_VALUE){
			return -1;
		}

		return ans;
	}
	
	// TC = O(n)
	// Finding floor and ceil in one go 
	public List<Integer> floorCeilOfBST(TreeNode root, int key) {

        List<Integer> ans = new ArrayList<>();
        if(root == null)    return ans;

        TreeNode temp = root;
        int floor = -1;
        int ceil = Integer.MAX_VALUE;
        
        while(temp != null){
            if(temp.val == key){
                floor = key;
                ceil = key;
                break;
            }

            if(temp.val < key){
                floor = Math.max(floor, temp.val);
                temp = temp.right;
            }else{
                ceil = Math.min(ceil, temp.val);
                temp = temp.left;
            }
        }

        ans.add(floor);
        if(ceil == Integer.MAX_VALUE){
            ans.add(-1);
        }else{
            ans.add(ceil);
        }

        return ans;
        
    }



	public static void main(String[] args) {
		
		
	}

}
