package BinarySearch;


import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchUse 
{

	// converting the question
	// kiska nth power m hoga
	// TC = O(log m)
	// SC = O(1)
	public static int NthRoot(int n, int m) 
	{
		int left = 1;
		int right = m;

		int mid = (left+right)/2;

		while(left<= right)
		{
			mid = (left+right)/2;

			if(Math.pow(mid, n) == m)
			{
				return mid;
			}
			else if(Math.pow(mid, n) > m)
			{
				right = mid-1;
			}
			else
			{
				left = mid+1;
			}
		}

		return -1;
	}
	
	// l = R*C
	// TC = O(l*log(l))
	// SC = O(l)
	public static int median(int matrix[][], int R, int C) 
	{
		// make an array out of all elements
		int l = R*C;
		int[] arr = new int[l];

		int index = 0;

		for(int i=0; i<R; i++)
		{
			for(int j=0; j<C; j++)
			{
				arr[index++] = matrix[i][j];
			}
		}

		Arrays.sort(arr);

		return arr[l/2]; // since number of elements is odd
	}

	// TC = O( log(10^9) * n*log(m))
	//		 ^binary search  ^countSmallEqual
	// This optimal works when n == odd and 1 <= matrix[i][j] <= Integer.MAX_VALUE
	private static int medianOptimal(int matrix[][], int row, int col)
	{
		int totalElements = row*col;

		int low = Integer.MAX_VALUE;
		int high = Integer.MIN_VALUE;

		// iterate over first column to find low
		for(int r=0; r<row; r++)
		{
			low = Math.min(matrix[r][0], low);
			high= Math.max(matrix[r][col-1], high);
		}

		int req = totalElements/2;

		while(low <= high)
		{
			int mid = (low+high)/2;
			int smallerEquals = countSmallEqual(matrix, mid);

			if(smallerEquals <= req)
			{
				low = mid+1;
			}
			else
			{
				high = mid-1;
			}
		}

		return low;

	}


	// TC = O(n*log m)
	// SC = O(1)
	private static int countSmallEqual(int[][] matrix, int mid)
	{	
		int row = matrix.length;

		int countAns = 0;

		for(int i=0; i<row; i++)
		{
			countAns += upperBound(matrix[i], mid);
		}

		return countAns;
	}

	// TC = O(n)
	private static int upperBound(int[] arr, int value)
	{
		int l = 0;
		int r = arr.length-1;
		int ans = -1;

		while(l<=r)
		{
			int mid = (l+r)/2;
			if(arr[mid] > value)
			{
				ans = mid; // Maybe an answer
				r = mid-1;
			}
			else
			{
				l = mid+1;
			}
		}
		
		if(ans == -1) {
			return arr.length;
		}

		return ans;
	}


	// using xor operation
	// TC = O(n)
	private static int singleNonDuplicate(int[] nums) 
	{
		int n = nums.length;
		int xor = 0;

		for(int i=0; i<n; i++)
		{
			xor = xor^nums[i];
		}
		return xor;
	}


	// TC = O(log n)
	private static int singleNonDupBetter(int[] nums)
	{
		int n = nums.length;

		int l = 0;
		int r = n-2;

		while(l<=r)
		{
			int mid = (l+r)/2;

			if((mid & 1) == 1)
			{
				// means in right half, shrink the right half.
				if(nums[mid+1] == nums[mid])
				{
					// decrease r
					r = mid-1;
				}
				else
				{
					// at left half, shrink the left half
					l = mid+1;
				}
			}
			else
			{
				// means at left half
				if(nums[mid+1] == nums[mid])
				{
					// decrease r
					l = mid+1;
				}
				else
				{
					// at right half
					r = mid-1;
				}
			}

		}

		return nums[l];
	}

	// TC = O(log n)
	public static int singleNonDupOptimal(int[] nums) 
	{
		int n = nums.length;

		int l = 0;
		int r = n-2;

		while(l<=r)
		{
			int mid = (l+r) >> 1; // right shift one bit

		if(nums[mid] == nums[mid^1])
		{
			// at left half so shrink it
			l = mid + 1;
		}
		else
		{
			// at riht half so shrink it
			r = mid - 1;
		}
		}

		return nums[l];
	}


	// TC = O(log n)
	// SC = O(1)
	// Search in sorted rotated array
	private static int search(int[] nums, int target) 
	{ 
		int n = nums.length;

		int l = 0;
		int r = n-1;

		while(l <= r)
		{
			int mid = (l+r)/2;

			if(nums[mid] == target)	return mid;

			// if left half is sorted
			if(nums[l] <= nums[mid])
			{
				if(target >= nums[l] && target < nums[mid])
				{
					// target lies in left half
					r = mid-1;
				}
				else
				{
					// target lies in right half
					l = mid +1;
				}
			}

			// if right half is sorted
			else
			{
				if(target > nums[mid] && target <=  nums[r])
				{
					// target lies in right half
					l = mid +1;
				}
				else
				{
					r = mid -1;
				}
			}
		}

		return -1;
	}

	// TC = O(m+n)
	// SC = O(m+n)
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) 
	{
		int[] ans = mergeSortedArray(nums1, nums2);
		if(ans.length %2 != 0)
		{
			return ans[ans.length/2];
		}
		int i1 = (ans.length)/2;
		int i2 = (ans.length-1)/2;
		double result = (ans[i1]+ans[i2])/2.0;
		return result;
	}

	public static int[] mergeSortedArray(int[] a1, int[] a2)
	{
		int[] ans = new int[a1.length+a2.length];
		int i = 0;
		int j = 0;
		int count = 0;
		while(i< a1.length && j< a2.length)
		{
			if(a1[i] < a2[j])
			{
				ans[count] = a1[i];
				i++;
			}
			else{
				ans[count] = a2[j];
				j++;
			}
			count++;
		}
		while(i<a1.length)
		{
			ans[count] = a1[i];
			count++;
			i++;
		}
		while(j<a2.length)
		{
			ans[count] = a2[j];
			count++;
			j++;
		}
		return ans;
	}


	// TC = O(m+n)
	// SC = O(1)
	private static double findMedianSortedArraysBetter(int[] nums1, int[] nums2)
	{
		int m = nums1.length;
		int n = nums2.length;

		int totalElements = m+n;

		// find mid1 element and mid2 element

		int ind2 = totalElements/2;
		int ind1 = ind2-1;

		int count = 0;
		long ind1Ele = -1;
		long ind2Ele = -1;
		int l = 0;
		int r = 0;
		while(l < m && r < n)
		{
			if(nums1[l] < nums2[r])
			{
				if(count == ind1)	ind1Ele = nums1[l];

				if(count == ind2)	ind2Ele = nums1[l];

				count++;
				l++;
			}
			else
			{
				if(count == ind1)	ind1Ele = nums2[r];

				if(count == ind2)	ind2Ele = nums2[r];

				count++;
				r++;
			}	
		}

		while(l < m)
		{
			if(count == ind1)	ind1Ele = nums1[l];

			if(count == ind2)	ind2Ele = nums1[l];

			count++;
			l++;
		}

		while(r < n)
		{
			if(count == ind1)	ind1Ele = nums2[r];

			if(count == ind2)	ind2Ele = nums2[r];

			count++;
			r++;
		}


		if(totalElements%2 == 0)
		{
			return (ind1Ele + ind2Ele)/2.0;
		}

		return ind2Ele;


	}

	// TC = O(log min(n1,n2))
	// SC = O(1)
	private static double findMedianSortedArrayOptimal(int[] nums1, int[] nums2)
	{
		int n1 = nums1.length;
		int n2 = nums2.length;

		int n = n1+n2;

		if(n1 > n2) return findMedianSortedArrayOptimal(nums2, nums1);

		int low = 0;
		int high = n1;

		int numLeft = n/2;

		while(low <= high)
		{
			int mid1  = (low+high) >> 1 ;
		int mid2 = numLeft - mid1;

		int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
		int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

		if(mid1 < n1)	r1 = nums1[mid1];
		if(mid2 < n2)	r2 = nums2[mid2];
		if(mid1-1 >=0) 	l1 = nums1[mid1-1];
		if(mid2-1 >=0)	l2 = nums2[mid2-1];

		if(l1 <= r2 && l2 <= r1)
		{
			if(n % 2 == 0) return  (Math.max(l1, l2) + Math.min(r1, r2)) /2.0;
			else	return Math.min(r1, r2);
		}
		else if(l1 > r2)	high = mid1-1;
		else 	low = mid1+1;
		}

		return 0.0;

	}


	// TC = O(log m)
	// SC = O(1)
	public static long kthElement(int arr1[], int arr2[], int m, int n, int k) {
		// Ensure arr1 is the smaller array
		if (m > n) return kthElement(arr2, arr1, n, m, k);

		int low = Math.max(0, k - n); 
		int high = Math.min(k, m);

		while (low <= high) {
			int mid1 = (low + high) / 2;
			int mid2 = k - mid1;

			// Edge cases handling
			long l1 = (mid1 > 0) ? arr1[mid1 - 1] : Long.MIN_VALUE;
			long l2 = (mid2 > 0) ? arr2[mid2 - 1] : Long.MIN_VALUE;
			long r1 = (mid1 < m) ? arr1[mid1] : Long.MAX_VALUE;
			long r2 = (mid2 < n) ? arr2[mid2] : Long.MAX_VALUE;

			if (l1 <= r2 && l2 <= r1) {
				return Math.max(l1, l2);
			} else if (l1 > r2) {
				high = mid1 - 1;
			} else {
				low = mid1 + 1;
			}
		}

		return 0L;
	}

	
	// TC = O(n * log(high-low))
	// SC = O(1)
	public static int books(ArrayList<Integer> list, int students) 
	{
		int n = list.size();
		if(students > n)	return -1;
		
		// We have to minimize the maximum pages a student can hold
		int low = 0;
		int high = 0;
		for(Integer x : list)
		{
			low = Math.max(x, low);
			high += x;
		}
		
		// Do binary search
		while(low <= high)
		{
			int mid = (low+high)/2;
			
			int s = countStudents(list, mid);
			if(s > students)
			{
				low = mid+1;
			}
			else
			{
				high = mid-1;
			}
		}
		
		return low;
		
    }
	
	private static int countStudents(ArrayList<Integer> list, int  maxPages)
	{
		int n = list.size();
		
		int pages = 0;
		int s = 1;
		
		for(int i=0; i<n; i++)
		{
			pages += list.get(i);
			if(pages > maxPages)
			{
				pages = list.get(i);
				s++;
			}
		}
		
		return s;
		
	}
	
	
	// TC = O(n*log(n) + n*log(high-low))
	// SC = O(1)
	public static int aggressiveCows(int []stalls, int k) 
	{
		int n = stalls.length;
		
		if(k > n)	return -1; // This case not reachable since k <= n in question
		
		Arrays.sort(stalls);
		int low = Integer.MAX_VALUE;
		int high = stalls[n-1] - stalls[0];
		
		for(int i=0; i<n-1; i++)
		{
			low = Math.min(low,  stalls[i+1] - stalls[i]);
		}
		
		while(low <= high)
		{
			int mid = (low + high)/2;
			
			int cows = countCows(stalls, mid, k);
			
			if(cows < k) 
			{
				high = mid-1;
			}
			else
			{
				low = mid+1;
			}
		}
		
		return high;
    }
	
	private static int countCows(int[] stalls, int minDist, int k)
	{
		int n = stalls.length;
		
		int cows = 1;
		int prevIndex = 0;
		for(int i=1; i<n; i++)
		{
			int dist = stalls[i] - stalls[prevIndex];
			if(dist >= minDist)
			{
				prevIndex = i;
				cows++;
			}
		}
		
		return cows;
	}
	
	public static void main(String[] args)
	{
		ArrayList<Integer> list = new ArrayList<>();
		int[] arr = {97, 26, 12, 67, 10, 33, 79, 49, 79, 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90, 24 };
		for(int i=0; i<arr.length; i++)
		{
			list.add(arr[i]);
		}
		
		System.out.println(books(list, 26));
	}

}
