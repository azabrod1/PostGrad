import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Sols {

	public static class Perfects implements Callable<List<Integer>>{
		List<Integer> toReturn;
		int[] array;
		
		public Perfects(int[] array){
			this.array = array;
			this.toReturn  =  new LinkedList<Integer>();
		}
		
		public  List<Integer> call() throws Exception{
			List<Integer> toReturn = new LinkedList<Integer>();

			for(int num: array){
				int sum = -num;
				for(int i = 1; i*i <= num; ++i)
					if(num%i == 0){ 
						sum += i;

						if (i * i != num)
							sum += (num/i);


					}

				if(sum == num) toReturn.add(num);


			}

			return toReturn;
		}

	
	}

	

	public static void create(){
		long start = System.currentTimeMillis();
		//ExecutorService executorService = Executors.newFixedThreadPool(2);
		int N = 1000000;
		int[] big= new int[N];
		for(int i = 0; i < N; ++i)
			big[i] = 1+i;
		
		int[] big2= new int[N];
		for(int i = 0; i < N; ++i)
			big2[i] = N+i;
		
		Perfects p2 = new Perfects(big);

		Perfects p1 = new Perfects(big2);
		
		List<Integer> l2 = null, l1= null;
		
		try {
			l1 = p1.call(); l2 = p2.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l1.addAll(l2);
		System.out.println(l1);
		System.out.println(System.currentTimeMillis() - start);

		//executorService.shutdown();

		
	}
	
	public static class PlayerCallable implements Callable {
        String name;
        int call_sequence = 0;
        static String[] SelectionTable = {
            "Rock", "Scissors", "Paper"
        };
        PlayerCallable(String given_name) {
            name = given_name;
        }
        public String call() throws InterruptedException {
            int delay = (int) (2000 * Math.random());
            call_sequence++;
            System.out.format("%s pauses %d microseconds on the %d-th invocation.\n", name, delay, call_sequence);
            Thread.sleep(delay);
            String choice = SelectionTable[three_sided_coin()];
            //System.out.format("%s selects %s.\n", name, choice);
            return choice;
        }
    }
	
	public static class HumanPlayerCallable extends PlayerCallable {
        Scanner sc = new Scanner(System.in);
        
        HumanPlayerCallable(String given_name) {
            super(given_name);
        }
        public String call() throws InterruptedException {
        	long to = System.currentTimeMillis();
        	System.out.println("Select rock, paper or scissors");
            //int delay = (int) (2000 * Math.random());
            call_sequence++;
            String choice = SelectionTable[sc.nextByte()];
            System.out.format("%s pauses %d microseconds on the %d-th invocation.\n", name, System.currentTimeMillis() - to, call_sequence);

            System.out.format("%s selects %s.\n", name, choice);
            return choice;
        }
    }
	
	
    public static void main(String[] args) throws Exception {
    	/*
        ExecutorService pool = Executors.newFixedThreadPool(2);
        PlayerCallable player1 = new PlayerCallable("player1");
        PlayerCallable player2 = new HumanPlayerCallable("Me");
        for (int i = 10; i > 0; i--) {
            Future future1 = pool.submit(player1);
            Future future2 = pool.submit(player2);
            
            System.out.println(payoff((String) future2.get(),
                                      (String) future1.get()));
        }
        pool.shutdown();
        */
    	
    	char[] cstr = "happy ness abcoo\u0000".toCharArray();
    	Interview3.makeUnique(cstr);
    	System.out.println(cstr);
    	
    	
    	
    	
    }
    public void run() {
        FutureTask player1 = new FutureTask(new ThisCallable());
    }
    public static int three_sided_coin() {
    return (int)(Math.random() * 3);
    }
    public static String payoff (String first_hand, String second_hand) {
    if (first_hand.equals(second_hand)) {
        return String.format("'%s' from both hands is a tie.", 
              first_hand);
    }
        if ((first_hand.equals("Rock") & second_hand.equals("Scissors")) ||
            (first_hand.equals("Scissors") & second_hand.equals("Paper")) ||
            (first_hand.equals("Paper") & second_hand.equals("Rock"))) {
            return String.format("One's '%s' beats Two's '%s'.", first_hand, second_hand);
        }
        return String.format("Two's '%s' beats One's '%s'.", second_hand, first_hand);
    }
    public class ThisCallable implements Callable {
        public Integer call() throws java.io.IOException {
           return 1;
        }
    }
	
	
	
	
	
	
	
	
	
	
}
