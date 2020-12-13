import java.util.Collection;
import java.util.Iterator;



public class DoublyLinkedList<E> extends Linkedlist<E> {

	protected DoubleNode tail;
	
	class DoubleNode extends Node{
		
		DoubleNode prev;
		
		
		public DoubleNode(E _item, DoubleNode _next, DoubleNode _prev){
			super(_item, _next);
			this.prev = _prev;
		}
		
		public DoubleNode(E _item){
			this(_item, null, null);
		}
		
	}
	
	public void reverse(){
		if(head == null || head == tail) return;
		
		DoubleNode curr = (DoublyLinkedList<E>.DoubleNode) head, ptr;
		
		
		while(curr != null){
			ptr = (DoublyLinkedList<E>.DoubleNode) curr.next;
			curr.next = curr.prev;
			curr.prev = ptr;
			curr = curr.prev; //Move on to next node
			
		}
	
		//Swap pointers
		ptr = (DoublyLinkedList<E>.DoubleNode) head; head = tail; tail =  ptr;
		
	}
	
	
	
	public void insert(E _item){
		if(this.tail == null) 
			this.head  = this.tail = new DoubleNode(_item, null, null);
		
		else{
			((DoubleNode) this.head).prev = new DoubleNode(_item, (DoubleNode) head,  null);
			this.head = ((DoubleNode) this.head).prev;
		}
	}
	
	public DoublyLinkedList(){;}


	public DoublyLinkedList(Collection<E> elements){

		if(elements.size() == 0) return;

		Iterator<E> it = elements.iterator();

		DoubleNode curr = new DoubleNode(it.next());

		this.head = curr;

		while(it.hasNext())
			curr = (DoublyLinkedList<E>.DoubleNode) (curr.next = new DoubleNode(it.next(), null, curr));
		
		this.tail = curr;
		
		
	}
	
	
}
