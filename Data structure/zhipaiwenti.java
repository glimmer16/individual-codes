import java.util.Scanner;

public class zhipaiwenti {
	public static void appoint(double[][] m){
		int N = m.length;

		for(int i = 0;i < N;i ++){
			double min = Double.MAX_VALUE;
			for(int j = 0;j < N;j ++){
				if(m[i][j] < min)
					min = m[i][j];
			}
			for(int j = 0;j < N;j ++)
				m[i][j] -= min;
		}

		for(int j = 0;j < N;j ++){
			double min = Double.MAX_VALUE;
			for(int i = 0;i < N;i ++){
				if(m[i][j] < min)
					min = m[i][j];
			}
			if(min == 0)
				continue;
			for(int i = 0;i < N;i ++)
				m[i][j] -=min;
		}
		

		while(true){
			boolean zeroExist = true;
			while(zeroExist){
				zeroExist = false;
				if(rowAppoint(m))
					zeroExist = true;
				if(colAppoint(m))
					zeroExist = true;

			}

			if(isOptimal(m))
				break;
			
			updataM(m);
			

			for(int i = 0;i < N;i ++){
				for(int j = 0;j < N;j ++)
					if(m[i][j]<0)
						m[i][j] = 0;
			}

		}
	}
	
	public static void updataM(double[][] m){
		int N = m.length;

		boolean[] rowIsChecked = new boolean[N];
		boolean[] colIsChecked = new boolean[N];

		for(int i = 0;i < N;i ++){
			for(int j = 0;j < N;j ++){
				if(m[i][j] == -1){
					rowIsChecked[i] = false;
					break;
				}else{
					rowIsChecked[i] = true;
				}
			}
		}
		
		boolean isChecked = true;
		
		while(isChecked){
			isChecked = false;

			for(int i = 0;i < N;i ++){
				if(rowIsChecked[i]){
					for(int j = 0;j < N;j ++){
						if(m[i][j]==-2 && !colIsChecked[j]){
							colIsChecked[j] = true;
							isChecked = true;
						}
					}
				}
			}

			for(int j = 0;j < N;j ++){
				if(colIsChecked[j]){
					for(int i = 0;i < N;i ++){
						if(m[i][j] == -1 && !rowIsChecked[i]){
							rowIsChecked[i] = true;
							isChecked = true;
						}
					}
				}
			}
		}

		double min = Double.MAX_VALUE;
		for(int i = 0;i < N;i ++){
			if(rowIsChecked[i]){
				for(int j = 0;j < N;j ++){
					if(!colIsChecked[j]){
						if(m[i][j] < min)
							min = m[i][j];
					}
				}			
			}
		}

		for(int i=0;i < N;i ++){
			if(rowIsChecked[i]){
				for(int j = 0;j < N;j ++){
					if(m[i][j] > 0)
						m[i][j] -= min;
				}
			}
		}

		for(int j=0;j < N;j ++){
			if(colIsChecked[j]){
				for(int i = 0;i < N;i ++){
					if(m[i][j] > 0)
						m[i][j] += min;
				}
			}
		}		
				
	}
	
	public static boolean isOptimal(double[][] m){
		int count = 0;
		for(int i = 0;i < m.length;i ++){
			for(int j = 0;j < m.length;j ++)
				if(m[i][j] == -1)
					count ++;
		}
		return count==m.length;
	}
	
	public static boolean rowAppoint(double[][] m){
		boolean zeroExist = false; 
		int N = m.length;

		for(int i = 0;i < N;i ++){
			int zeroCount = 0;
			int colIndex = -1;
			for(int j = 0;j < N;j ++){
				if(m[i][j]==0){
					zeroCount ++;
					colIndex = j;
					zeroExist = true;
				}
			}

			if(zeroCount == 1){
				m[i][colIndex] = -1;
				for(int k = 0;k < N;k ++){
					if(k == i)
						continue;
					else if(m[k][colIndex] == 0)
						m[k][colIndex] = -2;
				}
			}else if(zeroCount == 2){
				if(Math.random()>0.95){
					m[i][colIndex] = -1;
					for(int k = 0;k < N;k ++){
						if(k == i)
							continue;
						else if(m[k][colIndex] == 0)
							m[k][colIndex] = -2;
					}
					for(int j = 0;j < N;j ++){
						if(j == colIndex)
							continue;
						else if(m[i][j] == 0)
							m[i][j] = -2;
					}
				}
			}
		}
		return zeroExist;
	}
	public static boolean colAppoint(double[][] m){
		boolean zeroExist = false; 
		int N = m.length;
		for(int j = 0;j < N;j ++){
			int zeroCount = 0;
			int rowIndex = -1;
			for(int i = 0;i < N;i ++){
				if(m[i][j]==0){
					zeroCount ++;
					rowIndex = i;
					zeroExist = true;
				}
			}
			if(zeroCount == 1){
				m[rowIndex][j] = -1;
				for(int k = 0;k < N;k ++){
					if(k == j)
						continue;
					else if(m[rowIndex][k] == 0)
						m[rowIndex][k] = -2;
				}
			}
		}
		return zeroExist;
	}
	
	public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		int n = sc.nextInt();
		int m=sc.nextInt();
		int t=0;
		double [][]num = new double[n][n];
		double [][]M = new double[n][n];
		double [][]a=new double[m+1][3];

		for(int i=0;i<m;i++) {
			a[i][0]=sc.nextInt();
			a[i][1]=sc.nextInt();
			a[i][2]=sc.nextInt();
		}
		int k=0;
		for (int i = 0; i <n; i++) {
			for (int j = 0; j <n; j++) {
				if(i==a[k][0]-1&&j==a[k][1]-1) {
					num[i][j]=a[k][2];
					k++;
				}
				else {
					num[i][j]=10000;
				}
				M[i][j]=num[i][j];
			}
		}
		
		appoint(num);
		for(int i = 0;i < num.length;i ++){
			for(int j = 0;j < num.length;j ++)
				if(num[i][j] == -1)
				t+=	M[i][j];
		}		
		System.out.print(t);
	}
	
}