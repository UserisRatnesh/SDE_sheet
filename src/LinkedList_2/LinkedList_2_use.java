package LinkedList_2;



class ListNode 
{
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
		next = null;
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
    
    
    
	public static void main(String[] args)
	{

	}

}
