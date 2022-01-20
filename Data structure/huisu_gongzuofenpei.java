import java.util.Scanner;
 
public class huisu_gongzuofenpei {
	static int n;
	static int[][] p;
	static int[][] t;   //标记数组
	static int minP;
	static int tempMinP=0;
	public static void main(String[] args) {
		Scanner input= new Scanner(System.in);
		n=input.nextInt();
		int m=input.nextInt();
		p=new int[n][n];
		t=new int[n][n];
		int[][]a=new int[m+1][3];
		
		for(int i=0;i<m;i++) {
			a[i][0]=input.nextInt();
			a[i][1]=input.nextInt();
			a[i][2]=input.nextInt();
		}
		int k=0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i==a[k][0]&&j==a[k][1]) {
					p[i][j]=a[k][2];
					k++;
				}
				else {
					p[i][j]=10000;
				}
				minP+=p[i][j];
			}
		}
		getMinP(0);
		System.out.println(minP);
		input.close();
	}
	
	public static void getMinP(int k){
		if(k==n){
			if(tempMinP<minP){
				minP=tempMinP;
			}
			return;
		}
		for (int i = 0; i < n; i++) {
			if(isok(k, i)){
				tempMinP+=p[k][i];
				t[k][i]=1;
				getMinP(k+1);
				tempMinP-=p[k][i];
				t[k][i]=0;
			}
			
		}
	}
	
	public static boolean isok(int k,int i){
		for (int j = 0; j < n; j++) {
			if(t[k][j]==1){
				return false;
			}
		}
		for (int j = 0; j < n; j++) {
			if(t[j][i]==1){
				return false;
			}
		}
		return true;
	}
}