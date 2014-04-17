public class SquareMatrix extends Matrix{
	private int size;
	public SquareMatrix(int[][] m, int s){
		super(m,s,s);
		size = s;
	}
	public int det(){
		return size;
	}
}