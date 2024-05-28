package LinkedList_2;

import java.util.Stack;

class ListNode 
{
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
	}
}

class Pair<F, S>
{
	private F first;
	private S second;
	
	
	public Pair(F first, S second)
	{
		this.first=  first;
		this.second = second;
	}
	
	public F getFirst()
	{
		return this.first;
	}
	
	public S getSecond() 
	{
		return this.second;
	}
	
	public void setFirst(F first)
	{
		this.first = first;
	}
	
	public void setSecond(S second)
	{
		this.second = second;
	}

}


public class LinkedList_2_use 
{
	
	// Both optimal 
	// TC = O(2*n)
	// SC  = O(1)
	
	public static ListNode getIntersectionNodeOptimal1(ListNode headA, ListNode headB) 
    {
        ListNode a = headA;
        ListNode b = headB;
        
        // simultaneously iterate over both the ll
        // if you reach the end of first ll then assign the temp1 equal to head of second ll and vice versa for second ll
        while( a!=b)
        {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }

        return a;
        

    }
	public static ListNode getIntersectionNodeOptimal2(ListNode headA, ListNode headB) 
    {
        ListNode a = headA;
        ListNode b = headB;
        
        // find length of first ll
        int l1 = 0;
        while(a != null)
        {
            l1 ++;
            a = a.next;
        }
        // find length of second ll
        int l2 = 0;
        while(b != null)
        {
            l2++;
            b = b.next;
        }
        // find the difference 
        int diff = Math.abs(l2 - l1);

        // simultaneously iterate for the difference in sorter ll length

        a = headA;
        b = headB;
        if(l1 > l2)
        {
            while(diff > 0)
            {
                a = a.next;
                diff--;
            }
        }
        else
        {
            while(diff > 0)
            {
                b = b.next;
                diff--;
            }
        }

        // now the difference have been travelled
        // Now traverse simultaneously on both and will find the common one
        while(a != b)
        {
            a = a.next;
            b = b.next;
        }

        return a;
    }
    
	
	// TC = O(n)
    // SC = O(K)

    public static ListNode reverseKGroup(ListNode head, int k)
    {
        // find length of ll
        int l = 0;
        ListNode temp = head;
        while(temp != null)
        {
            l++;
            temp = temp.next;
        }

        int possibleReverse = l/k;
        ListNode newHead = null;
        ListNode tail = null;
        while(possibleReverse > 0)
        {
            ListNode headToPass = tail == null ? head : tail.next;
            Pair<ListNode, ListNode> pair = reverse(headToPass, k);
            if(newHead == null)
            {
                newHead = pair.getFirst();
                tail = pair.getSecond();
            }
            else{
                tail.next = pair.getFirst();
                tail = pair.getSecond();
            }
            possibleReverse--;
        }

        return newHead;
    }
    public static Pair<ListNode, ListNode> reverse(ListNode head, int k)
    {
        Stack<ListNode> stk = new Stack<>();

        ListNode temp = head;

        while( k > 0 && temp != null)
        {
            stk.push(temp);
            temp = temp.next;
            k--;
        }

        if(k > 0)
        {
            return new Pair<>(head, null);
        }

        // reverse the ll
        ListNode end = temp;
        
        ListNode newHead = null;
        temp = null;

        while(!stk.isEmpty())
        {
            ListNode pop = stk.pop();
            if(newHead == null)
            {
                newHead = pop;
                temp = pop;
            }
            else
            {
                temp.next = pop;
                temp = temp.next;
            }
        }

        temp.next = end;

        return new Pair<>(newHead, temp);

    }


    
    
	public static void main(String[] args)
	{

	}

}
