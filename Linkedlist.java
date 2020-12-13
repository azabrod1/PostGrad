import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Linkedlist<E> {
	protected Node head;
	
	public Linkedlist(){}
	
	public Linkedlist(Collection<E> elements){
		
		if(elements.size() == 0) return;
		
		Iterator<E> it = elements.iterator();
		
		this.head = new Node(it.next());
		
		Node curr = this.head;
		
		while(it.hasNext())
			curr = (curr.next = new Node(it.next()));
		
		
		
	}
	
	class Node{
		
		E item;
		Node next;
		
		public Node(E _item, Node _next){
			this.item = _item;
		    this.next = _next;
		}
		
		public Node(E _item){
			this(_item, null);
		}
		
	}
	
	public void rotate(final int positions){
		
		Node ptr1 = head, ptr2 = head;
		
		for(int p = 0; p < positions; ++p)
			ptr2 = ptr2.next;
		
		if(ptr2 == null) return;
		
		while(ptr2.next != null){
			ptr1 = ptr1.next; ptr2 = ptr2.next;
		}
		
		ptr2.next = head;
		head = ptr1.next;
		ptr1.next = null;
		
	}
	
	
	public void deleteAlternates(){
		
		Node curr = head;
		
		while(curr != null && curr.next != null){
			curr.next = curr.next.next;
			curr = curr.next;
		}
		
	}
	
	
	public void insert(E _item){
		
		this.head = new Node(_item, head);
		
	}
	
	public E getHead(){
		if(head == null) return null;
		
		return head.item;
	}
	
	public E deleteHead(){
		
		E toReturn = head.item;
		head       = head.next;		
		
		return toReturn;
	}
	
	public E remove(int index){
		if(index == 0)
			return deleteHead();
		
		Node curr = head;
		for(int n = 0; n < index - 1; ++n)
			curr = curr.next;
		
		E toReturn = curr.next.item;
		curr.next  = curr.next.next;
		
		return toReturn;
	
	}
	
	public E get(int index){
		Node curr = head;
		for(int n = 0; n < index; ++n)
			curr = curr.next;
		
		return curr.item;
		
	}
	
	public boolean isCycle(){
		Node slow, fast;
		slow = fast = this.head; 
		
		while(fast != null){
			slow = slow.next;
			
			if((fast = fast.next) == null) return false;
			
			fast = fast.next;
			
			if(fast == slow) return true;
			
		}
				
	   return false;
	}
	
	public static  Linkedlist<Integer> getCycleExample(){
		Linkedlist<Integer> x = new Linkedlist<Integer>();
		
		for(int i = 0; i < 10; ++i)
			x.insert(Integer.valueOf(i));
		
		Linkedlist<Integer>.Node curr = x.head;
		for(int n = 0; n < 4; ++n)
			curr = curr.next;
		
		Linkedlist<Integer>.Node curr2 = x.head;
		for(int n = 0; n < 9; ++n)
			curr2 = curr2.next;
		
		curr2.next = curr;
			
		return x;
	}
	
	
	public void reverse(){
		
		if(head == null || head.next == null) return;
		
		head = reverse(null, this.head);
		
		
	}
	
	private Node reverse(Node prev, Node curr){
		Node newHead; 
		
		if(curr.next == null) newHead = curr;
		else 
			newHead = reverse(curr, curr.next);
		
		curr.next = prev;
		
		return newHead;
		
	}
	

	public int size(){
		int size = 0;
		Node curr = head;
		while(curr != null){
			curr = curr.next;
			++size;
		}
		return size;
	}
	
	@Override
	public String toString(){
		
		if(head == null)
			return "Empty List\n";
	
		StringBuilder sb = new StringBuilder();
		Node curr = head;
		while(curr.next != null){
			sb.append(curr.item + " --> ");
			curr = curr.next;
		}
		
		sb.append(curr.item);
		return sb.toString();
		
	}
	
	
	
	

}
