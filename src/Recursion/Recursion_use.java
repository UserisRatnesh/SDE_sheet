package Recursion;

import java.util.ArrayList;

public class Recursion_use {

  private static ArrayList<Integer> subsetSums(ArrayList<Integer> list, int n) {

    ArrayList<Integer> ans = new ArrayList<>();
    subsetSumHelper(list, n, 0, ans, 0);

    return ans;
  }

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

  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    int n = 3;
    subsetSums(list, n);
  }

}
