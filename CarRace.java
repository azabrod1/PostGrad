import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRace {
	private final int TEAMS;
	private final Car[] cars;
	private final double trackLength;
	private final static double TOO_CLOSE 	    = 10;
	private final static double HANDLING_FACTOR = 0.8;
	private final static int    FRAME_RATE      = 2;
	private final static int    DEFAULT_NITRO   = 1;
	private int time = 0; 
	private final List<RaceResult> results;
	
	public static List<RaceResult> doRace(final int numTeams, final double trackLength){
		CarRace race = new CarRace(numTeams, trackLength);
		race.race();
		return race.results;
		
	}
	

	
	private CarRace(int numTeams, double trackLength){
		this.TEAMS = numTeams;
		this.cars  = initStandardCarArray();
		this.trackLength = trackLength;
		this.results     = new ArrayList<>();
	}
	
	private Car[] initStandardCarArray(){
		Car[] arr = new Car[TEAMS];
		
		for(int c = 0; c < TEAMS; ++c)
			arr[c] = new Car(c, HANDLING_FACTOR, 2, DEFAULT_NITRO, (150 + 10*(c+1))*(1000/60), 2*(c+1), -200*c);
		
		return arr;
	}
	
	public static class RaceResult implements Comparable<RaceResult>{
		final int    team;
		final double time;
		final double finalPosition; //Used for tie breaks
		
		public RaceResult(Car car, double time){
			this.team 		   = car.team;
			this.finalPosition = car.position;
			this.time  		   = time;
			
		}
		
		@Override 
		public String toString(){
			return String.format("Team: %d, Time: %g", team, time);
		}
		
		public static String resultToString(List<RaceResult> list){
			StringBuilder ret = new StringBuilder();
			for(int t = 1; t <= list.size(); ++t)
				ret.append(String.format("%d. %s\n", t, list.get(t-1)));
			return ret.toString();
			
		}

		@Override
		public int compareTo(RaceResult that) {
			
			if(this.time != that.time)
				return Double.compare(this.time, that.time);
			else
				return Double.compare(that.finalPosition, this.finalPosition);
			
		}
		
	}
	
	private void finishLine(Car car){
		
		RaceResult result = new RaceResult(car, time);
		results.add(result);
		car.finished = true;
		
	}
	
	//return false if race finished
	private boolean process(){
		
		//calculate new position and speed
		for(Car car: cars){
			
			
			if(time == 44) System.out.println(car.position + "    " + car.team + "  " + car.speed);
			
			if(car.finished) continue;
			double newSpeed = car.speed +  car.acceleration * FRAME_RATE;
			car.position   += (car.speed + Math.min(newSpeed, car.maxSpeed)) * 0.5;
			if(car.position > trackLength)
				finishLine(car);
			car.speed      = newSpeed;
		}
		
		//Sort by position and apply position based speed effects
		Arrays.sort(cars, (x,y) -> x.position == y.position? 0 : x.position < y.position? -1 : 1);
		
		if(cars[0].finished) return false; //Finished
		
			//Edge cases
		if(cars[1].position - cars[0].position <= TOO_CLOSE && !cars[1].finished)
			cars[0].speed *= HANDLING_FACTOR;
		
		if(cars[TEAMS-1].position - cars[TEAMS-2].position <= TOO_CLOSE && !cars[TEAMS-2].finished)
			cars[TEAMS-1].speed *= HANDLING_FACTOR;
		
		for(int c = 1; c < TEAMS-1; ++c){
			if(cars[c].finished) break;
			if(cars[c].position - cars[c-1].position <= TOO_CLOSE || (cars[c+1].position - cars[c].position <= TOO_CLOSE && !cars[c+1].finished))
				cars[c].speed *= cars[c].hf;
		}
		if(cars[0].hasNitro()) //Last place car can apply nitro
			cars[0].nitroBoost();
		
		for(Car car: cars){
			if(car.finished) break;
			if(car.speed > car.maxSpeed) car.speed = car.maxSpeed;
		}
		
		return true;
		
	}
	
	private void race(){
		do{
			time += FRAME_RATE;
		}while(process());
		
		finishRace();
		
	}
	
	private void finishRace(){
		results.sort(null);
	}
	
	private class Car{
		public double speed, position;
		public final double acceleration, hf, nitroBoost, maxSpeed;
		public final int team;
		public int nitroTank;
		public boolean finished;
		
		public Car(int team, double hf, double nitroBoost, int initNitro, double maxSpeed, double acceleration, double startingPosition){
			
			this.team 	     = team;
			this.hf          = hf;
			this.nitroBoost  = nitroBoost;
			this.nitroTank   = initNitro;
			this.maxSpeed    = maxSpeed;
			this.acceleration= acceleration;
			this.speed       = 0;
			this.position    = startingPosition;
			this.finished    = false;
			
		}
		
		public boolean hasNitro(){
			return nitroTank != 0;
		}
		public void nitroBoost(){
			this.speed *= this.nitroBoost;
		}
		
		
	}
	
	
}
