import java.math.BigInteger;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Utility.Utility;
import Utility.Clock;
import binaryTree.BinarySearchTree;
import binaryTree.BinaryTree;

public abstract class MainClass {
	
	public static void main(String...args){
	
		//List<CarRace.RaceResult> result = CarRace.doRace(5, 1000000);
		//System.out.println(CarRace.RaceResult.resultToString(result));
		/*
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,1,1,5,6,7,2,1,1,1,1,1,8,1));
		for(int x = 0; x < 18; ++x) list.add(0,Integer.valueOf(101));
		long numOnes = list.stream().parallel().filter(p -> p.equals(Integer.valueOf(101))).count();
		System.out.println(list.size() + "  " + numOnes + "  " + Interview3.getMajorityElement(list));
		*/
		/*
		Random r = new Random();
		int[] array = new int[1000];
		
		for(int i = 0; i < 1000; ++i)
			array[i] = r.nextInt(8)+1;
		
		int[] x = { 1, 4 ,2, 5, 1, 9, 2, 6, 7, 6, 8, 9 };
		System.out.println(Interview3.Jumps(array).length-1);
		
		System.out.println(Interview3.jumps(array));
		
		List<Integer> list = Arrays.asList(4,2,9,12,3,8,5, 15, 120, 3, 1, 0, 43, 90);
		
		Linkedlist<Integer> l = new Linkedlist<>(list);
		
		SortedLinkedList<Integer> s  = new SortedLinkedList<>(l);
		
		System.out.println(l.toString());
		
		System.out.println(s.toString());

		
		final int N = 3241;
		Integer[] arr = Utility.randomArray(N);
		Linkedlist<Integer> l = new Linkedlist<>(Arrays.asList(arr));
		
		SortedLinkedList<Integer> s  = new SortedLinkedList<>(l);
		
		System.out.println(s); */
		
		//trees();
		/*
		char[] order = {'c', 'a', 'f', 't', 's'};
		String[] words = {"caa","caas", "cts", "ftasas", "tas", "tasas"};
		
		System.out.println(Interview3.inCustomOrder(order, words) );
		*/
		
		String string = "ha4ha".replace("4", "Д" + "4" + "Д");
		
		String[] s = string.split("Д");
		
		System.out.println(Arrays.toString(splitButKeepSplitter("h+a*ha-wsd", "(\\*|\\+|-)")));
		
		
	}
	
