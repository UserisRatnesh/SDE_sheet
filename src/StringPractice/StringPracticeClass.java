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


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
