package Stack_And_Queue;


import java.util.Stack;


public class Stack_And_Queue_Use 
{
	
	class MyQueue 
	{
	    Stack<Integer> input, output;
	    public MyQueue() 
	    {
	        this.input = new Stack<>();
	        this.output = new Stack<>();
	    }
	    

	    public void push(int x) 
	    {
	        input.add(x);
	    }
	    
	    // TC = O(n) in worst case
	    // But O(1) amortized
	    public int pop() 
	    {
	        if(!output.isEmpty())   return output.pop();

	        while(!input.isEmpty())
	        {
	            output.add(input.pop());
	        }

	        if(output.isEmpty())    return -1;

	        return output.pop();
	    }
	    
	    // TC = O(n) in worst case
	    // But O(1) amortized
	    public int peek() 
	    {
	        if(!output.isEmpty())   return output.peek();

	        while(!input.isEmpty())
	        {
	            output.add(input.pop());
	        }

	        if(output.isEmpty())    return -1;

	        return output.peek();
	        
	    }
	    
	    public boolean empty() 
	    {
	        return input.isEmpty() && output.isEmpty();
	        
	    }
	}

	public static void main(String[] args) 
	{

	}

}
