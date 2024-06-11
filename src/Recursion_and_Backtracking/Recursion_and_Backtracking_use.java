package Recursion_and_Backtracking;

import java.util.List;
import java.util.ArrayList;

public class Recursion_and_Backtracking_use 
{
	
	public static List<List<Integer>> permute(int[] nums) 
    {
		int n = nums.length;
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<n; i++)
		{
			list.add(nums[i]);
		}
		
		List<List<Integer>> ans = new ArrayList<>();
		
		permuteHelper(list, new ArrayList<>(), ans);
        
		return ans;
    }
	
	private static void permuteHelper(List<Integer> numsList, List<Integer> list, List<List<Integer>> ans)
	{
		int n = numsList.size();
		if(n == 0)
		{
			ans.add(list);
			return;
		}
		
		for(int i=0; i<n; i++)
		{
			int ele = numsList.get(i);
			List<Integer> newList = new ArrayList<>(list);
			newList.add(ele);
			List<Integer> newNumList = new ArrayList<>(numsList);
			newNumList.remove(i);
			permuteHelper(newNumList, newList, ans);
		}
	}

	public static void main(String[] args) 
	{
		int[] nums = {1,2,3};
		System.out.println(permute(nums));
	}

}
