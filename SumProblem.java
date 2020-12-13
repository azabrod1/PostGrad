
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SumProblem {

	
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
		
		Arrays.sort(a); Arrays.sort(b);
			
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
}
	
	
	
