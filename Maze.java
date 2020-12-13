import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {

	private final boolean[][] data;
	private final int N;
	
	public Maze(int N){
		this.data = new boolean[N][N];
		this.N    = N;
	}
	
	public static Maze randomMaze(final double P, final int N){
		Maze maze  = new Maze(N);
		boolean[][] data = maze.data;
		ThreadLocalRandom rand = ThreadLocalRandom.current();
		
		for(int r = 0; r < N; ++r)
			for(int c = 0; c < N; ++c)
				maze.data[r][c] = rand.nextDouble() < P? true : false;
		
		
		data[0][0] = (data[N-1][N-1] = false);
		return maze;
		
	}
	
		
		
/*
	private String printPath(b){
		
	}
	*/
	
	
	public String solve(){
		
		boolean[][] visited = new boolean[N][N];
		
		int r, c;
		
		Deque<Integer> stackR = new ArrayDeque<Integer>();
		Deque<Integer> stackC = new ArrayDeque<Integer>();
	
		
		stackR.push(0); stackC.push(0);
		
		while(!stackR.isEmpty()){
			
			r = stackR.pop(); c = stackC.pop();
			
			if(visited[r][c]) continue;
			
			if(r == N-1 && c == N-1) 
				return "Path exists";
				
			visited[r][c] = true;
			
			
			if(c + 1 < N && !visited[r][c+1] && !data[r][c+1]){
				stackR.push(r);
				stackC.push(c+1);
			}
			
			if(r+1 < N && !visited[r+1][c] && !data[r+1][c]){
				stackR.push(r+1);
				stackC.push(c);
			}
			
			
			if( c - 1 >= 0 && !visited[r][c-1] && !data[r][c-1]){
				stackR.push(r);
				stackC.push(c-1);
			}
			
			if(r-1 >= 0 &&  !visited[r-1][c] && !data[r-1][c]){
				stackR.push(r-1);
				stackC.push(c);
			}
		
			
		}
		
		return "path not found\n";
		
	}

	
	public void set(int r, int c){
		data[r][c] = true;
	}
	
	public void unSet(int r, int c){
		data[r][c] = false;
	}
	
	public boolean get(int r, int c){
		return data[r][c];
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		for(boolean[] row: data){
			
			for(boolean c: row)
				sb.append(String.format("%s ", c? "#": " "));
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
	
	
	
	
	
}
