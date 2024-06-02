package LinkedList_and_Arrays;

import java.util.HashMap;

class ListNode 
{
	int val;
	ListNode next;
	ListNode random;
	ListNode(int x) {
		val = x;
		next = null;
		random = null;
	}
}


public class LinkedList_and_Arrays_use 
{
	
	// TC = O(n)
    // SC = O(1)
    public static ListNode rotateRight(ListNode head, int k) 
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
    
    // TC = O(2n)
    // SC = O(n) 
    private static ListNode deepCopyList(ListNode head)
    {
    	if(head == null)
        {
            return null;
        }
    	
    	ListNode temp = head;
    	ListNode newHead = new ListNode(0);
    	ListNode ans = newHead;
    	
    	// Maintain the map for corresponding new nodes
    	HashMap<ListNode, ListNode> correspondingNodes = new HashMap<>();
    	
    	while(temp != null) // O(n)
    	{
    		ListNode newNode = new ListNode(temp.val);
    		ans.next = newNode;
    		ans = ans.next;
    		
    		// updating the corresponding nodes
    		correspondingNodes.put(temp, newNode);

    		// update the pointer
    		temp = temp.next;
    	}
    	
    	// iterate over new created node structure and update the random connection
    	ListNode tempOld = head;
    	ListNode tempNew = newHead.next;
    	while(tempNew != null) // O(n)
    	{
    		ListNode tempOldRandom = tempOld.random;
    		ListNode corresponding = correspondingNodes.get(tempOldRandom); // O(1)
    		tempNew.random = corresponding;
    		
    		// update the pointers
    		tempOld = tempOld.next;
    		tempNew = tempNew.next;
    	}
    	

    	return newHead.next;
    }

    // TC = O(3n)
    // SC = O(1)
    private static ListNode deepCopyListOptimal(ListNode head)
    {
    	/*
    	 * 1. Iterate over the original list and create new node corresponding to each node
    	 * 		and store that node in between the nodes.
    	 * 2. Now Iterate over the original list and find the node random pointer is pointing.
    	 * 3. Now the next of the old node will be it's new node and 
    	 * 		next of it's random node will be random node of new corresponding node.
    	 * 4. Now update the list to have only new nodes.
    	 */
    	
    	ListNode temp = head;
    	
    	// Inserted new nodes between older nodes
    	while(temp != null)
    	{
    		ListNode newNode = new ListNode(temp.val);
    		newNode.next = temp.next;
    		temp.next = newNode;
    		temp = newNode.next;
    	}
    	
    	temp = head;
    	while(temp != null)
    	{
    		// assign new node next to random next
    		temp.next.random = temp.random == null ? null : temp.random.next;
    		
    		temp = temp.next.next;
    	}
    	
    	
    	// Extract the new node structure
    	ListNode newHead = new ListNode(0);
    	ListNode ans = newHead;
    	temp = head;
    	while(temp != null)
    	{
    		newHead.next = temp.next;
    		newHead = newHead.next;
    		temp.next = newHead.next;
    		temp = temp.next;
    	}
    	
    	
    	return ans.next;
    }
    
	public static void main(String[] args) 
	{
		

	}

}
