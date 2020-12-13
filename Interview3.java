import java.util.ArrayDeque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Interview3 {
	
	private static boolean isAlpha(char c){
		return ( (c >= 65 && c <= 90) || (c >= 97 && c <= 122));
			
	}
	
	public static Set<Set<Integer>> zeros(int[] array){
		
		Set<Set<Integer>> toReturn = new HashSet<Set<Integer>>();
		
		Map<Integer, Integer[]> results = new HashMap<Integer, Integer[]>();
		
		results.put(0, new Integer[]{});
		
		for(int e: array){
			if(results.containsKey(-e)){
				Set<Integer> s = Arrays.stream(results.get(-e)).collect(Collectors.toSet());
				s.add(e);
				toReturn.add(s);
			}	
			Map<Integer, Integer[]> toAdd = new HashMap<Integer, Integer[]>();
			results.forEach( (sum, values) -> toAdd.put(sum + e, arrPlusOne(values, e)));
			results.putAll(toAdd);
		}
		
		
		return toReturn;
		
	}
	
	private static String charDequeToString(Deque<Character> list){
		StringBuilder sb = new StringBuilder(list.size());
		
		for(Character ch: list)
			sb.append(ch);
		
		return sb.toString();
			
	}
	

	public static List<String> permutations(String str){
		Character[] free = 
			    str.chars().mapToObj(c -> Character.valueOf((char) c)).toArray(Character[]::new); 
		
		List<String> result = new LinkedList<String>();
		
		permutations(new ArrayDeque<Character>(Arrays.asList(free)), new ArrayDeque<Character>(), result);
		
		return result;
		
	}

	private static void permutations(Deque<Character> free, Deque<Character> soFar, List<String> result){
		if(free.isEmpty())
			result.add(charDequeToString(soFar));
		
		for(int c = 0; c < free.size(); ++c){
			soFar.add(free.removeFirst());
			permutations(free, soFar, result);
			free.add(soFar.removeLast());
				
		}
	}
	
	public static List<String> permutationsNoDups(String str){
		Character[] free = 
			    str.chars().mapToObj(c -> Character.valueOf((char) c)).sorted().toArray(Character[]::new); 
		
		System.out.println(Arrays.toString(free));
		
		List<String> result = new LinkedList<String>();
		
		permutationsNoDups(new ArrayDeque<Character>(Arrays.asList(free)), new ArrayDeque<Character>(), result);
		
		return result;
		
	}
	
	static int jumps(int[] arr){
				
		int nextOne = 1;
		int[] jumpsToReach = new int[arr.length];
		jumpsToReach[0] = 0;
		
		for(int curr = 0; curr < arr.length && nextOne <= arr.length; ++curr)
			for(; nextOne < arr.length &&  nextOne <= curr + arr[curr]; ++nextOne)
				jumpsToReach[nextOne] = jumpsToReach[curr] + 1;
		

		return jumpsToReach[arr.length-1];
	}
	static int[] Jumps (int[] arr)
	{
	    int[] LeftMostReacher = new int[arr.length];
	    //let's see , for each element , how far back can it be reached from 

	    LeftMostReacher[0] = -1; //the leftmost reacher of 0 is -1

	    int unReachableIndex = 1; //this is the first index that hasn't been reached by anyone yet
	    //we use this unReachableIndex var so each index's leftmost reacher is  the first that was able to jump to it . Once flagged by the first reacher , new reachers can't be the leftmost anymore so they check starting from unReachableIndex

	    // this design insures that the inner loop never flags the same index twice , so the runtime of these two loops together is O(n)

	    for (int i = 0; i < arr.length; i++)
	    {
	        int maxReach = i + arr[i];

	        for (; unReachableIndex <= maxReach && unReachableIndex < arr.length; unReachableIndex++)
	        {

	            LeftMostReacher[unReachableIndex] = i;
	        }

	    }

	    // we just go back from the end and then reverse the path

	    int index = LeftMostReacher.length - 1;
	    Stack<Integer> st = new Stack<Integer>();

	    while (index != -1)
	    {
	        st.push(index);
	        index = LeftMostReacher[index];
	    }
	    int[] ret = new int[st.size()];
	    
	    for(int i = 0; i < st.size(); ++i)
	    	ret[i] = arr[st.pop()];
	    
	    return ret;
	   
	}
	
	public static int uniqueElement(int[] arr){
		
		int zeros = ~( 0), ones = 0, twos = 0, zerosToRemove, onesToRemove, twosToRemove;
		
		for(int e: arr){
			zerosToRemove = zeros & e;
			onesToRemove  = ones  & e;
			twosToRemove  = twos  & e;
			zeros  = (zeros & ~zerosToRemove) | twosToRemove;
			
			ones   = (ones  & ~onesToRemove)  | zerosToRemove;
			
			twos   = (twos  & ~twosToRemove)  | onesToRemove;

		}
		
		return ones;
		
	}
	
	
	public static int uniqueElement(int[] arr, int N){
		
		int[] mods = new int[N]; int[] toRemove =  new int[N];
		mods[0] = ~0;
		
		for(int e: arr){
			
			for(int m = 0; m < N; ++m)
				toRemove[m] = mods[m] & e;
			
			for(int m = 0; m < N; ++m)
				mods[m] = (mods[m] & ~toRemove[m]) | toRemove[(m+N-1)%N];

		}
		
		return mods[1];
		
	}

	
	public static <E> E getMajorityElement(List<E> list){
		
		E majority = null;
		int count = 0;
		for(E curr: list)
			if(count == 0){
				count = 1; majority = curr;
			}
			else if(curr.equals(majority)) ++count;
			else --count;
	
		count = 0;
		
		for(E curr: list)
			if(curr.equals(majority))
				++count;
		
		return count > list.size()/2? majority : null;
	}
	
	public static long change(final int[] denoms, final int target){
		
		final long[][] dp = new long[target+1][denoms.length];
		
		Arrays.fill(dp[0], 1);
				
		for(int t = 1; t <= target; ++t){
			long last = 0;
			for(int d = 0; d < denoms.length; ++d){
				long denom = denoms[d];
				last = (dp[t][d] =  last + (  (denom <= t)? dp[(int) (t-denom)][d] : 0 ));
				
			}
		}
		return dp[target][denoms.length-1];
	}
	
	private static int change(final int[] denoms, final int idx, int target){

		if(target == 0) return 1;
		if(idx == denoms.length) return 0;
		int toReturn  = 0;
				
		while(target >= 0){
			toReturn += change(denoms, idx+1, target);
			target  -= denoms[idx];
		}
		return toReturn;
	}
	
	
	public static int change2(final int[] denoms, final int target){
		
		return change(denoms, 0, target);
		

	}
	
	static List<Integer> getDuplicates(int[] longFile){
		BitSet bs = new BitSet();
		Set<Integer> dups = new HashSet<Integer>();
		for(int val: longFile)
			if(bs.get(val)) dups.add(val);
			else  bs.set(val);
		
		return new ArrayList<Integer>(dups);

	}

	private static void permutationsNoDups(Deque<Character> free, Deque<Character> soFar, List<String> result){
		if(free.isEmpty())
			result.add(charDequeToString(soFar));
		
		Character last = '\0';
		
		for(int c = 0; c < free.size(); ++c){
			Character curr = free.removeFirst();
			if(curr.equals(last)){
				free.add(curr);
				continue;
			}
			soFar.add(curr);
			permutationsNoDups(free, soFar, result);
			free.add(soFar.removeLast());
			last = curr;
		}
	}
	
	
	
	
	
	
	public static int changeBits(int original, int m, int start, int end){
		int mask = ~0 - (1 << (start + 1)) + (1 << (end));
		
		return (original & mask) | (m << start);
		
		
	}
	
	public static String toBinary(double num){
		
		int intNum = (int) num;
		
		double decimal = num - intNum;
		
		StringBuilder binary  = new StringBuilder();
		
		while(intNum > 0){
			binary.append(intNum & 1);
			intNum >>= 1;
		}
		
		binary.reverse(); binary.append(".");
		
		while(decimal != 0){
			decimal *= 2;
			if(decimal >= 1){
				binary.append(1); decimal -= 1;
			}
			else binary.append(0);
		}
		
	
		return binary.toString();
	}
	
	public static double fromBinary(String num){
		
		int curr = 0;
		final int len = num.length();
		int intValue = 0;
		
		while(curr < len){
			char ch = num.charAt(curr++); 
			if(ch == '.') break;
			intValue  += intValue +  Character.getNumericValue(ch);
			
		}
		
		double place = 0.5;
		double result = intValue;
		
		while(curr < len){
			result += place *(num.charAt(curr++) == '1'? 1 : 0);
			place /= 2;
		
		}

		return result;
		
	}
	
	private static int binarySearch(int[] array, int key, int lo, int hi){

		if(hi < lo) return -1;
		
		int midIdx = (hi + lo) / 2;
		int mid = array[midIdx];
		
		if(mid == key) return midIdx;
		
		return key < mid? binarySearch(array, key, lo, midIdx-1) :  binarySearch(array, key, midIdx+1, hi);
		
	}
	
	static long nthPrime(int n){
		
		if(n == 1) return 2;      if(n == 2) return 3;       if(n == 3) return 5;
		
		final int lim =(int)(n*(Math.log(n) + Math.log(Math.log(n)))) + 3;
		
		boolean[] sieve = new boolean[(lim-3)/2 ];
		
		for(int i = 3; i <= Math.sqrt(lim); i += 2)
			for(int y = i*3; y < lim; y += 2*i){
				//System.out.println(y + "  " + ((y-3)/2) + "  " + i);
				sieve[(y-3)/2] = true;
			}
			
		int primesFound = 1; 
		
		for(int i = 0; i < (lim-3)/2 ; ++i){
			if(!sieve[i]){
				++primesFound;
			}
			if(primesFound == n)
				return i*2+3;
		}
		
		System.err.println("Error, nth prime not found\n");
		return -1;
	}
	
	
	public static int binarySearch(int[] array, int key){
		if(array == null || array.length == 0) return -2;
		
		return binarySearch(array, key, 0 ,array.length -1 );
	
	}
	
	private static int searchSorted(int[] array, int key, int lo, int hi){

		if(hi < lo) return -1;
		int midIdx = (hi + lo) / 2;
		int mid = array[midIdx];
		
		if(mid == key) return midIdx;
		
		if(array[lo] <= mid){ //If left sorted
			if(key < mid && key >= array[lo])
				return binarySearch(array, key, lo, midIdx-1);
			else
				return searchSorted(array, key, midIdx+1, hi);
		
			
		}
		
		else//Then right side is sorted 
			if(key > mid && key <= array[hi])
				return binarySearch(array, key, midIdx+1, hi);
			else 
				return searchSorted(array, key, lo, midIdx-1);

	}
	
	
	public static int searchSorted(int[] array, int key){
		return searchSorted(array, key, 0 ,array.length -1 );
	
	}
	
	private static Integer[] arrPlusOne(Integer[] arr, int extra){
		Integer[] toReturn = Arrays.copyOf(arr, arr.length + 1);
		toReturn[arr.length] = extra;
		return toReturn;
		
	}
	public static void specialChars(char[] chars){
		
		int left = 0, right = chars.length - 1;
		while(right > left){
			System.out.println(left+ "  "+ right);

			if(!isAlpha(chars[right])) --right;
			else if(!isAlpha(chars[left])) ++left;
			else{
				System.out.println(left+ "  "+ right);

				char temp = chars[right];
				chars[right--] = chars[left];
				chars[left++]  = temp;
			}
				
		}
		
	}
	
	
	static boolean unique(String str){
		
		boolean[] charSet = new boolean[256];
		
		for(byte b: str.getBytes())
			if(charSet[b]) return false;
			else charSet[b] = true;
		
		
		
		return true;
		
	}
	
	static void makeUnique(char[] cstr){
		
		boolean[] arr = new boolean[256];
		
		arr[cstr[0]] = true; 
		int write = 1, read = 1;
		
		while(cstr[read] != '\u0000') 
			if(arr[cstr[read]])
				++read;
			else
				arr[cstr[write++] = cstr[read++]]  = true;
			

		cstr[write] = '0';
		
		
	}
	
static int nthSum(int target, int[] a, int[] b){
		
		/*Each element a[i] needs to eventually be matched with each b[i],
		First match a[i] with b[0], then b[1] and so on.  Iterator i keeps track of
		which b[j] is next (currIt = j). Priority queue helps us determine which
		a[i], b[j] pair is next*/
		
		final class SumPtr{
			final int index;;
			int sum,  currIt;
			public SumPtr(int index){
				this.index = index;
				currIt     = 0;
			}
			
			public SumPtr(int index, int sum){
				this(index);
				this.sum   = sum;
			}
			
			public boolean hasNext(){
				return currIt < b.length;
			}
			public SumPtr next(){
				sum =  a[index] + b[currIt++];
				return this;
			}
			@Override
			public String toString(){
				return String.format("Index: %d, sum: %d, currIt: %d ", index, sum, currIt);
				
			}
		}
		
		Arrays.parallelSort(a); Arrays.parallelSort(b);
			
		PriorityQueue<SumPtr> pq = new PriorityQueue<SumPtr>( (SumPtr A, SumPtr B ) -> Integer.compare(A.sum, B.sum) );
		pq.addAll(Stream.iterate(0, i -> i+1).limit(a.length).map(i -> new SumPtr(i, a[i] + b[0])).collect(Collectors.toList()));
				
		int currSum = Integer.MAX_VALUE, sumsProcessed = 0;
		
		while(sumsProcessed < target){
			SumPtr next = pq.poll();
			if(next.sum != currSum){
				++sumsProcessed;
				currSum = next.sum;
			}
			if(next.hasNext()) //Increment iterator for a[i] and put it back in pq
				pq.add(next.next());
			
		}
		
		return currSum;
	}
	
	public static String sortAWithB(String A, String B){
		
	Map<Character, Integer> map = new TreeMap<Character, Integer>();
		
	for(char c: A.toCharArray()){
		Integer curr = map.putIfAbsent(c, 1);
		if(curr != null) map.put(c, curr+1);
	}
	
	StringBuilder sb = new StringBuilder();
	
	for(char c: B.toCharArray()){
		for(int i = 0; i < map.getOrDefault(c, 0); ++i)
			sb.append(c);
		map.remove(c);
	}
	
	for(char c: A.toCharArray()){
		if(map.containsKey(c))
			sb.append(c);
	}
	
	
	return sb.toString();
		
	}
	
	private static class CustomComp implements Comparator<String>{
		private final Map<Character, Integer> ordering; 
		
		CustomComp(Map<Character, Integer> ordering) {
			this.ordering = ordering;
		}
		@Override
		public int compare(String o1, String o2) {
			int c = 0;
			
			while(c < o1.length() && c < o2.length())
			{
				if(o1.charAt(c) != o2.charAt(c))
					return ordering.get(o2.charAt(c)) - ordering.get(o1.charAt(c));
				else 
					++c;
			}
			
			return o2.length() - o1.length();
			
		}
	}
	
	
	
	public static  boolean inCustomOrder(char[] _order, String[] words){
		Map<Character, Integer> order = new HashMap<>();
		for(int i = 0; i < _order.length; ++i)
			order.put(_order[i], i);
		
		CustomComp comp  = new CustomComp(order);
		
		for(int i = 0; i < words.length-1; ++i)
			if(comp.compare(words[i], words[i+1]) < 0) return false;
		
		return true;
		
		
	}
	
	

	
	public static  boolean isCombination(String A, String B, String C){
		if(A.length() + B.length() != C.length()) return false;
		int a = 0, b = 0, c = 0;
		char currA, currB, currC;
		while(a != A.length() && b != B.length()){
			currA = A.charAt(a); currB = B.charAt(b); currC = C.charAt(c);
			if(currA == currC)
				if(currB != currC){
					++a; ++c;
				}
				else{
					int offset = 0;
					while(true){
						++offset;
						if(A.charAt(a) != C.charAt(c)) return false;
						++a; ++b; ++c;
						if(a == A.length() || b == B.length() || A.charAt(a) != B.charAt(b))
							return isCombination(A.substring(a), B.substring(b-offset), C.substring(c)) || 
								   isCombination(A.substring(a-offset), B.substring(b), C.substring(c));

						if(c == C.length()) return false;
					}
				}
			
			else if(currB == currC){
				++b;  ++c;
			}
			else 
				return false;
				
		}
		
		return (A.substring(a) + B.substring(b)).equals(C.substring(c));
	
		
	}
	
	public static boolean uniqueBit(String str){
		
		int check = 0;
		
		for(int c = 0; c < str.length(); ++c){
			byte val = (byte) (str.charAt(c) - 'a');
			if((check & (1 << val)) > 0)
				return false;
			
			check |= (1 << val);
			
		}
			
		return true;
		
	}
	

	
	
}
