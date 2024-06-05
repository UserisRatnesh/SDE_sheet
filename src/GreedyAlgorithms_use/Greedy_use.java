package GreedyAlgorithms_use;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

class Job {
  int id, profit, deadline;

  public Job(int id, int profit, int deadline) {
    this.id = id;
    this.profit = profit;
    this.deadline = deadline;
  }
}

public class Greedy_use {

  // This is the brute force solution to find max
  // meetings that can be can held
  // TC = O(n*long n + n* n!)
  public static int maxMeetings(int start[], int end[], int n) {
    mergeSort(start, end, 0, n - 1);

    int ans = 0;
    for (int i = 0; i < n; i++) {
      int temp = helper(start, end, i, n, 1);
      ans = Math.max(ans, temp);
    }

    return ans;
  }

  // TC = O(n!)
  private static int helper(int start[], int end[], int i, int n, int meetings) {
    int ans = meetings;

    for (int j = i + 1; j < n; j++) {
      // if ith meeting is held
      if (end[i] < start[j]) {
        int temp = helper(start, end, j, n, meetings + 1);
        ans = Math.max(temp, ans);
      }

    }

    return ans;
  }

  // Sort the start and end arrays according to increasing order of their start
  // time
  // is start time is same then keep the one having small end time

  private static void mergeSort(int start[], int end[], int l, int r) {

    if (l == r) {
      return;
    }
    int mid = (l + r) / 2;

    mergeSort(start, end, l, mid);
    mergeSort(start, end, mid + 1, r);
    merge(start, end, l, mid, r);
  }

  private static void merge(int start[], int end[], int l, int mid, int r) {

    // merge the sorted parts
    int[] arr1 = new int[r - l + 1];
    int[] arr2 = new int[r - l + 1];
    int index1 = 0;
    int index2 = 0;
    int left = l;
    int right = mid + 1;

    while (left <= mid && right <= r) {
      if (start[left] < start[right]) {
        arr1[index1++] = start[left];
        arr2[index2++] = end[left];
        left++;
      } else if (start[left] > start[right]) {
        arr1[index1++] = start[right];
        arr2[index2++] = end[right];
        right++;
      } else // if both are equal
      {
        if (end[left] < end[right]) {
          arr1[index1++] = start[left];
          arr2[index2++] = end[left];
          left++;
        } else {
          arr1[index1++] = start[right];
          arr2[index2++] = end[right];
          right++;
        }
      }
    }

    while (left <= mid) {
      arr1[index1++] = start[left];
      arr2[index2++] = end[left];
      left++;
    }

    while (right <= r) {
      arr1[index1++] = start[right];
      arr2[index2++] = end[right];
      right++;
    }

    // copy the elements to original array

    for (int idx = 0; idx < arr1.length; idx++) {
      start[idx + l] = arr1[idx];
      end[idx + l] = arr2[idx];
    }
  }

  // TC = O(n * log n)
  private static int maxMeetingsOptimal(int[] start, int[] end, int n) {
    // put everything a list of lists
    // each list will contain start, end and index
    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      List<Integer> l = new ArrayList<>();
      l.add(start[i]);
      l.add(end[i]);
      l.add(i);
      list.add(l);
    }

    // Now sort the list in increasing order of the end time value
    // TC = O(n*log n)
    // Collections.sort uses merge sort or quick sort, both have avg time complexity
    // as (n*log n)
    Collections.sort(list, new Comparator<List<Integer>>() {
      public int compare(List<Integer> l1, List<Integer> l2) {
        return l1.get(1) - l2.get(1);
      }
    });

    // Now we will iterate over list and find the number of meetings that can be
    // held
    // first meeting which is finishing most early will always take place
    int prevEndTime = list.get(0).get(1);
    int countAns = 1;
    for (int i = 1; i < list.size(); i++) {
      int sTime = list.get(i).get(0);
      if (sTime > prevEndTime) {
        countAns++;
        prevEndTime = list.get(i).get(1);
      }
    }

    // return ans
    return countAns;
  }

  /*
   * private static int findPlatform(int arr[], int dep[], int n) {
   * 
   * List<Integer> list = new ArrayList<>();
   * list.add(dep[0]);
   * 
   * for (int i = 1; i < n; i++) {
   * boolean needNewPlatform = true;
   * // for each i iterate over list
   * // if there is any element that have less end time than ith start time then
   * no
   * // need of new platform
   * int smallestEndTimeIndex = -1;
   * for (int j = 0; j < list.size(); j++) {
   * if (list.get(j) < arr[i]) {
   * needNewPlatform = false;
   * if (smallestEndTimeIndex == -1) {
   * smallestEndTimeIndex = i;
   * } else if (list.get(j) < list.get(smallestEndTimeIndex)) {
   * smallestEndTimeIndex = i;
   * }
   * }
   * }
   * 
   * if (needNewPlatform) {
   * list.add(dep[i]);
   * } else {
   * list.set(smallestEndTimeIndex, dep[i]);
   * }
   * }
   * 
   * return list.size();
   * }
   */

  // TC = O(2*n + 2*n*log n)
  private static int findPlatform(int[] arr, int[] dep, int n) {

    Arrays.sort(arr);
    Arrays.sort(dep);

    int l = 0;
    int r = 0;
    int maxPlatform = Integer.MIN_VALUE;
    int currPlatform = 0;

    while (l < n && r < n) {
      if (arr[l] <= dep[r]) {
        // need of new platform
        l++;
        currPlatform++;
        maxPlatform = Math.max(maxPlatform, currPlatform);
      } else {
        r++;
        currPlatform--;
      }
    }

    return maxPlatform;
  }

  private static int[] JobScheduling(Job arr[], int n) {

    // we will go greedy
    // will select job having early deadline since everyone is taking same one unit
    // of time

    // sort the jobs in increasing order of their deadline
    // if deadline is same then keep the jobs having more profit
    Arrays.sort(arr, new Comparator<Job>() {
      public int compare(Job first, Job second) {
        return second.profit - first.profit;
      }
    });

    int[] jobeDoneDay = new int[n];

    int jobsDone = 0;
    int totalProfit = 0;
    for (int i = 0; i < n; i++) {
      Job job = arr[i];
      int deadline = job.deadline - 1;
      int temp = deadline;
      while (temp >= 0 && jobeDoneDay[temp] != 0) {
        temp--;
      }

      if (temp >= 0) {
        // this job can not be done
        jobeDoneDay[temp] = job.id;
        totalProfit += job.profit;
        jobsDone++;
      }
    }

    return new int[] { jobsDone, totalProfit };
  }

  public static void main(String[] args) {

    int[] start = { 900, 940, 950, 1100, 1500, 1800 };
    int[] end = { 910, 1200, 1120, 1130, 1900, 2000 };

    System.out.println(findPlatform(start, end, 6));
  }
}
