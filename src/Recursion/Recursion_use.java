package Recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
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

  private static void subsetHelper(int[] nums, int n, int i, List<Integer> list, List<List<Integer>> subsets) 
  {
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

  // TC = O(2^n * n^2)
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

  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(2);
    list.add(3);
    int[] nums = {1,2,2,2,3,3};
    int n = 3;
    System.out.println(subsetsWithoutDuplicate(nums));
  }

}