	static public final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))";
	public static String[] splitButKeepSplitter(String string, String regex) {
		
		final String[] aEach = string.split( String.format(WITH_DELIMITER, regex) );
		return aEach;
				
	}
	
	
	private static void crap(){
		/*
		LinkedList<Integer> ll = new LinkedList<Integer>();
		
		
		for(int i = 100; i >= 0; --i) ll.insert(i);
		
		ll.remove(4);
		System.out.println(ll);
			*/
		/*
		SortedLinkedList<Long> l2 = new SortedLinkedList<>();
		
		Random rand = new Random();
		for(int i = 0; i < 1000; ++i)
			l2.insert(rand.nextLong());
		
		System.out.println(l2);
		
		int[] x = {3,-4,7,1};
		
		Set<Set<Integer>> s = Interview3.zeros(x);
		
		System.out.println(s);
		
		
		LinkedList<Integer> ll = LinkedList.getCycleExample();
		LinkedList<Integer> l2 = new LinkedList<Integer>();
		
		for(int i = 0; i < 100; ++i)
			l2.insert(i);
		System.out.println(l2.isCycle());
		*/
		/*
		BinarySearchTree<Integer> bt = new BinarySearchTree<Integer>();
		final int SIZE = 1000;
		Integer[] ar = new Integer[SIZE];
		
		for(int i = 0; i < SIZE; ++i) ar[i] = i;
		
		shuffle(ar);
		
		System.out.println(Arrays.toString(ar));
		
		for(Integer x: ar) bt.add(x);
		
		bt.print();
		
		
		
		for(int i =0 ; i < SIZE; ++i){
			BinarySearchTree<Integer> sub = (BinarySearchTree<Integer>) bt.getSubtree(i);

			System.out.println(BinaryTree.isSubTree(bt, sub));

			
		}
		*/
		
		/*
		for(int a = 0; a < SIZE; ++a)
			for(int b = 0; b < SIZE; ++b)
				if(a == b) continue;
				else if(bt.shortestDistance(a, b) != bt.shortestDistance2(a, b)) System.err.println("nah mate");
				*/
		
		/*BinaryTree<Integer> e =  BinaryTree.withPaths();
		e.getPaths(e, 0);
		List<List<Integer>> paths = e.getPaths(e, 0);
		
		System.out.println(bt.isBST(bt));
		*/
		
		
		//System.out.println("\n" + bt.shortestDistance(11, 15));
		
		//System.out.println(bt.depth(15, bt.root));
		/*
		Integer[] arr =  {5, 7, 1, 9, 11, 10, 5};
		
		int[] p2 = {1,4,3,8,6,9,5};
		
		int[] nums =  {5, 7, 1, 9, 11, 10, 5}; 
		*/
		
		//System.out.println(BinarySearchTree.isValidPostOrder(p2));
		
		
		//System.out.println(BinarySearchTree.isValidPostOrder(arr));
		
		//Interview3.changeBits(12, 13, 4, 2);
		
		//BinarySearchTree<Integer> bt = BinarySearchTree.fromPostOrder(arr);
	//	System.out.println(BinarySearchTree.isValidPostOrder(nums));
		/*
		double toConvert  = 5212.942147342141;
		String ret = Interview3.toBinary(toConvert);
		System.out.printf("Binary for %f: %s\n", toConvert, ret);
		
		
		System.out.println(Integer.parseInt(ret.split("\\.")[0], 2));
		
		System.out.println("From binary: " + Interview3.fromBinary(ret)); */
		
		//int[] array = {60, 67, 90, -3, -2, 0, 1 , 4, 12, 12, 41, 55};
		
		//for(int e: array)
		//	System.out.println(Interview3.searchSorted(array, e));
		/*
		long start = System.currentTimeMillis();
		
		System.out.println(Interview3.nthPrime(10000000));
		
		System.out.println("Time elapsed : " + (System. currentTimeMillis() - start));
		*/
		
		//trees();	
		/*
		List<String> result = Interview3.permutations("happys");
		List<String> result2 = Interview3.permutationsNoDups("happys");

		
		Set<String> resultSet = new TreeSet<String>(result);
		Set<String> resultSet2 = new TreeSet<String>(result2);

		
		System.out.println("Has No Duplicates  " + (resultSet.size() == result.size()));
		
		System.out.println(result.size() + "   " + result);
		
		System.out.println("Has No Duplicates  " + (resultSet2.size() == result2.size()));
		
		System.out.println(result2.size() + "   " + result2);
		
		Integer o = new Integer(4), r = new Integer(4);
		
		System.out.println(o.equals(r));
		
		int[] nums = {1,2,3,4,4, 5, 9, 12, 12, 1};
		System.out.println(Interview3.getDuplicates(nums));
		
		
	*/
	}
	private static void trees(){
		BinarySearchTree<Integer> bt = new BinarySearchTree<>();
		
		Integer[] inserts = {10, 5,4 , 22, 11,6,19, 16, 25};
		
		for(int e: inserts) bt.add(e);
		bt.print();
		
		String serial = bt.serializeTree();
		System.out.println(serial);
		
		BinaryTree<Integer> bt2 = BinaryTree.deserializeTree(serial);
		
		((BinarySearchTree<Integer>) bt2).print();
		
		System.out.println("Sum: " + bt2.sumMinLevel());
		
		/*
		BinarySearchTree<Integer> tt = new BinarySearchTree<Integer>();
		final int SIZE = 1000;
		Integer[] ar = new Integer[SIZE];
		
		for(int i = 0; i < SIZE; ++i) ar[i] = i;
		
		shuffle(ar);
		
		for(Integer x: ar) tt.add(x);
		
		String ss = tt.serializeTree();
		
		String second = ((BinarySearchTree<Integer>) tt.deserializeTree(ss)).serializeTree();
		
		System.out.println(ss.equals(second));
		
		Integer[] inOrder = {2,5,6,10,12,14,15};

		Integer[] preOrder = {10,5,2,6,14,12,15};
		
		BinarySearchTree<Integer> cTree = BinarySearchTree.getFromOrders(preOrder, inOrder);
		
		cTree.print();
		*/
		

				
	}

	
	static <E> void shuffle(E[] array){
		
		Random r = new Random();
		
		for(int i = array.length - 1; i > 0; --i){
			E temp  = array[i]; int idx = r.nextInt(i);
			array[i] = array[idx]; array[idx] = temp;
			
		}
	}
	
	public enum Days{
		MONDAY("Crappist day"), TUESDAY("PRetty bad too"), WEDNESDAY, THURSDAY, FRIDAY("Best Day"), SATURDAY("great day"), SUNDAY;
		static Days[] indices = values();

		private final String info;

		 Days(String _info){
			this.info = _info; 
		}
		 
		Days(){this.info = "";}
		
		public String getInfo(){
			return info;
		}
	}
	
	public enum Snack{
		Apple(1.80), Grapes(1.2), صصققققق(1.1), Oreos(0.8), Chocolate(2.121), Date(0.60); 
		static Snack[] indices = values();
		private final double price; 
		
		 Snack(double _price){
			this.price = _price;
		}
		 
		 double getPrice(){
			 return price;
		 }
		
	}
	
	public static class Basket{
		private final int[] snacks = new int[Snack.values().length];
		
		public void put(Snack...foods){
			for(Snack s: foods)
				snacks[s.ordinal()]++;
		}
		
		public void put(Snack s){
			snacks[s.ordinal()]++;
		}
		
		public double getTtlPrice(){
			double ttl = 0;
			for(int s = 0; s < snacks.length; ++s)
				ttl += (Snack.indices[s].price * snacks[s]);
				
			return ttl; 
		
		}
		
	}
	

	
	public static class Ex extends Exception{
	
		public Ex(String message) {
	        super(message);
	    }
	
	}
	
	
	public void Клавиатура(){
		System.out.println("dsdsd");
	}
	
	
	public static void go(int i, Scanner sc) throws Ex{
		if(sc == null)
			sc = new Scanner(System.in);

		try{
			if(sc.nextInt() == 5)
				throw new Ex("Hey");
			else
				go(i+1, sc);

		}catch(Ex e){
			System.out.println("I:" + i);
			throw new Ex((e.getMessage() + " " + i));
		}
	}
		
}
	


