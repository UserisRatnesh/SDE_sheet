package LinkedList_and_Arrays;

class ListNode 
{
	int val;
	ListNode next;
	ListNode bottom;
	ListNode(int x) {
		val = x;
		next = null;
		bottom = null;
	}
}


public class LinkedList_and_Arrays_use 
{
	
	// TC = O(n)
    // SC = O(1)
    public ListNode rotateRight(ListNode head, int k) 
    {
        if(head == null || head.next == null || k == 0)
        {
            return head;
        }

        // find the length of linked list
        ListNode prev = head;
        ListNode fwd = head;
        int length = 1;
        // we are taking initial length as 1 since we want the fwd pointer to be at last node
        // so that at last when we want to link it to first we need not to traverse again 
        while(fwd.next != null) // O(n)
        {
            length++;
            fwd = fwd.next;
        }

        // if k is more than length of linked list 
        // then we can only rotate (k % length) times and still it will get the same result
        k = k % length;
        int count = 1;
        while(count != length-k) // O(n % k)
        {
            count++;
            prev = prev.next;
        }

        // now previous is pointing to the (n-k)th node from start
        fwd.next = head;
        head = prev.next;
        prev.next = null;
        return head;
        
    }

	public static void main(String[] args) 
	{
		

	}

}
