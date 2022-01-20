import java.util.Scanner;

public class 两个机器处理零件 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int []a=new int [n];
		int sum=0;
		for(int i=0;i<n;i++)
		{
			a[i]=in.nextInt();
			sum+=a[i];
		}
		int [][]b=new int [n][sum/2+1];
		for(int j=0;j<a[0];j++)
		{
			b[0][j]=0;
		}
		for(int j=a[0];j<=sum/2;j++)
		{
			b[0][j]=a[0];
		}
		for(int i=1;i<n;i++)
		{
			for(int j=0;j<=sum/2;j++)
			{
				if(j<a[i])
				{
					b[i][j]=b[i-1][j];
				}
				else
					b[i][j]=Math.max(b[i-1][j], b[i-1][j-a[i]]+a[i]);
			}
		}
		System.out.println(sum-b[n-1][sum/2]);
	}

}
