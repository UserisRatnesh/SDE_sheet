package Recursion_and_Backtracking;

import java.util.List;
import java.util.ArrayList;

public class Recursion_and_Backtracking_use 
{

	// TC = O(n * n!)
	// SC = O(n!) => we can reduce it by using a map and a boolean array to maintain
	// which element is picked till now.
	public static List<List<Integer>> permute(int[] nums) {
		int n = nums.length;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(nums[i]);
		}

		List<List<Integer>> ans = new ArrayList<>();

		permuteHelper(list, new ArrayList<>(), ans);

		return ans;
	}

	private static void permuteHelper(List<Integer> numsList, List<Integer> list, List<List<Integer>> ans) {
		int n = numsList.size();
		if (n == 0) {
			ans.add(list);
			return;
		}

		for (int i = 0; i < n; i++) {
			int ele = numsList.get(i);
			List<Integer> newList = new ArrayList<>(list);
			newList.add(ele);
			List<Integer> newNumList = new ArrayList<>(numsList);
			newNumList.remove(i);
			permuteHelper(newNumList, newList, ans);
		}
	}

	// TC = O(n! * n)
	public List<List<String>> solveNQueens(int n) {
		int[][] board = new int[n][n];
		List<List<String>> ans = new ArrayList<>();
		placeQueens(n, 0, board, ans);
		return ans;
	}

	private void placeQueens(int n, int row, int[][] board, List<List<String>> ans) {
		if (row == n) {
			List<String> arrList = new ArrayList<>();
			for (int[] arr : board) {
				String s = "";
				for (int x : arr) {
					if (x == 1) {
						s += 'Q';
					} else {
						s += '.';
					}
				}
				arrList.add(s);
			}
			ans.add(arrList);
			return;

		}

		for (int col = 0; col < n; col++) {
			if (isSafePlace(n, row, col, board)) {
				board[row][col] = 1;
				placeQueens(n, row + 1, board, ans);
				board[row][col] = 0;
			}
		}
	}

	private boolean isSafePlace(int n, int row, int col, int[][] board) {
		if (row >= n || row < 0 || col >= n || col < 0) {
			return false;
		}

		// vertical check
		for (int i = 0; i < row; i++) {
			if (board[i][col] == 1) {
				return false;
			}
		}

		// diagonal check
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		// 2nd diagonal check
		for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		System.out.println(permute(nums));
	}

}
