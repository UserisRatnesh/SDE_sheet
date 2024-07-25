package StringPractice;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
