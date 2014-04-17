import java.util.*;
import java.io.*;

class Main{
	private static int height, width, dimension;
	private static String str = "Usage: Main [-options] <sourse file>/n"
			+ "/tor Mnaxb = ((n1,n2,n3),(n4,n5,n6),(n7,n8,n9)) and Vnb = (n1,n2,n3) and [operation]/n----/n"
			+ "Example: V12 = (11,6) M12x3 = ((1,2),(7,8),(49,50)) V12 * M12x3/n"
			+ "/t M13x5 = ((9,10,8,11,7),(13,7,14,6,15),(4,15,3,16,2)) M25x1 = ((1),(1),(1),(1),(1)) M13x5 * M25x1/n"
			+ "/t M13x3 = ((-1,1,3),(2,2,6),(1,1,1)) M23x3 = ((1,0,0),(0,1,0),(0,0,1)) M13x3 + 2M23x3";
	public static void main(String[] args) throws IOException, WrongCallError, WrongMatrixOperation{
		Scanner sc=null;
		Matrix[] arr = new Matrix[3];
		int i=0, j=0;
		String[] arg;
		try {
		if(args.length < 2)
			throw new WrongCallError(str);
		if(args[0].equals("-d")){
			sc = new Scanner(new File("args[1]"));
			String line = sc.nextLine();
			line = ' '+line;
			j=0;
			for(i=0;i<line.length();i++) if(line.charAt(i)==' ') j++;
			arg = new String[j];
			j=-1;
			for(i=0;i<line.length();i++){
				if(line.charAt(i)==' '){
					arg[++j]="";
					while(line.charAt(i+1)==' ') i++;
				}
				else{
					arg[j]+=line.charAt(i);
				}
			}
			sc.close();
		}
		else{
			if(!(args[0].charAt(0)=='V'|args[0].charAt(0)=='M')){
				throw new WrongCallError(str);	
			}
			else{
				arg = args;
			}
		}
		arr[0] = processingStr(arg[0], arg[1], arg[2]);
		if(arg[3].equals("det")){
			
		}
		else{
			arr[1] = processingStr(arg[3], arg[4], arg[5]);
			arr[2] = getOperation(arr[0], arr[1], arg[0], arg[3], arg[6], arg[7], arg[8]);
		}
		
		}catch(IOException e){
			e.getMessage();
		}
		catch(WrongMatrixOperation e){
			e.getMessage();
		}
		catch(WrongCallError e){
			e.getMessage();
		}
		catch(Exception e){
			e.getMessage();
		}
		finally{
			if(sc!=null)
				sc.close();
		}
	}
	private static Matrix processingStr(String s1, String s2, String s3) throws WrongCallError{
		if(s1.charAt(0)=='V'){
			dimension = Integer.parseInt(s1.substring(1));
			Vector v = new Vector(getArray(s3, 1, dimension), dimension);
			return v;
		}
		else
			if(s1.charAt(1)=='M'){
				height = Integer.parseInt(s1.substring(1, s1.indexOf('x')));
				width = Integer.parseInt(s1.substring(s1.indexOf('x')+1));
					if(height == width){
						int size = height;
						SquareMatrix sm = new SquareMatrix(getArray(s3, size, size),size);
						return sm;
					}
					else{
						Matrix m = new Matrix(getArray(s3, height, width), height, width);
						return m;
					}
			}
			else throw new WrongCallError(str);
	}
	private static int[][] getArray(String s1, int h, int w){
		int i=0,j=0,k=0;
		int[][] array = new int[h][w];
		k=s1.lastIndexOf('(', s1.indexOf(','))-1;
		while(i<h){
			k+=2;
			while(j<w){
				if(j==w-1){
					array[i][j]=Integer.parseInt(s1.substring(k, s1.indexOf(')', k)));
					k=s1.indexOf(')', k)+1;
				}
				else{
					array[i][j]=Integer.parseInt(s1.substring(k, s1.indexOf(',', k)));
					k=s1.indexOf(',', k)+1;
				}
				j++;
			}
			j=0;
			i++;
		}
		return array;
	}
	private static Matrix getOperation(Matrix arr1, Matrix arr2, String s1, String s2, String s3, String s4, String s5) throws WrongMatrixOperation{
		int drot;
		Matrix m1 = null;
		Matrix m2 = null;
		if(s3.contains(s1)&s5.contains(s2)&!s3.contains(s2)&!s5.contains(s1)){
			if(s1.equals(s3)){
				if(s2.equals(s5)){}
				else{
					m2=arr2.multiplyOn(Integer.parseInt(s5.substring(0, s5.indexOf(s2))));
				}
			}
			else{
				if(s2.equals(s5)){
					m1=arr1.multiplyOn(Integer.parseInt(s3.substring(0, s3.indexOf(s1))));
				}
				else{
					m1=arr1.multiplyOn(Integer.parseInt(s3.substring(0, s3.indexOf(s1))));
					m2=arr2.multiplyOn(Integer.parseInt(s5.substring(0, s5.indexOf(s2))));
				}
			}
			switch(s4.charAt(0)){
			case '+' : {
				m1 = arr1.add(arr2);
				return m1;
			}
			case '-' : {
				m1 = arr1.add(arr2.multiplyOn(-1));
				return m1;
			}
			case '*' : {
				m1 = arr1.multiplyOn(arr2);
				return m1;
			}
			case '.' : {
				if(s1.charAt(0)=='V' & s2.charAt(0)=='V')
					drot = ((Vector)m1).scalar((Vector)m2);
				else throw new WrongMatrixOperation("Unequal dimension vectors. Please check sourse.");
				System.out.println("Result is number : "+drot);
				return null;
			}
			case 'x' : {
				if(s1.charAt(0)=='V' & s2.charAt(0)=='V' & Integer.parseInt(s1.substring(2))==3 & Integer.parseInt(s2.substring(2))==3){
					m1 = ((Vector)m1).vectorMultiplication((Vector)m2);
				}
				return m1;
			}
			default : throw new WrongMatrixOperation();
			}
		}
		else{
			if(s5.contains(s1)&s3.contains(s2)&!s5.contains(s2)&!s3.contains(s1)){
				if(s1.equals(s5)){
					if(s2.equals(s3)){}
					else{
						m1=arr2.multiplyOn(Integer.parseInt(s3.substring(0, s3.indexOf(s2))));
					}
				}
				else{
					if(s2.equals(s5)){
						m2=arr1.multiplyOn(Integer.parseInt(s5.substring(0, s5.indexOf(s1))));
					}
					else{
						m2=arr1.multiplyOn(Integer.parseInt(s5.substring(0, s5.indexOf(s1))));
						m1=arr2.multiplyOn(Integer.parseInt(s3.substring(0, s3.indexOf(s2))));
					}
				}
				switch(s4.charAt(0)){
				case '+' : {
					m1 = arr1.add(arr2);
					return m1;
				}
				case '-' : {
					m1 = arr1.add(arr2.multiplyOn(-1));
					return m1;
				}
				case '*' : {
					m1 = arr1.multiplyOn(arr2);
					return m1;
				}
				case '.' : {
					if(s1.charAt(0)=='V' & s2.charAt(0)=='V')
						drot = ((Vector)arr1).scalar((Vector)arr2);
					else throw new WrongMatrixOperation("Unequal dimension vectors. Please check sourse.");
					System.out.println("Result is number : "+drot);
					return null;
				}
				case 'x' : {
					if(s1.charAt(0)=='V' & s2.charAt(0)=='V' & Integer.parseInt(s1.substring(2))==3 & Integer.parseInt(s2.substring(2))==3){
						m1 = ((Vector)m1).vectorMultiplication((Vector)m2);
					}
					return m1;
				}
				default : throw new WrongMatrixOperation();
				}
			}
		}
		return m2 = null;
	}
}