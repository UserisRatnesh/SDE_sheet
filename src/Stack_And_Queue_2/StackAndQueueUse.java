package Stack_And_Queue_2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;



class LFUCache {

	HashMap<Integer, DoubleLinkedList> freqList;
	HashMap<Integer, Node> map;
	final int capacity;
	int minFreq;
	int currSize;

	public LFUCache(int capacity) {
		this.capacity = capacity;
		this.freqList = new HashMap<>();
		this.map = new HashMap<>();
		this.minFreq = 0;
		this.currSize = 0;
	}

	public int get(int key) {
		Node currNode = map.get(key);
		if (currNode == null) {
			return -1;
		}
		updateNode(currNode);
		return currNode.value;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			Node node = map.get(key);
			node.value = value;
			updateNode(node);
			return;
		}

		if (this.currSize == this.capacity) {
			// remove node from freqList having minimum freq
			DoubleLinkedList dll = freqList.get(minFreq);
			Node rem = dll.tail.prev;
			dll.remove(rem);
			map.remove(rem.key);
			this.currSize--;
		}

		// Add a new node
		Node add = new Node(key, value);
		DoubleLinkedList dll = freqList.getOrDefault(1, new DoubleLinkedList());
		dll.addNode(add);
		freqList.put(1, dll);
		map.put(key, add);
		this.minFreq = 1;
		this.currSize++;
	}

	private void updateNode(Node node) {
		// Remove the node from curr freqList
		DoubleLinkedList dl = freqList.get(node.frequency);
		dl.remove(node);

		// update the min freq
		if (dl.listSize == 0 && minFreq == node.frequency) {
			this.minFreq++;
		}

		// increase the freq
		node.frequency++;

		// Add the node to new freq list by increasing the freq
		DoubleLinkedList dll = freqList.getOrDefault(node.frequency, new DoubleLinkedList());
		dll.addNode(node);
		freqList.put(node.frequency, dll);
	}

	class Node {
		int key;
		int value;
		int frequency;
		Node prev;
		Node next;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			this.frequency = 1;
			this.prev = null;
			this.next = null;
		}
	}

	class DoubleLinkedList {
		int listSize;
		Node head;
		Node tail;

		public DoubleLinkedList() {
			this.listSize = 0;
			this.head = new Node(0, 0);
			this.tail = new Node(0, 0);
			this.head.next = this.tail;
			this.tail.prev = head;
		}

		public void remove(Node node) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
			this.listSize--;
		}

		public void addNode(Node node) {
			this.listSize++;
			node.next = head.next;
			head.next = node;
			node.next.prev = node;
			node.prev = head;
		}
	}
}

class Pair {
	int first;
	int second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

// TC = O(1)
// SC = O(2*n)
class MinStack {
	Stack<Pair> stk;

	public MinStack() {
		this.stk = new Stack<>();
	}

	// Add to treemap takes log(n) time
	public void push(int val) {
		if (stk.isEmpty()) {
			stk.push(new Pair(val, val));
		} else {
			int min = Math.min(stk.peek().second, val);
			stk.push(new Pair(val, min));
		}
	}

	// Remove from treemap takes log(n) time
	public void pop() {
		stk.pop();
	}

	public int top() {
		return stk.peek().first;
	}

	public int getMin() {
		return stk.peek().second;
	}
}

class MinStackBetter {
	Stack<Long> stk;
	Long min;

	public MinStackBetter() {
		this.stk = new Stack<>();
		this.min = Long.MAX_VALUE;
	}

	// Add to treemap takes log(n) time
	public void push(int value) {
		Long val = Long.valueOf(value);
		if(stk.isEmpty())
		{
			stk.push(val);
			min = val;
		}
		else
		{
			if(val < min)
			{
				Long topush = 2*val - min;
				stk.push(topush);
				min = val;
			}
			else {
				stk.push(val);
			}
		}
	}


	public void pop() {
		if(stk.isEmpty())
		{
			return ;
		}

		Long val = stk.pop();
		if(val < min)
		{
			// update the min
			min = 2*min - val;
		}
	}

	public int top() {
		if(stk.isEmpty())
		{
			return -1;
		}

		Long val = stk.peek();
		if(val < min)
		{
			return min.intValue();
		}
		return val.intValue();
	}

	public int getMin() {
		return min.intValue();
	}
}




public class StackAndQueueUse 
{

	public ArrayList<Integer> prevSmaller(ArrayList<Integer> A) 
	{
		Stack<Integer> st = new Stack<>();

		ArrayList<Integer> ans = new ArrayList<>();

		for(int x : A)
		{
			while(!st.isEmpty() && st.peek() >= x)
			{
				st.pop();
			}

			if(st.isEmpty())    ans.add(-1);
			else    			ans.add(st.peek());
			st.add(x);

		}

		return ans;
	}

