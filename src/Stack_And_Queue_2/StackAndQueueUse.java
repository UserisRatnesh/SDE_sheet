package Stack_And_Queue_2;

import java.util.ArrayList;
import java.util.Stack;

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

	public static void main(String[] args) 
	{

	}

}
