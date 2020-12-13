package Utility;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utility {
	
	public static Clock getClock(){
		return new Clock();
	}

	public static Integer[] randomArray(int size){
		Integer[] ret = new Integer[size];
		
		for(int i = 0; i < size; ++i)
			ret[i] = i;
		
		shuffle(ret);
		
		return ret;
		
	}
	//each element from low to hi-1 has p chance of actually being there
	public static Integer[] randomListWithProbP(double p, int low, int hi){
		ThreadLocalRandom r = ThreadLocalRandom.current();
		List<Integer> list = new ArrayList<>();
		
		for(int i = low; i < hi; ++i)
			if(r.nextDouble() <= p)
				list.add(i);
		
		Integer[] ret = list.toArray(new Integer[list.size()]);
		shuffle(ret);

		return ret;
		
	}
	
	public static int[] fromInteger(Integer[] src){
		int[] ret = new int[src.length];
		
		for(int i = 0; i < src.length; ++i)
			ret[i] = src[i];
		
		return ret;
	}
	
	
	
	public static <E> void shuffle(E[] array){

		Random r = new Random();

		for(int i = array.length - 1; i > 0; --i){
			E temp  = array[i]; int idx = r.nextInt(i);
			array[i] = array[idx]; array[idx] = temp;

		}
	}

}
