
public class Matrix implements Cloneable {
	
	final int nrows, ncols;
	final int[][] data;
	
	public Matrix(int rows, int cols){
		this.nrows = rows; this.ncols = cols;
		this.data = new int[nrows][ncols];
	}
	
	public Matrix(int[][] _data){
		this.data = _data; 
		this.nrows = _data.length; this.ncols = _data[0].length;
		
	}
	
	@Override
	public String toString(){
		StringBuilder b = new StringBuilder();
		for(int[] row: data){
			for(int c: row)
				b.append(String.format("%2d ", c));
			b.append("\n");
			
			
		}
		return b.toString();
		
	}
	
	public void rotate(){
		
		if(nrows != ncols) throw new UnsupportedOperationException();
		final int N  = nrows; int temp = 0;
		
		
		for(int r = 0; r < N/2; ++r){
			for(int c = r; c < N - r - 1; ++c){
				temp  		       = data[r][c];
				
				data[r][c]         = data[N-1-c][r]; //left --> top
				data[N-1-c][r]     = data[N-1-r][N-1-c];
				data[N-1-r][N-1-c] = data[c][N-1-r]; 
				data[c][N-1-r]     = temp;
				
			
			}
			
			
		}
		

		
	}
	
	protected Object clone() throws CloneNotSupportedException{
		
		int[][] dataCopy =  new int[nrows][ncols];
		
		for(int x = 0; x < nrows; ++x)
			System.arraycopy(data[x], 0, dataCopy[x], 0, ncols);
		
		return new Matrix(dataCopy);
		}
	
	
	
	
}
