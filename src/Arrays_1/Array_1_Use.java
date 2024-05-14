package Arrays_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Scanner;

public class Array_1_Use 
{
	public static ArrayList<ArrayList<Integer>> zeroMatrix(ArrayList<ArrayList<Integer>> matrix, Integer n, Integer m) 
    {
        Map<String, Boolean> map = new HashMap<>();
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                String key = i+"."+j;
                if(matrix.get(i).get(j) == 0)
                {
                    map.put(key, true);
                }
            }
        }

        Set<String> keys = map.keySet();
        for(String s : keys)
        {
            int i = Integer.parseInt(s.split("\\.")[0]);
            int j = Integer.parseInt(s.split("\\.")[1]);

            for(int row=0; row<n; row++)
            {
                matrix.get(row).set(j, 0);
            }
            for(int col=0; col<m; col++)
            {
                matrix.get(i).set(col, 0);
            }
        }

        return matrix;
    }
	
	public static List< Integer > nextGreaterPermutation(List< Integer > A) 
    {
        int n = A.size();
        List<Integer> output = new ArrayList<>();

        int index = -1;
        for(int i=n-2; i>=0; i--)
        {
            if(A.get(i) < A.get(i+1))
            {
                index = i;
                break;
            }
        }
        
        // when you do not find any index for which a[i] < a[i+1] 
        if(index == -1)
        {
            Collections.sort(A);
            return A;
        }
        
        
        for(int j=0; j<index; j++)
        {
            output.add(A.get(j));
        }

        // iterate from last element to i th element 
        // and find the value just greater than i th element
        for(int j=n-1; j>index; j--)
        {
            if(A.get(j) > A.get(index))
            {
            	output.add(A.get(j));
                Integer temp = A.get(j);
                A.set(j,  A.get(index));
                A.set(index,  temp);
                break;
            }
        }


        for(int j=n-1; j>index; j--)
        {
        	output.add(A.get(j));
            
        }

        return output;
    }
	
	private static int mergeSort(int[] arr, int low, int high)
    {
        if(low >= high)
        {
            return 0;
        }

        int mid = (low + high)/2;
        int count = 0;
        
        count += mergeSort(arr, low, mid);
        count += mergeSort(arr, mid+1, high);
        count += merge(arr, low, mid, high);
        return count;
    }

    private static int merge(int[] arr, int low, int mid, int high)
    {
        
    	int[] tempList = new int[high-low+1];
    	int index = 0;
        int left = low;
        int right = mid+1;

        int count = 0;
        while(left <= mid && right <= high)
        {
            double a = arr[left];
            double b = 2d*arr[right];
            if(a > b)
            {
                count += mid-left+1;
                right++;
            }
            else
            {
            	left++;
            }
            
        }
        
        
        left = low;
        right = mid+1;
        
        while(left <= mid && right <= high)
        {
            if(arr[left] < arr[right])
            {
                tempList[index++] = arr[left];
                left++;
            }
            else
            {
                tempList[index++] = arr[right];
                right++;
            }
        }

        while(left <= mid)
        {
        	tempList[index++] = arr[left++];
        }

        while(right <= high)
        {
        	tempList[index++] = arr[right++];
        }

        for(int i=low; i<=high; i++)
        {
            arr[i] = tempList[i-low];
        }

        return count;
    }
	
    public static int protectedOrNot(ArrayList<Integer> a, ArrayList<Integer> b) {
        int n = a.size();

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (a.get(i) > b.get(i)) {
                pq.add(a.get(i) - b.get(i));
            } else if (a.get(i) < b.get(i)) {
                tmp.add(b.get(i) - a.get(i));
            }
        }

        if (tmp.size() == 0) return 0;

        Collections.sort(tmp);
        if (pq.size() == 0) return -1;

        int curr = pq.poll();
        int res = 1;

        for (int i = 0; i < tmp.size(); i++) {
            if (curr >= tmp.get(i)) {
                res++;
                curr -= tmp.get(i);
            } else {
                int ok = 0;
                while (!pq.isEmpty()) {
                    curr += pq.poll();
                    res++;
                    if (curr >= tmp.get(i)) {
                        ok = 1;
                        res++;
                        curr -= tmp.get(i);
                        break;
                    }
                }
                if (ok == 0) return -1;
            }
        }
        return res;
    }
    
    public static String round932Div2Q1(int n, String s)
    {
        int left = 0;
        int right = s.length()-1;
        if(isLeftGreater(left, right, s) == -1 || isLeftGreater(left, right, s) == 0)
        {
            return s;
        }
        
        return reverse(s) + s;
        
    }
    
    private static String reverse(String s)
    {
        String ans = "";
        for(int i=s.length()-1; i>=0; i--)
        {
            ans+=s.charAt(i);
        }
        return ans;
    }
    
    private static int isLeftGreater(int left, int right, String s)
    {
        while(left < right)
        {
            int asciiLeft = (int)s.charAt(left);
            int asciiRight = (int)s.charAt(right);
            if(asciiLeft < asciiRight)
            {
                return -1;
            }
            else if(asciiLeft > asciiRight)
            {
                return 1;
                
            }
            left++;
            right--;
        }
        
        return 0;
    }
 
    private static int protectedOrNot1(ArrayList<Integer> a, ArrayList<Integer> b)
    {
    	List<Integer> list = new ArrayList<>();
    	int n = a.size();
    	for(int i=0; i<n; i++)
    	{
    		list.add(a.get(i)- b.get(i));
    	}
    	
    	Collections.sort(list);
    	
    	List<Integer> isCounted = new ArrayList<>();
    	int count = 0;
    	
    	int left = 0;
    	int right = n-1;
    	while(left< right)
    	{
    		if(list.get(left) < 0 && list.get(right)>0)
    		{
    			int sum = list.get(left) + list.get(right);
    			if(sum >0)
    			{
    				list.set(right,  sum);
    				list.set(left,  0);
    				if(!isCounted.contains(left))
    				{
    					count++;
    					isCounted.add(left);
    				}
    				if(!isCounted.contains(right))
    				{
    					count++;
    					isCounted.add(right);
    				}
    				left++;
    			}
    			else if(sum <0)
    			{
    				list.set(right,  0);
    				list.set(left,  sum);
    				if(!isCounted.contains(left))
    				{
    					count++;
    					isCounted.add(left);
    				}
    				if(!isCounted.contains(right))
    				{
    					count++;
    					isCounted.add(right);
    				}
    				right--;
    			}
    			else
    			{
    				list.set(right, 0);
    				list.set(left,  0);
    				if(!isCounted.contains(left))
    				{
    					count++;
    					isCounted.add(left);
    				}
    				if(!isCounted.contains(right))
    				{
    					count++;
    					isCounted.add(right);
    				}
    				left++;
    				right--;
    			}
    		}
    		else
    		{
    			break;
    		}
    	}
    	
    	for(int x : list)
    	{
    		if(x < 0)
    		{
    			return -1;
    		}
    	}
    	return count;
    }

    
  
	public static void main(String args[])
	{
		int[] a = {4,-1,10,2,3};
		int[] b = {3,2,0,1,4};
		ArrayList<Integer> fileSize = new ArrayList<>();
		for (int value : a) {
            fileSize.add(value);
        }
		ArrayList<Integer> minSize = new ArrayList<>();
		for (int value : b) {
            minSize.add(value);
        }
		System.out.println(protectedOrNot(fileSize, minSize));
		System.out.println(protectedOrNot1(fileSize, minSize));
		
		
	}

}
