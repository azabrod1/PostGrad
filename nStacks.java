import java.util.ArrayDeque;
import java.util.Deque;

public class nStacks {

	private final Node[] buf;
	private final int S; 
	private int next;
	private final int[] stackPtr;
	private Deque<Integer> freeList = new ArrayDeque<Integer>();
	
	public nStacks(int _S, int _size){
		this.buf      = new Node[_size];
		this.S        = _S;
		this.stackPtr = new int[_S];
		this.next     = 0;
	}
	
	
	private int nextNode(){
		if(!freeList.isEmpty())
			return freeList.pop();
		buf[next] = new Node();
		return next++;
	}
	
	
	public void push(int item, int stack){
		int index    = nextNode();
		Node newNode = buf[index];
		
	    newNode.prev = stackPtr[stack];
		
		newNode.item = item;
		stackPtr[stack] = index;
		
	}
	
	public int pop(int stack){
		
		Node toPop = buf[stackPtr[stack]];
		freeList.push(stackPtr[stack]);
		stackPtr[stack] = toPop.prev; 
		
		return toPop.item;
		
	}
	

	
	
	private class Node{
		int prev; 
		int item;
		
	}
	
	/*
	private class Block{
		
		Node[] nodes;
		Block prev;
		
		public Block(Node){
			
		}
		*/
		
		
	
	
}
