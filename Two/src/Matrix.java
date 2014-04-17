public class Matrix{
	public int[][] numberlings;
	private int height,width;
	private int i,j,k;
	public Matrix(int[][] m,int h,int w){
		numberlings = m;
		height = h;
		width = w;
	}
	public Matrix multiplyOn(Matrix m){
		int mWidth = m.getWidth();
		Matrix matrix = new Matrix(new int[height][mWidth],height,width);
		for(i=0;i<height;i++)
			for(k=0;k<width;k++)
				for(j=0;j<mWidth;j++)
					matrix.numberlings[i][j]+=numberlings[i][k]*m.numberlings[k][j];
		i=0;j=0;k=0;
		if(matrix.getHeight()==matrix.getWidth())
			return (SquareMatrix)matrix;
		else
			return matrix;
	}
	public Matrix multiplyOn(int n){
		Matrix matrix = new Matrix(numberlings,height,width);
		for(i=0;i<height;i++)
			for(j=0;j<width;j++)
				matrix.numberlings[i][j]*=n;
		i=0;j=0;
		return matrix;
	}
	public Matrix add(Matrix m){
		Matrix matrix = new Matrix(numberlings,height,width);
		for(i=0;i<height;i++)
			for(j=0;j<width;j++)
				matrix.numberlings[i][j]+=m.numberlings[i][j];
		i=0;j=0;
		return matrix;		
	}
	public void setHeight(int h){
		height = h;
	}
	public void setWidth(int w){
		width = w;
	}
	public int getHeight(){
		return height;
	}
	public int getWidth(){
		return width;
	}
}