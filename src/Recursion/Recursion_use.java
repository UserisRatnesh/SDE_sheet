package Recursion;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Recursion_use {

  private static ArrayList<Integer> subsetSums(ArrayList<Integer> list, int n) {

    ArrayList<Integer> ans = new ArrayList<>();
    subsetSumHelper(list, n, 0, ans, 0);

    return ans;
  }

  // TC = O(2^n)
  private static void subsetSumHelper(ArrayList<Integer> list, int n, int i, ArrayList<Integer> ans, int sumTillNow) {
    if (i == n) {
      ans.add(sumTillNow);
      return;
    }
    // Take
    subsetSumHelper(list, n, i + 1, ans, sumTillNow + list.get(i));

    // Not take
    subsetSumHelper(list, n, i + 1, ans, sumTillNow);

  }

  public static List<List<Integer>> subsetsWithoutDuplicate(int[] nums) {

    int n = nums.length;
    Arrays.sort(nums);
    List<List<Integer>> ans = new ArrayList<>();
    subsetHelper(nums, n, 0, new ArrayList<>(), ans);

    return ans;

  }

  // TC = O(2^n * k)
  private static void subsetHelper(int[] nums, int n, int i, List<Integer> list, List<List<Integer>> subsets) {
    // base case
    if (i == n) {
      subsets.add(list);
      return;
    }

    subsets.add(list);

    // iterate over the nums array
    // and pick the one which has not been picked yet for this size
    // since array is sorted it will work to avoid picking the same element twice
    // for the same size of subset
    for (int index = i; index < n; index++) {
      List<Integer> newList = new ArrayList<>(list);
      newList.add(nums[index]);
      subsetHelper(nums, n, index + 1, newList, subsets);
      while (index + 1 < n && nums[index] == nums[index + 1]) {
        index++;
      }
    }
  }

  // TC = O(2^n * n^2) ?
  public List<List<Integer>> subsetWithoutDuplicateHelper(int[] input, int si) {
    if (si == input.length) {
      List<List<Integer>> ans = new ArrayList<>();
      List<Integer> arr = new ArrayList<>();
      ans.add(arr);
      return ans;
    }

    List<List<Integer>> output = new ArrayList<>();
    List<List<Integer>> smallOutput = subsetWithoutDuplicateHelper(input, si + 1);

    for (List<Integer> list : smallOutput) {
      ArrayList<Integer> newList = new ArrayList<>(list); // O(n)
      newList.add(input[si]);
      output.add(list);
      output.add(newList);

    }
    return output;
  }
  
  
  public List<List<Integer>> combinationSum(int[] candidates, int target) 
  {
      List<Integer> list = new ArrayList<>();
      List< List < Integer > > ans = new ArrayList<>();
      helper(candidates, 0, target, list , ans);
      return ans;
  }

  // TC = O(2^t * k)
  // SC = O(number of combinations possible)
  private void helper(int[] arr, int i, int target, List<Integer> list, List<List<Integer>> ans)
  {
      if(target == 0)
      {
          ans.add(list);
          return;
      }
      if(target < 0 || i == arr.length)
      {
          return;
      }


      // not take
      helper(arr, i+1, target, list, ans);

      // take 
      List<Integer> newList = new ArrayList<>(list); // O(k) -> k = length of list
      newList.add(arr[i]);
      helper(arr, i, target-arr[i], newList, ans); // do not increament i since multiple takes of same element is possible
  }
  
  
  public List<List<Integer>> combinationSum2(int[] candidates, int target) 
  {
      int n = candidates.length;
      List<List<Integer>> ans = new ArrayList<>();
      Arrays.sort(candidates);
      helper(candidates, 0, n, target, new ArrayList<>(), ans);
      return ans;
  }


  // TC = O(2^n * k)
  // SC = O(k*x) -> i.e x = number of combinations, k = avg. length of each combinations
  private void helper(int[] candidates, int i, int n, int target, List<Integer> list, List<List<Integer>> ans)
  {
      if(target == 0)
      {
          ans.add(list);
          return;
      }

      if(i >= n || target < 0)
      {
          return;
      }

      for(int index = i; index<n; index++)
      {
          List<Integer> newList = new ArrayList<>(list);
          int ele = candidates[index];
          newList.add(ele);
          helper(candidates, index+1, n, target-ele, newList, ans);
          while(index+1 < n && candidates[index+1] == candidates[index])
          {
              index++;
          }
      }

  }

  public static void main(String[] args) {

    int[] nums = { 1, 2, 2, 2, 3, 3 };
    System.out.println(subsetsWithoutDuplicate(nums));
  }

}
