package Stack_And_Queue_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;



class LFUCache {

    HashMap<Integer, DoubleLinkedList> freqList;
    HashMap<Integer, Node> map;
    final int capacity;
    int minFreq;
    int currSize;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.freqList = new HashMap<>();
        this.map = new HashMap<>();
        this.minFreq = 0;
        this.currSize = 0;
    }

    public int get(int key) {
        Node currNode = map.get(key);
        if (currNode == null) {
            return -1;
        }
        updateNode(currNode);
        return currNode.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            updateNode(node);
            return;
        }

        if (this.currSize == this.capacity) {
            // remove node from freqList having minimum freq
            DoubleLinkedList dll = freqList.get(minFreq);
            Node rem = dll.tail.prev;
            dll.remove(rem);
            map.remove(rem.key);
            this.currSize--;
        }

        // Add a new node
        Node add = new Node(key, value);
        DoubleLinkedList dll = freqList.getOrDefault(1, new DoubleLinkedList());
        dll.addNode(add);
        freqList.put(1, dll);
        map.put(key, add);
        this.minFreq = 1;
        this.currSize++;
    }

    private void updateNode(Node node) {
        // Remove the node from curr freqList
        DoubleLinkedList dl = freqList.get(node.frequency);
        dl.remove(node);

        // update the min freq
        if (dl.listSize == 0 && minFreq == node.frequency) {
            this.minFreq++;
        }

        // increase the freq
        node.frequency++;

        // Add the node to new freq list by increasing the freq
        DoubleLinkedList dll = freqList.getOrDefault(node.frequency, new DoubleLinkedList());
        dll.addNode(node);
        freqList.put(node.frequency, dll);
    }

    class Node {
        int key;
        int value;
        int frequency;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
            this.prev = null;
            this.next = null;
        }
    }

    class DoubleLinkedList {
        int listSize;
        Node head;
        Node tail;

        public DoubleLinkedList() {
            this.listSize = 0;
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            this.head.next = this.tail;
            this.tail.prev = head;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            this.listSize--;
        }

        public void addNode(Node node) {
            this.listSize++;
            node.next = head.next;
            head.next = node;
            node.next.prev = node;
            node.prev = head;
        }
    }
}


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
