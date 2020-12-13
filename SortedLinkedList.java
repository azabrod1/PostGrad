
public class SortedLinkedList<E extends Comparable<E>> extends Linkedlist<E> {
	
	public SortedLinkedList(Linkedlist<E> list){
		int len = list.size();
		this.head = mergesort(list.head, 0, len-1);
		list.head = null;
		
	}
	
	public Node mergesort(Node head, int start, int last){

		if(start == last){
			System.out.println(head.item );
			head.next = null;
			return head;
		}
		
		int _mid = (start + last)/2;
		Node mid = head;
		
		for(int n = start; n < _mid; ++n) 
			mid = mid.next;

		Node  secondHalf = mergesort(mid.next, _mid+1, last), firstHalf = mergesort(head, start, _mid);
		
		return merge(firstHalf, secondHalf);
	
	}
	
	private Node merge(Node l1, Node l2){

		Node newHead, curr;
		if(l1.item.compareTo(l2.item) <= 0){
			newHead = curr = l1;
			l1 = l1.next;
		}
		else{
			newHead = curr = l2;
			l2 = l2.next;
		}
		

		while(l1 != null && l2 != null)
			if(l1.item.compareTo(l2.item) <= 0){
				curr = (curr.next = l1);
				l1 = l1.next;
			}
			else{
				curr = (curr.next = l2);
				l2 = l2.next;
			}
		
		if(l1 != null)
			curr.next = l1;
		else
			curr.next = l2;

		return newHead;
	}

	
	@Override
	public void insert(E item){
		
		if(head == null || item.compareTo(head.item) <= 0){
			super.insert(item);
			return;
		}
		
		Node curr = head;
		
		while(curr.next !=  null && item.compareTo(curr.next.item) > 0)
			curr = curr.next;
		
		Node newNode = new Node(item, curr.next);
		curr.next    = newNode;
		
	}
	

	
	
	
}