	// TC = O(n^2)
	// SC = O(1)
	// Giving TLE
	public static int largestRectangleArea(int[] arr) {
		int n = arr.length;
		int ans = 0;

		for (int i = 0; i < n; i++) {
			int h = arr[i];

			int width = 1;
			int j = i;
			for (j = i + 1; j < n; j++) {
				if (arr[j] < h) {
					break;
				}
				width++;
			}

			for (j = i - 1; j >= 0; j--) {
				if (arr[j] < h) {
					break;
				}
				width++;
			}

			ans = Math.max(ans, h * width);

		}

		return ans;
	}

	// TC = O(3*n)
	// SC = O(3*n)
	public static int largestRectangleAreaBetter(int[] arr) {
		int n = arr.length;
		int ans = 0;

		Stack<Integer> stk = new Stack<>();
		int[] leftSmallIndex = new int[n];
		int[] rightSmallIndex = new int[n];

		for (int i = 0; i < n; i++) {
			while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
				stk.pop();
			}
			if (stk.isEmpty()) {
				leftSmallIndex[i] = 0;
			} else {
				leftSmallIndex[i] = stk.peek() + 1;
			}
			stk.push(i);
		}

		stk.clear();
		for (int i = n - 1; i >= 0; i--) {
			while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
				stk.pop();
			}
			if (stk.isEmpty()) {
				rightSmallIndex[i] = n - 1;
			} else {
				rightSmallIndex[i] = stk.peek() - 1;
			}
			stk.push(i);
		}

		for (int i = 0; i < n; i++) {
			ans = Math.max(ans, (rightSmallIndex[i] - leftSmallIndex[i] + 1) * arr[i]);
		}

