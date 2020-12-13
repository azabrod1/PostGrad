package binaryTree;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
	
	public BinarySearchTree() { }
	
	private BinarySearchTree(BinaryTree<E>.Node _root) {
		this.root = _root;
	}
	
	public static <E extends Comparable<E>> BinarySearchTree<E> getFromOrders(E[] preorder, E[] inorder){
			
		TreeMaker<E> tool = new TreeMaker<E>(preorder, inorder);
		return tool.run();
	
	}
	
	private static class TreeMaker<E extends Comparable<E>> {
		
		private final E[] preorder, inorder;
		private int preIdx = 0;
		private final BinarySearchTree<E> tree;
		
		public TreeMaker(final E[] preorder, final E[] inorder){
			this.preorder = preorder;
			this.inorder  = inorder;
			this.tree     = new BinarySearchTree<E>();
		}
		
		public BinarySearchTree<E> run(){
			
			tree.root = getFromOrders(0, preorder.length-1);
		
			return tree;
		}
		
		
		
		private int linearSearch(E[] array, E toFind){
			for(int i = 0; i < array.length; ++i)
				if(array[i].equals(toFind))
					return i;
			return -1;
		}
		
		private BinarySearchTree<E>.Node getFromOrders(int lo, int hi){
			
			if(hi < lo) return null;
			
			E next = preorder[preIdx++];
			if(hi == lo) return tree.new Node(next, null, null);
			
			int idx = linearSearch(inorder, next); 
			
			return tree.new Node(next, getFromOrders(lo, idx -1 ), getFromOrders(idx+1, hi));
						
		
	}
	
				
		
		
	}
	
	
	
	
	public static <E extends Comparable<E>> BinarySearchTree<E> fromPostOrder(E[] array){
		
		BinarySearchTree<E> bt = new BinarySearchTree<E>();
		bt.root = fromPostOrder(bt, array, 0, array.length -1);
		System.out.println(bt.root);
		return bt;
		
	}
	
	
	private static <E extends Comparable<E>> BinarySearchTree<E>.Node fromPostOrder(BinarySearchTree<E> tree, E[] array, int from, int to){
		
		if(to < from) return null;
		
		if(from == to) return tree.new Node(array[to]);
		
		E root = array[to];

		int fromLeft = to - 1;
				
		while(fromLeft >= from){
			if(array[fromLeft].compareTo(root) < 0)
				break;
			--fromLeft;
		}
		
	
		return tree.new Node(array[to], fromPostOrder(tree, array, from, fromLeft), fromPostOrder(tree, array, fromLeft+1, to-1) );
	}
	
	
	
	private boolean add(E item, Node curr){ //return true if item added, false if already exists
		
		if(curr.item.compareTo(item) == 0) return false;

		if(item.compareTo(curr.item) < 0){
			if(curr.left == null){
				curr.left = new Node(item);
				return true;
			}
			else
				return add(item, curr.left);

		}


		if(curr.right == null){
			curr.right = new Node(item);
			return true;
		}
		else
			return add(item, curr.right);

	}
	
	public void removeSmallest(){
		if(root == null) return;
		//root is the smallest
		if(root.left == null) 
			root = root.right;
		
		
	
	}
	
	public static boolean isValidPostOrder(int[] trav){
		
	
		return isValidPostOrder(trav, 0, trav.length-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static boolean isValidPostOrder(final int[] trav, final int from, final int to, int lo, int hi){

		if(to < from) return true;
		
		if(to == from)
			return trav[to] >= lo && trav[to] <= hi;
		
		int root = trav[to]; 
		
		if(root < lo || root > hi) return false;
		
		int midIdx;
		
		for(midIdx = to - 1; midIdx >= from; --midIdx )
			if(trav[midIdx] < root)
				break;
				
		return isValidPostOrder(trav, from, midIdx, lo, Math.min(hi, root)) &&
				isValidPostOrder(trav, midIdx + 1, to-1, Math.max(root, lo), hi);
		
	}
	
		
	
	@Override
	public boolean add(E item) {		
		if(root == null){
			root = new Node(item); return true;
		}
		return add(item, root);
	}
	
	protected int depth(E element, Node root){
		
		if(root == null) return -1;
		
		if(root.item.compareTo(element) == 0) return 0;
		
		if(root.item.compareTo(element) < 0)
			return 1 + depth(element, root.right);
		
		    return 1 + depth(element, root.left);
	}
	
	private int shortestDistance(E min, E max, Node n){
		if(n.item.compareTo(min) < 0)
			return shortestDistance( min, max, n.right);
		
		
		if(n.item.compareTo(min) == 0)
			return 1+depth(max, n.right);
		
		
		if(n.item.compareTo(max) < 0)
			return 2+depth(min, n.left) + depth(max, n.right) ;
		
		
		if(n.item.compareTo(max) == 0)
			return 1+depth(min, n.left);
		
			return  shortestDistance( min, max, n.left);
		
	}
	
	@Override
	public int shortestDistance(E x, E y){
		if(x.compareTo(y) == 0) return 0;
		
		if(x.compareTo(y) < 0) return shortestDistance(x, y, root);
		
		return shortestDistance(y,x, root);
		
	}
	
	protected Node find(E element){
		return find(element, root);
	}
	
	protected Node find(E item, Node node){
		if(node == null) return null;
		
		if(node.item.compareTo(item) == 0) return node; 																																																																			
		
		return item.compareTo(node.item) < 0? find(item, node.left) : find(item, node.right);
				
	}
	
	public BinaryTree<E> getSubtree(E element){
		
		return new BinarySearchTree<E>(find(element));
		
	}

	@Override
	public boolean delete(E element) {
		return false;
	}
	
	public void print(){
		if(root == null) System.out.println("Empty tree");
		BTreePrinter printer = new BTreePrinter();
		printer.printNode(root);
	}
	
	
	 class BTreePrinter {

	    public void printNode(Node root) {
	        int maxLevel = this.maxLevel(root);

	        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	    }

	    private void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
	        if (nodes.isEmpty() || BinarySearchTree.isAllElementsNull(nodes))
	            return;

	        int floor = maxLevel - level;
	        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
	        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
	        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

	        BinarySearchTree.printWhitespaces(firstSpaces);

	        List<Node> newNodes = new ArrayList<Node>();
	        for (Node node : nodes) {
	            if (node != null) {
	                System.out.print(node.item);
	                newNodes.add(node.left);
	                newNodes.add(node.right);
	            } else {
	                newNodes.add(null);
	                newNodes.add(null);
	                System.out.print(" ");
	            }

	            BinarySearchTree.printWhitespaces(betweenSpaces);
	        }
	        System.out.println("");

	        for (int i = 1; i <= endgeLines; i++) {
	            for (int j = 0; j < nodes.size(); j++) {
	            	BinarySearchTree.printWhitespaces(firstSpaces - i);
	                if (nodes.get(j) == null) {
	                	BinarySearchTree.printWhitespaces(endgeLines + endgeLines + i + 1);
	                    continue;
	                }

	                if (nodes.get(j).left != null)
	                    System.out.print("/");
	                else
	                	BinarySearchTree.printWhitespaces(1);

	                BinarySearchTree.printWhitespaces(i + i - 1);

	                if (nodes.get(j).right != null)
	                    System.out.print("\\");
	                else
	                	BinarySearchTree.printWhitespaces(1);

	                BinarySearchTree.printWhitespaces(endgeLines + endgeLines - i);
	            }

	            System.out.println("");
	        }

	        printNodeInternal(newNodes, level + 1, maxLevel);
	    }

	    private int maxLevel(Node node) {
	        if (node == null)
	            return 0;

	        return Math.max(this.maxLevel(node.left), this.maxLevel(node.right)) + 1;
	    }


	}
	 
	    private static void printWhitespaces(int count) {
	        for (int i = 0; i < count; i++)
	            System.out.print(" ");
	    }
	    
	    private static <T> boolean isAllElementsNull(List<T> list) {
	        for (Object object : list) {
	            if (object != null)
	                return false;
	        }

	        return true;
	    }


}
