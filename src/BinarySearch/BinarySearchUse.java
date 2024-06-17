package BinarySearch;


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
	private static int upperBound(int[] arr, int f)
	{
		int l = 0;
		int r = arr.length-1;
		int ans = -1;
		
		while(l<=r)
		{
			int mid = (l+r)/2;
			if(arr[mid] > f)
			{
				ans = mid; // Maybe an answer
				r = mid-1;
			}
			else
			{
				l = mid+1;
			}
		}
		
		return ans;
	}

	public static void main(String[] args)
	{
	}

}
