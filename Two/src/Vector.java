class Vector extends Matrix{
	private int dimension;
	private int i=0,sum=0;
	public Vector(int[][] v, int d){
		super(v,1,d);
		dimension = d;
	}
	public int scalar(Vector v){
		for(i=0;i<dimension;i++)
			sum+=numberlings[0][i]*v.numberlings[0][i];
		i=0;
		return sum;
	}
	public Vector vectorMultiplication(Vector v){
		Vector vector = new Vector(new int[1][dimension],dimension);
		vector.numberlings[0][1]=numberlings[0][2]*v.numberlings[0][3]-numberlings[0][3]*v.numberlings[0][2];
		vector.numberlings[0][2]=-numberlings[0][1]*v.numberlings[0][3]+numberlings[0][3]*v.numberlings[0][1];
		vector.numberlings[0][3]=numberlings[0][1]*v.numberlings[0][2]-numberlings[0][2]*v.numberlings[0][1];
		return vector;
	}
}