package binaryTree;
import java.sql.Time;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public abstract class BinaryTree<E> {

	protected class Node{
		E item;
		Node left, right;
		
		public Node(E item, Node left, Node right){
			this.item = item; this.left = left; this.right = right;
		}
		
		public Node(E item){
			this(item, null, null);
		}
		
		public Node(){
			this(null, null, null);
		}
		
	}
	protected Node root;
	
	private int shortestDistance(E x, E y, Node node){
		if(node == null) return 0;
		int left  = shortestDistance(x, y, node.left);
		int right = shortestDistance(x, y, node.right);
		boolean found = node.item.equals(x) || node.item.equals(y);
		
		if(left + right < 0) return left+right;
		
		if(left > 0 && right > 0 || ((left+right > 0) && found)) return -left-right;
		
		return left+right+ (found || left+right > 0? 1:0);
		
		
	}
	
	
	protected BinaryTree<E>.Node find (E toFind, BinaryTree<E>.Node root){
		if(root == null) return null;
		
		if(root.item.equals(toFind))
			return root;
		
		BinaryTree<E>.Node left = find(toFind, root.left);
		
		return left!=null? left: find(toFind, root.right);
		
	}
	
	
	
	
	
	
	
	protected BinaryTree<E>.Node find (E toFind){
		
		return find(toFind, this.root);
		
	}
	
	private static <E> boolean sameTree(BinaryTree<E>.Node n1, BinaryTree<E>.Node n2){
		
		if(n1 == null && n2 == null) return true;
		
		if(n1 == null || n2 == null || !n1.item.equals(n2.item)) return false;
		
		return sameTree(n1.left, n2.left) && sameTree(n1.right, n2.right);
		
		
	
	}
	private static <E> boolean isSubTree(BinaryTree<E>.Node node, BinaryTree<E> child){


		if(node == null) return false;

		if(sameTree(node, child.root)) return true;		
		
		return isSubTree(node.left, child) || isSubTree(node.right, child);
		
	
	}
	
	public BinaryTree<E> getSubtree(E element){
		
		throw new UnsupportedOperationException();
		
	}
	
	public static <E> boolean isSubTree(BinaryTree<E> parent, BinaryTree<E> child){
		
		if(child == null || child.root == null) return true;
		return isSubTree(parent.root, child);
		
	
	}
	
	public static <E extends Number> List<List<E>> getPaths(BinaryTree<E> bt, int target){
		List<List<E>> paths = new ArrayList<List<E>>();
		List<E> numsSoFar  = new ArrayList<E>();
		
		getPaths(numsSoFar, bt.root, paths, 0, target );
		
		return paths;
		
	}
	
	private static <E extends Number> void getPaths( List<E> soFar, BinaryTree<E>.Node curr, List<List<E>> paths, int sum, int target){
		
		if(curr == null) return;
		
		soFar.add(curr.item);
		
		sum += curr.item.intValue();
		
		//boolean[] targets = new boolean[soFar.size()];
		
		int sumX = sum; 
		
		for(int i = 0; i < soFar.size() - 1; ++i){
			if(sumX == target)
				paths.add(new ArrayList<>(soFar.subList(i, soFar.size())));
			
			sumX -= soFar.get(i).intValue();
			
		}
		
	
		getPaths(soFar, curr.left, paths, sum, target );  
		getPaths(soFar, curr.right, paths, sum, target );
		
		soFar.remove(soFar.size()-1 ); //Pop

	}
	
	public static boolean isBST(BinaryTree<? extends Number> bt){
		int[] ptr = {Integer.MIN_VALUE};
		return isBST(ptr, bt.root);
	}
	
	private static boolean isBST(int[] ptr, BinaryTree<? extends Number>.Node curr ){
		
		if(curr == null) return true;
		
		
		if(!isBST(ptr, curr.left) || curr.item.intValue() < ptr[0]) return false;
		
		ptr[0] = curr.item.intValue();
		
		return isBST(ptr, curr.right); 
		
	
	}
	
	public static BinaryTree<Integer> withPaths(){
		BinaryTree<Integer> tree = new BinarySearchTree<Integer>();
		BinaryTree<Integer>.Node curr = tree.new Node(5);
		tree.root = curr;
		curr.left = tree.new Node(3, tree.new Node(-8), tree.new Node(-6, tree.new Node(6), tree.new Node(3))); curr.right = tree.new Node(-3, tree.new Node(-2), tree.new Node(-6));
		//tree.root = new tree.Node(5, new tree.Node(3), new tree.Node(-3));
		return tree;
		
	}
	
	public int depth(E element){
		return depth(element, this.root);
	}
	
	
	protected int depth(E element, Node root){
		throw new UnsupportedOperationException();
	}
	
	public int shortestDistance(E x, E y){
		if(x.equals(y)) return 0;
		return -shortestDistance(x, y, root);
	}
	

	public int shortestDistance2(E x, E y){
		if(x.equals(y)) return 0;
		return -shortestDistance(x, y, root);
	}
	
	abstract public boolean add(E element);
	
	abstract public boolean delete(E element);
	
	private boolean contains(E toFind, Node node){
		if(node == null) return false;
		
		if(node.item == toFind)
			return true;
		
		return contains(toFind, node.left) || contains(toFind, node.right);
		
	}
	

	
	
	
	public boolean contains(E element){
		return contains(element, root);
	}
	
	public String serializeTree(){
		
		if(this.root == null) return "";
		
		StringBuilder tree = new StringBuilder();

		serializeTree(root, tree);
		
		return tree.toString();
		
			
	}
	
	private final static String MARKER = "null";
	
	private void serializeTree(Node curr, final StringBuilder tree){
		
		if(curr == null){
			tree.append("null,");
			return;
		}
		
		tree.append(curr.item); tree.append(',');
		serializeTree(curr.left, tree);  serializeTree(curr.right, tree);
		
	}
	
	public static BinaryTree<Integer> deserializeTree(String code){
		if(code.equals("")) return new BinarySearchTree<Integer>();
		
		BinaryTree<Integer> tree = new BinarySearchTree<Integer>();
		
		Deque<BinaryTree<Integer>.Node> stack = new ArrayDeque<BinaryTree<Integer>.Node>();
		String[] elements = code.split(",");
		Integer e = Integer.valueOf(elements[0]);
		tree.root = tree.new Node(e);
		stack.push(tree.root);
		BinaryTree<Integer>.Node curr = tree.root;
	
				
		for(int c = 1; c < elements.length; ++c){
			if(curr != null){
				stack.push(curr);
				curr = (curr.left = (elements[c].equals(MARKER))? null : tree.new Node(Integer.valueOf(elements[c])));
			}
			else
				curr = (stack.pop().right = (elements[c].equals(MARKER))? null : tree.new Node(Integer.valueOf(elements[c])));
			
		}
		return tree;
		
	}
	
	
	
	public static boolean canBeBST(int[] preorder) {
		int root = Integer.MIN_VALUE;
		Deque<Integer> stack = new ArrayDeque<>();
		/*
		for(int curr: preorder) {
			if(curr < root)
				return false;
			*/
		//while(!stack.isEmpty() && )
			
		
			
		//}
		
		return true;
		
	}
	
	public int sumMinLevel(){
		if(root == null) return 0;
		Deque<Node> queue = new ArrayDeque<>();
		queue.push(root);
		Node curr, left, right;
		
		boolean isLeaf = false;
		int sizeLevel  = 1;
		int nextLevel  = 0;
		int sum        = 0;
		while(!isLeaf){
			sum = 0;
			while(sizeLevel-- > 0){
				curr = queue.remove();
				sum += (Integer) curr.item;
				left = curr.left; right = curr.right;
				if( (left = curr.left) == null)
					isLeaf = true;
				else{ 
					++nextLevel; queue.add(left);
				}
				if( (right = curr.right) == null)
					isLeaf = true;
				else{ 
					++nextLevel; queue.add(right);
				}
			
			}
			sizeLevel = nextLevel; nextLevel = 0;
		}
		
		return sum;
	}
	
	
	
	

	
}
