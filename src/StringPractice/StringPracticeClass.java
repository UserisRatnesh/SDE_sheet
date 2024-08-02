package StringPractice;



class TrieNode {
	char data;
	TrieNode[] children;
	boolean isTerminal;

	public TrieNode(char data) {
		this.data = data;
		this.children = new TrieNode[26];
		this.isTerminal = false;

		// assign those children a null value
		for (int i = 0; i < 26; i++) {
			children[i] = null;
		}
	}

	public void insertWord(TrieNode root, String word) {

		int n = word.length();

		if (n == 0) {
			root.isTerminal = true;
			return;
		}

		int charIndex = word.charAt(0) - 'a';
		TrieNode child;
		if (root.children[charIndex] == null) {
			// This is not the direct child of the root
			// So make it one
			char data = word.charAt(0);
			child = new TrieNode(data);
			root.children[charIndex] = child;
		} else {
			child = root.children[charIndex];
		}

		insertWord(child, word.substring(1));

	}

}

public class StringPracticeClass {


	// TC = O(m+n)
	// SC = O(m)
	public static void KMP(String s, String pattern) {
		int n = s.length();
		int pl = pattern.length();

		int[] lps = new int[pl];
		lps[0] = 0;
		int length = 0;
		int idx = 1;
		while (idx < pl) {
			if (pattern.charAt(idx) == pattern.charAt(length)) {
				length++;
				lps[idx] = length;
				idx++;
			} else {
				if (length == 0) {
					lps[idx] = 0;
					idx++;
				} else {
					length = lps[length - 1];
				}
			}
		}

		int j = 0;
		int i = 0;
		while (i < n) {
			if (pattern.charAt(j) == s.charAt(i)) {
				i++;
				j++;
			}

			if (j == pl) { // Here j can never be equal to zero, if j == 0 then array out of bound exception
				System.out.println("Found at index : " + (i - pl));
				j = lps[j - 1];
			} else if (pattern.charAt(j) != s.charAt(i)) {
				if (j == 0) {
					i++;
				} else {
					j = lps[j - 1];
				}
			}

		}
	}

	// TC = O(n*m)
	// SC = O(1)
	public static boolean bruteForcePatternFinding(String s, String pattern) {
		int n = s.length();
		int pl = pattern.length();

		int left = 0;
		int right = 0;
		while (right < n) {

			int length = right - left + 1;
			if (length < pl) {
				right++;
				continue;
			}

			if (length > pl) {
				left++;
			}

			if (pattern.equals(s.substring(left, right + 1))) {
				return true;
			}
			right++;
		}

		return false;
	}


	// TC = O(n)
	// SC = O(1)
	public int romanToInt(String s) {
		int n = s.length();
		int ans = 0;
		int prevValue = 0;
		for (int i = n - 1; i >= 0; i--) {
			char c = s.charAt(i);

			int currValue = 0;
			if (c == 'I') {
				currValue = 1;
			} else if (c == 'V') {
				currValue = 5;
			} else if (c == 'X') {
				currValue = 10;

			} else if (c == 'L') {
				currValue = 50;

			} else if (c == 'C') {
				currValue = 100;

			} else if (c == 'D') {
				currValue = 500;

			} else {
				currValue = 1000;

			}

			if (currValue < prevValue) {
				ans -= currValue;
			} else {
				ans += currValue;
			}
			prevValue = currValue;
		}
		return ans;
	}


	// TC = O(n)
	// SC = O(1)
	public int myAtoi(String s) {

		long ans = 0;
		s = s.trim();
		int n = s.length();
		boolean isNegative = false;

		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			int value = c-48;

			if( c == '-')
			{
				if(i == 0)
				{
					isNegative = true;
				}
				else
				{
					break;
				}
			}
			else if(c == '+')
			{
				if(i != 0)
				{
					break;
				}
			}else if(value >=0 && value <= 9){

				if(isNegative)
				{
					if(ans >= 0)
					{
						ans = -1 * ans;
					}
					ans = ans * 10 - value;
				}
				else{
					ans = ans * 10 + value;
				}

				if(ans <= Integer.MIN_VALUE)
				{
					ans = Integer.MIN_VALUE;
				}
				if(ans >= Integer.MAX_VALUE){
					ans = Integer.MAX_VALUE; 
				}
			}else{
				break;
			}

		}

		return (int)ans;
	}


	// TC = O(n*l)
	// SC = O(n)
	public static String longestCommonPrefix(String[] strs) {

		// We will try to implement tries data structure
		// and store all the words of the string in that
		// and will search to find the largest common prefix
		TrieNode root = new TrieNode('\0');
		int n = strs.length;
		for (int i = 0; i < n; i++) {
			root.insertWord(root, strs[i]);
		}
		// Search for the largest common prefix

		String ans = helper(root, "");
		return ans;

	}

	public static String helper(TrieNode root, String ans) {

		if (root == null) {
			return ans;
		}

		int childCount = 0;
		TrieNode child = null;
		// Find the number of children
		for (int i = 0; i < 26; i++) {
			if (root.children[i] != null) {
				childCount++;
				child = root.children[i];
			}
		}

		if (childCount == 1 && child != null) {
			if (child.isTerminal) {
				return ans + child.data;
			}
			return helper(child, ans + child.data);
		}
		return ans;
	}
	
	
	// TC = O(m+n), In best and average case, but O(m*n) in worst case
	// SC = O(length(B))	
    public static int repeatedStringMatch(String A, String B) {
        if (A.isEmpty() || B.isEmpty()) {
            return -1;
        }
        int count = 1;
        StringBuilder sbA = new StringBuilder(A);
        while (sbA.length() < B.length()) {
            sbA.append(A);
            count++;
        }

        if (RobinKarp(sbA.toString(), B)) {
            return count;
        }
        if (RobinKarp(sbA.append(A).toString(), B)) {
            return count + 1;
        }
        return -1;
    }




	public static final long MOD = 1_000_000_007;
	
	public static boolean RobinKarp(String text, String pattern) {
		if (text.length() < pattern.length()) {
			return false;
		}

		int n = text.length();
		int m = pattern.length();

		long power = 1;
		long BASE = 256;

		for (int i = 1; i <= m - 1; i++) {
			power = (power * BASE) % MOD;
		}

		long patternHashCode = 0;
		for (int i = 0; i < m; i++) {
			patternHashCode = (patternHashCode * BASE + pattern.charAt(i)) % MOD;
		}

		long textHashCode = 0;
		for (int i = 0; i < n; i++) {
			textHashCode = (textHashCode * BASE + text.charAt(i)) % MOD;

			if (i + 1 < m) {

				continue;
			}

			if (i + 1 > m) {
				textHashCode = (textHashCode - text.charAt(i - m) * power) % MOD;
			}
			if (textHashCode < 0) {
				textHashCode += MOD;
			}

			if (textHashCode == patternHashCode && text.substring(i - m + 1, i + 1).equals(pattern)) {
				return true;
			}
		}

		return false;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
