import java.util.Scanner;

public class gongzuofenpei {
	public static int sum = 0,n;
	public static int [] [] num ;
	public static boolean []  bool;
	public static int min = Integer.MAX_VALUE;
	public static int m;

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		n = sc.nextInt();
		m=sc.nextInt();
		num = new int [n+1][n+1];
		bool = new boolean [n+1];
		int[][]a=new int[m+1][3];

		for(int i=0;i<m;i++) {
			a[i][0]=sc.nextInt();
			a[i][1]=sc.nextInt();
			a[i][2]=sc.nextInt();
		}
		int k=0;
		for (int i = 1; i <=n; i++) {
			for (int j = 1; j <=n; j++) {
				if(i==a[k][0]&&j==a[k][1]) {
					num[i][j]=a[k][2];
					k++;
				}
				else {
					num[i][j]=10000;
				}
			}
		}
		f(1);
		System.out.println(min);
	}

	public static void f(int a){
		if(a==n+1){
			if(sum<min){
				min=sum;
			}
			return;
		}
		for (int i = 1; i <=n; i++) {
			if(!bool[i]){
				sum+=num[a][i];
				bool[i]=true;
				f(a+1);
				sum-=num[a][i];
				bool[i]=false;
			}
		}
	}
}	
