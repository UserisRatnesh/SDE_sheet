package Recursion_and_Backtracking;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
	public static List<List<String>> solveNQueens(int n) {
		int[][] board = new int[n][n];
		List<List<String>> ans = new ArrayList<>();
		placeQueens(n, 0, board, ans);
		return ans;
	}

	private static void placeQueens(int n, int row, int[][] board, List<List<String>> ans) {
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

	private static boolean isSafePlace(int n, int row, int col, int[][] board) {
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


	// TC = 
	// SC = 
	private static void solveSudoku(char[][] boards)
	{
		sudokuSolveHelper(boards, 0, 0);
	}

	private static boolean sudokuSolveHelper(char[][] boards, int row, int col)
	{
		// if reached the 9th row 
		if(row == 9)
		{
			return true;
		}

		// if reached the last column
		if(col == 9)
		{
			return sudokuSolveHelper(boards, row+1, 0);
		}

		// if the given cell is filled
		if(boards[row][col] != '.')
		{
			return sudokuSolveHelper(boards, row, col+1);
		}


		// try filling each value from 1 to 9
		for(char c='1'; c<='9'; c++)
		{
			if(isPossibleToFill(c, boards, row, col))
			{
				boards[row][col] = c;
				if(sudokuSolveHelper(boards, row, col+1))
				{
					return true;
				}
				boards[row][col] = '.'; // backtrack and check for next value of i
			}
		}

		return false;	
	}

	private static boolean isPossibleToFill(char value, char[][] boards, int row, int col)
	{
		// if row or column out of range
		if(row < 0 || row >= 9 || col < 0 || col >= 9)
		{
			return false;
		}


		for(int i=0; i<9; i++)
		{
			// check for particular row
			if(boards[i][col] == value)
			{
				return false;
			}
			// check for column 
			if(boards[row][i] == value)
			{
				return false;
			}

			// check for small triangle
			if(boards[3*(row/3) + i/3][3*(col/3) + i%3] == value)
			{
				return false;
			}

		}
		return true;
	}


	// TC = O(n^2 * m^n) => one color has n options
	// SC = O(n+m) extra memory
	private static boolean graphColoring(boolean graph[][], int n, int e, int m)
	{
		if(m >= n)
		{
			return true;
		}

		HashMap<Integer, Integer> colorMap = new HashMap<>();

		return graphColoringHelper(0, graph, n, e, m, colorMap);
	}

	private static boolean graphColoringHelper(int node, boolean graph[][], int n, int e, int m, HashMap<Integer, Integer> colorMap)
	{

		// base case
		if(node == n)
		{
			return true;
		}

		// iterate over padoshi of present node
		// maintain colors used by padoshi 
		// assign this node different color from its padodhi
		// if no any color left then return false


		boolean[] color = new boolean[m+1];
		for(int i=0; i<n; i++)
		{
			if(i == node)
			{
				continue;
			}
			if(graph[node][i] && colorMap.containsKey(i))
			{
				color[colorMap.get(i)] = true;
			}
		}


		for(int i=1; i<=m; i++)
		{
			if(color[i] == false)
			{
				// assign this color to present node
				colorMap.put(node, i);
				if(graphColoringHelper(node+1, graph, n, e, m, colorMap))
				{
					return true;
				}

				colorMap.remove(node); // Backtrack
			}
		}


		return false;

	}

	// TC = O(4^(n*n))
	// SC = O(n^2 + n^2) => one for recursive stack used and other for pathTaken
	public static ArrayList<String> findPathRatInMaze(int[][] m, int n) {
        // Your code here
        ArrayList<String> ansList = new ArrayList<>();
        int[][] pathTaken = new int[n][n];
        ratInMaze(0, 0, m, n, pathTaken, new StringBuilder(), ansList);
        return ansList;
    }

	private static void ratInMaze(int row, int col, int[][] matrix, int n, int[][] pathTaken, StringBuilder ans, ArrayList<String> ansList)
	{
		// base case
		if(row == n-1 && col == n-1)
		{
			ansList.add(ans.toString());
			return;
		}
		
		if(matrix[row][col] == 0) // pruning
		{
		    // path not possible
		    return;
		}
		
		
		// left
		if(col-1 >= 0 && matrix[row][col-1] == 1 && pathTaken[row][col] == 0)
		{
			ans.append("L");
			pathTaken[row][col] = 1;
			ratInMaze(row, col-1, matrix, n, pathTaken, ans, ansList);
			pathTaken[row][col] = 0; // backtrack
			ans.deleteCharAt(ans.length()-1); // backtrack in string 
		}
		
		// right
		if(col+1 < n && matrix[row][col+1] == 1 && pathTaken[row][col] == 0)
		{
			ans.append("R");
			pathTaken[row][col] = 1;
			ratInMaze(row, col+1, matrix, n, pathTaken, ans, ansList);
			pathTaken[row][col] = 0; // backtrack
			ans.deleteCharAt(ans.length()-1); // backtrack in string 
		}
		
		// down
		if(row+1 < n && matrix[row+1][col] == 1 && pathTaken[row][col] == 0)
		{
			ans.append("D");
			pathTaken[row][col] = 1;
			ratInMaze(row+1, col, matrix, n, pathTaken, ans, ansList);
			pathTaken[row][col] = 0; // backtrack
			ans.deleteCharAt(ans.length()-1); // backtrack in string 
		}
		
		// up
		if(row-1 >=0 && matrix[row-1][col] == 1 && pathTaken[row][col] == 0)
		{
			ans.append("U");
			pathTaken[row][col] = 1;
			ratInMaze(row-1, col, matrix, n, pathTaken, ans, ansList);
			pathTaken[row][col] = 0; // backtrack
			ans.deleteCharAt(ans.length()-1); // backtrack in string 
		}
		
		
	}
	
	
	
	private static ArrayList<String> wordBreak(String s, ArrayList<String> dictionary)
	{
		Set<String> mapDict = new HashSet<>(dictionary);
		
		ArrayList<String> ansList = new ArrayList<>();
		wordBreakHelper(s, mapDict, ansList, "");
		return ansList;
	}
	
	private static void wordBreakHelper(String s, Set<String> mapDict, ArrayList<String> ansList, String ansString)
	{
		if(s.length() == 0)
		{
			ansList.add(ansString);
			return;
		}
		
		int l = s.length();
		
		for(int i=1; i<=l; i++)
		{
			if(mapDict.contains(s.substring(0, i))) 
			{
				wordBreakHelper(s.substring(i), mapDict, ansList, ansString+s.substring(0,i));
			}
		}
	}
	
	

	public static void main(String[] args) 
	{

	}

}