		return ans;

	}

	// TC = O(2*n)
	// SC = O(n)
	public static int largestRectangleAreaOptimal(int[] arr) {
		int n = arr.length;
		int ans = 0;

		Stack<Integer> stk = new Stack<>();

		for (int i = 0; i <= n; i++) {
			int curr = i == n ? Integer.MIN_VALUE : arr[i];
			while (!stk.isEmpty() && curr < arr[stk.peek()]) {
				int h = arr[stk.pop()];
				int rightSmall = i;
				int leftSmall = stk.isEmpty() ? -1 : stk.peek();
				ans = Math.max(ans, (rightSmall - leftSmall - 1) * h);
			}
			stk.push(i);
		}

		return ans;
	}


	// TC = O(n*k)
	// SC = O(n-k+1)
	// Giving TLE
	public static int[] maxSlidingWindow(int[] nums, int k) 
	{
		int n = nums.length;
		int[] ans = new int[n - k + 1];
		int index = 0;

		for (int i = 0; i <= n - k; i++) {
			int max = Integer.MIN_VALUE;
			for (int j = i; j < i + k; j++) {
				max = Math.max(max, nums[j]);
			}

			ans[index++] = max;
		}

		return ans;
	}

	// TC = O(n*k), In a worst case, But in best case it can be O(n)
	// SC = O(n-k+1)
	public int[] maxSlidingWindow1(int[] nums, int k) 
	{
		int n = nums.length;
		int left = 0;
		int right = 0;
		int[] ans = new int[n - k + 1];
		int index = 0;

		int maxIndex = -1;
		int maxElement = Integer.MIN_VALUE;

		while (right < k) {
			if (nums[right] >= maxElement) {
				maxElement = nums[right];
				maxIndex = right;
			}
			right++;
		}

		ans[index++] = maxElement;

		while (right < n) {
			left++;
			if (maxIndex <= right && maxIndex >= left) {
				if (nums[right] >= maxElement) {
					ans[index++] = nums[right];
					maxIndex = right;
					maxElement = nums[right];
				}
				else
				{
					ans[index++] = maxElement;
				}
			} else {
				int currMax = Integer.MIN_VALUE;
				for (int i = left; i <= right; i++) {
					if (currMax <= nums[i]) {
						maxIndex = i;
						currMax = nums[i];
					}
				}
				maxElement = currMax;
				ans[index++] = maxElement;
			}
			right++;
		}

		return ans;
	}

	// TC = O(2*n) => since over all iterations included you are removing at most n
	// elements
	// SC = O(n-k+1) +(K) => for dequeue
	public static int[] maxSlidingWindowOptimal(int[] nums, int k) {

		int n = nums.length;
		int[] ans = new int[n - k + 1];
		int index = 0;

		// Store the indices such that their values are in strictly decreasing order
		Deque<Integer> dq = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {

			// Removing the extra indices
			// At max it will remove only one element since we are increasing single element
			// every time
			if (!dq.isEmpty() && dq.peekFirst() == i - k)
				dq.removeFirst();

			// Removing the unwanted indices with smaller values
			while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
				dq.removeLast();
			}

			dq.addLast(i);
			if (i >= k - 1)
				ans[index++] = nums[dq.peekFirst()];
		}

		return ans;
	}

	static class Triple {
		int r;
		int c;
		int t;

		public Triple(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	
	// TC = O(m*n + 4*m*n)
	// SC = O(m*n)
	public static int orangesRotting(int[][] grid) {

		Queue<Triple> q = new LinkedList<>();
		int m = grid.length;
		int n = grid[0].length;

		int countFresh = 0;
		// Enter all indexes of 2

		int[][] visited = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 2) {
					q.add(new Triple(i, j, 0));
					visited[i][j] = 1;
				}
				if (grid[i][j] == 1) {
					countFresh++;
				}
			}
		}

		int time = 0;
		int count = 0;

		while (!q.isEmpty()) {

			Triple pair = q.poll();
			int i = pair.r;
			int j = pair.c;
			int t = pair.t;
			time = Math.max(time, t);

			int[] dirI = { -1, 0, 1, 0 };
			int[] dirJ = { 0, 1, 0, -1 };
			for (int idx = 0; idx < 4; idx++) {
				int newI = i + dirI[idx];
				int newJ = j + dirJ[idx];

				if (newI < m && newI >= 0 && newJ < n && newJ >= 0 && visited[newI][newJ] == 0
						&& grid[newI][newJ] == 1) {
					q.add(new Triple(newI, newJ, t + 1));
					visited[newI][newJ] = 1;
					grid[newI][newJ] = 2;
					count++;

				}

			}

		}

		if (countFresh != count) {
			return -1;
		}
		return time;
	}

	/*
	 * Stock span problem => solved using a list just brute force.
	 */
	
	
	// TC = O(n^2)
	// SC = O(n + n)
	public static int[] maxMinWindow(int[] a, int n) {
        // Write your code here

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = minWindow(a, n, a[i]);
        }

        return ans;
    }

    public static int minWindow(int[] a, int n, int windowSize) {

        int max = Integer.MIN_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();
        int l = 0;
        int r = 0;
        while (r < n) {
            // remove all out of index
            while (!dq.isEmpty() && dq.getFirst() < l) {
                dq.removeFirst();
            }

            // add the current index if empty
            if (dq.isEmpty()) {
                dq.add(r);
            } else if (a[r] > a[dq.getLast()]) {
                dq.add(r);
            } else {
                while (!dq.isEmpty() && a[r] <= a[dq.getLast()]) {
                    dq.removeLast();
                }
                dq.add(r);
            }

            if (r - l + 1 == windowSize) {
                max = Math.max(max, a[dq.getFirst()]);
                l++;
            }
            r++;
        }

        return max;
    }
    
    // TC = O(n* n-i)
    // SC = O(n)
    public static int[] maxMinWindowBetter(int[] a, int n) {
        // Write your code here

        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            // search in left for smaller element index
            int l = i;
            while (l >= 0 && a[i] <= a[l]) {
                l--;
            }

            // search in right for greater element index
            int r = i;
            while (r < n && a[i] <= a[r]) {
                r++;
            }


            int length = r - l- 1;
            ans[length-1] = Math.max(ans[length-1], a[i]);
        }

        for (int i = n-2; i >= 0; i--) {
            ans[i] = Math.max(ans[i], ans[i+1]);
        }
        return ans;
    }
    
    
    // TC = O(2*n)
    // SC = O(2*n)
    public static int[] maxMinWindowMostOptimal(int[] arr, int n) {

        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MIN_VALUE);
        // stack to keep track of element indices such that their values are in
        // increasing order
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i <= n; i++) {

            // search the index of left smaller element

            // search the index of right smaller element

            // find the length for which the current element can be an option for smaller
            // element
            // Assign this value for that length if already present element if smaller than
            // present one

            int currEle = i == n ? Integer.MIN_VALUE : arr[i];
            while (!stk.isEmpty() && currEle <= arr[stk.peek()]) {

                int popElement = arr[stk.pop()];

                // now we know that left smaller for this popElement is the element just below
                // this in the stack
                // and right smaller is the curr one
                int r = i;
                int l = !stk.isEmpty() ? stk.peek() : -1;

                int length = r - l - 2;
                ans[length] = Math.max(popElement, ans[length]);
            }
            stk.push(i);
        }

        // If any length is vacant then fill it by just next element
        for (int i = n - 2; i >= 0; i--) {
            ans[i] = Math.max(ans[i], ans[i + 1]);
        }

        return ans;
    }

	public static void main(String[] args) 
	{

	}

}
