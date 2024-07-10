package LinkedList_and_Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

class ListNode {
  int val;
  ListNode next;
  ListNode random;

  ListNode(int x) {
    val = x;
    next = null;
    random = null;
  }
}

class Item {
  int value, weight;

  public Item(int value, int weight) {
    this.weight = weight;
    this.value = value;
  }
}

public class LinkedList_and_Arrays_use {

  // TC = O(n)
  // SC = O(1)
  public static ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) {
      return head;
    }

    // find the length of linked list
    ListNode prev = head;
    ListNode fwd = head;
    int length = 1;
    // we are taking initial length as 1 since we want the fwd pointer to be at last
    // node
    // so that at last when we want to link it to first we need not to traverse
    // again
    while (fwd.next != null) // O(n)
    {
      length++;
      fwd = fwd.next;
    }

    // if k is more than length of linked list
    // then we can only rotate (k % length) times and still it will get the same
    // result
    k = k % length;
    int count = 1;
    while (count != length - k) // O(n % k)
    {
      count++;
      prev = prev.next;
    }

    // now previous is pointing to the (n-k)th node from start
    fwd.next = head;
    head = prev.next;
    prev.next = null;
    return head;

  }

  // TC = O(2n)
  // SC = O(n)
  private static ListNode deepCopyList(ListNode head) {
    if (head == null) {
      return null;
    }

    ListNode temp = head;
    ListNode newHead = new ListNode(0);
    ListNode ans = newHead;

    // Maintain the map for corresponding new nodes
    HashMap<ListNode, ListNode> correspondingNodes = new HashMap<>();

    while (temp != null) // O(n)
    {
      ListNode newNode = new ListNode(temp.val);
      ans.next = newNode;
      ans = ans.next;

      // updating the corresponding nodes
      correspondingNodes.put(temp, newNode);

      // update the pointer
      temp = temp.next;
    }

    // iterate over new created node structure and update the random connection
    ListNode tempOld = head;
    ListNode tempNew = newHead.next;
    while (tempNew != null) // O(n)
    {
      ListNode tempOldRandom = tempOld.random;
      ListNode corresponding = correspondingNodes.get(tempOldRandom); // O(1)
      tempNew.random = corresponding;

      // update the pointers
      tempOld = tempOld.next;
      tempNew = tempNew.next;
    }

    return newHead.next;
  }

  // TC = O(3n)
  // SC = O(1)
  private static ListNode deepCopyListOptimal(ListNode head) {
    /*
     * 1. Iterate over the original list and create new node corresponding to each
     * node
     * and store that node in between the nodes.
     * 2. Now Iterate over the original list and find the node random pointer is
     * pointing.
     * 3. Now the next of the old node will be it's new node and
     * next of it's random node will be random node of new corresponding node.
     * 4. Now update the list to have only new nodes.
     */

    ListNode temp = head;

    // Inserted new nodes between older nodes
    while (temp != null) {
      ListNode newNode = new ListNode(temp.val);
      newNode.next = temp.next;
      temp.next = newNode;
      temp = newNode.next;
    }

    temp = head;
    while (temp != null) {
      // assign new node next to random next
      temp.next.random = temp.random == null ? null : temp.random.next;

      temp = temp.next.next;
    }

    // Extract the new node structure
    ListNode newHead = new ListNode(0);
    ListNode ans = newHead;
    temp = head;
    while (temp != null) {
      newHead.next = temp.next;
      newHead = newHead.next;
      temp.next = newHead.next;
      temp = temp.next;
    }

    return ans.next;
  }

  // TC = O(n)
  // SC = O(1)
  public static int removeDuplicates(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int k = 1; // Initialize the unique elements count
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[i - 1]) {
        // If the current element is different from the previous one
        // Increment the unique elements count and update the next unique element
        nums[k] = nums[i];
        k++;
      }
    }

    return k;
  }

  // TC = O(n)
  // SC = O(1)
  public static int findMaxConsecutiveOnes(int[] nums) {
    int n = nums.length;

    int ans = 0;
    int l = 0;
    int r = 0;
    while (r < n) {
      if (nums[r] == 1) {
        ans = Math.max(ans, r - l + 1);
        r++;
      } else {
        l = r + 1;
        r++;
      }
    }
    return ans;

  }

  // TC = O(n^2)
  // SC = O(number of unique triplets)
  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i++) {
      // remove duplicates
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      int left = i + 1;
      int right = nums.length - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum == 0) {
          ans.add(Arrays.asList(nums[i], nums[left], nums[right]));

          // removes duplicates
          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }
          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }
          left++;
          right--;
        } else if (sum > 0) {
          right--;
        } else {
          left++;
        }
      }
    }
    return ans;
  }

  // TC = O(n)
  // SC = O(1)
  public static int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int leftMax = 0;
    int rightMax = 0;
    int trappedWater = 0;

    while (left < right) {
      if (height[left] < height[right]) {
        if (height[left] >= leftMax) {
          leftMax = height[left];
        } else {
          trappedWater += leftMax - height[left];
        }
        left++;
      } else {
        if (height[right] >= rightMax) {
          rightMax = height[right];
        } else {
          trappedWater += rightMax - height[right];
        }
        right--;
      }
    }

    return trappedWater;
  }
  
  // TC = O(n)
  // SC = O(n)
  public static int trapRainwater(int[] arr) {
      int n = arr.length;
      int left = 0;
      int ans = 0;

      int[] rightMax = new int[n];
      for (int i = n - 1; i >= 0; i--) {
          if (i == n - 1) {
              rightMax[i] = arr[i];
              continue;
          }
          rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
      }

      int leftMax = arr[left];

      while (left < n - 1) {
          if (arr[left] >= leftMax) {
              // update left max
              leftMax = arr[left];
          } else {
              // contains water
              if (arr[left] < rightMax[left + 1]) {
                  ans += Math.min(leftMax, rightMax[left + 1]) - arr[left];
              }
          }
          left++;
      }
      return ans;
  }

  // Fractional knapsack
  // Below code if item breaking is not allowed
  private static long fractionalKnapsack(int w, Item[] arr, int n) {

    return fractionalKnapsackHelper(w, arr, n, 0);
  }

  private static long fractionalKnapsackHelper(int w, Item[] arr, int n, int i) {

    // Base case
    if (i == n || w <= 0) {
      return 0;
    }

    // Two options

    // Take this Item
    // Take is only possible if current weight is more than or equal to
    // arr[i].weight
    long take = Long.MIN_VALUE;
    if (arr[i].weight <= w) {
      take = arr[i].value + fractionalKnapsackHelper(w - arr[i].weight, arr, n, i + 1);
    }

    // Not take this Item
    long notTake = fractionalKnapsackHelper(w, arr, n, i + 1);

    return Math.max(take, notTake);
  }

  // Fractional knapsack
  // Item breaking is allowed
  // TC = O(n*log n)
  // SC = O(1)
  private static long knapsack(int w, Item[] arr, int n) {

    // first sort the Item array according to value to weight ratio
    Arrays.sort(arr, new Comparator<Item>() {
      public int compare(Item i1, Item i2) {
        double ratio1 = i1.value / i1.weight;
        double ratio2 = i2.value / i2.weight;
        return Double.compare(ratio2, ratio1);
      }
    });

    // Iterate over array and keep on adding the values untill w is greater than or
    // equal to weight of ith item
    long totalValue = 0;
    for (int i = 0; i < n; i++) {
      Item item = arr[i];
      int weight = item.weight;
      int value = item.value;
      if (w >= weight) {
        totalValue += value;
        w -= weight;
      } else {
        // divide in fraction and add then break
        totalValue += (value / weight) * w;
        break;
      }
    }

    return totalValue;

  }

  public static void main(String[] args) {

  }

}
