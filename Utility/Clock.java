package Utility;

public class Clock{
	long last;
	public double start(){
		double ret = System.currentTimeMillis() - last;
		last = System.currentTimeMillis();
		return ret;
	}
	public long lap(){
		 return System.currentTimeMillis() - last;
	}
	
	public long end(){
		return System.currentTimeMillis() - last;
		
	}
	
	public void printTime(){
		this.printTime("event");
	}
	public void printTime(String msg){
		System.out.println("Time Elapsed for " + msg + ": " + (System.currentTimeMillis() - last));
	}
	
	public String toString(){
		return "Time Elapsed: " + (System.currentTimeMillis() - last);
	}
	
}