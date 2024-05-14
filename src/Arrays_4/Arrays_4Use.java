package Arrays_4;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Arrays_4Use 
{
	
	// when array contains only positive values
	// O(2*n) => because inner loop only runs for total of n i.e it does not run n times for every right
	public static int longestSubarrayWithSumK(int []a, long k) 
    {
        int n = a.length; // size of the array.

        int left = 0, right = 0; // 2 pointers
        long sum = a[0];
        int maxLen = 0;
        while (right < n) {
            // if sum > k, reduce the subarray from left
            // until sum becomes less or equal to k:
            while (left <= right && sum > k) {
                sum -= a[left];
                left++;
            }

            // if sum = k, update the maxLen i.e. answer:
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Move forward thw right pointer:
            right++;
            if (right < n) sum += a[right];
        }

        return maxLen;
    }
	
	// when array contains positive, negative and zero
	// O(n * search in HashMap )
	public static int getLongestSubarray(int []arr, int k) 
	{
		HashMap<Integer, Integer> prefSum = new HashMap<>();
        int maxLen = 0;
        int sum = 0;
		int n = arr.length;
        for(int i=0; i<n; i++)
        {
            sum += arr[i];
            if(sum == k)
            {   
                maxLen = i+1;
            }
            else
            {
				int rem = sum-k;
                if(prefSum.containsKey(rem))
                {
                    int index = prefSum.get(rem);
                    maxLen = Math.max(maxLen, i-index);
                }
            }
			if(!prefSum.containsKey(sum))
			{
				prefSum.put(sum, i);
			}
        }
        return maxLen;
	}
	
	
	// count the number of sub-array having sum equals k
	// similar to finding sub-array having sum equals k
	// update -> instead of storing index of sum-k, stores the number of times the sum has already appeared 
	//				till now, and increase the ansCount by that count.
	public static int subarraySum(int[] nums, int k) 
    {
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int sum = 0;
        for(int i=0; i<n; i++)
        {
            sum += nums[i];

            if(sum == k)
            {
                ans++;
            }

            int rem = sum - k;
            if(map.containsKey(rem))
            {
                int count = map.get(rem);
                ans += count;
            }

            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return ans;
    }
	
	
	// count sub-array with xor k  
	public static int subarrayXOR_B(ArrayList<Integer> A, int B) 
    {
        int n = A.size();
        int ans = 0;
        
        for(int i=0; i<n; i++)
        {
            int xor = A.get(i);
            if(xor == B)
            {
                ans++;
            }
            for(int j=i+1; j<n; j++)
            {
                xor = xor ^ A.get(j);
                if(xor == B)
                {
                    ans++;
                }
            }
        }
        return ans;
    }
	
	
	// finding length of longest substring with non repeating character
	// TC -> O(n^2 * 1 OR n) => 1 OR n depending upon .containsKey() method of map
	public static int lengthOfLongestSubstring(String s) 
    {
        int n = s.length();
        int maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++)
        {
            String substring = "";

            for(int j=i; j<n; j++)
            {
                char c = s.charAt(j);
                if(map.containsKey(c))
                {
                    break;
                }
                substring += c;
                map.put(c, j);
            }
            maxLen = Math.max(maxLen, substring.length());
            map.clear();
        }

        return maxLen;
        
    }
	
	// method - 2
	// TC => O(2*n) 
	// SC => O(n+n)
	public int lengthOfLongestSubstring2(String s) 
    {
        int n = s.length();
        if(n == 0)
        {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        int maxLen = 1;
        String substr = ""+s.charAt(0);
        map.put(s.charAt(0), 0);
        for(int i=1; i<n; i++)
        {
            char c = s.charAt(i);
            if(map.containsKey(c))
            {
                int indexOfCinSubstring = substr.indexOf(c);
                substr = substr.substring(indexOfCinSubstring+1) + c;
                int threshold = map.get(c);

                // remove all characters stored in map present before c
                // Collect keys to be removed
                List<Character> keysToRemove = new ArrayList<>();
                for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                    if (entry.getValue() <= threshold) {
                        keysToRemove.add(entry.getKey());
                    }
                }

                // Remove elements based on collected keys
                for (Character key : keysToRemove) {
                    map.remove(key);
                }
            }
            else
            {
                substr += c;
            }
            maxLen = Math.max(maxLen, substr.length());
            map.put(c, i);
        }

        return maxLen;
    }
	
	
	public static void main(String[] args) 
	{
		
		ArrayList<Integer> a = new ArrayList<>();
		int b = 6;
		a.add(4);
		a.add(2);
		a.add(2);
		a.add(6);
		a.add(4);
		System.out.println(subarrayXOR_B(a,b));
	}

}
