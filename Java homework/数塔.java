import java.util.Scanner;

public class ???? {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int [][]a=new int [n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<=i;j++)
			a[i][j]=in.nextInt();
		}
		int [][]b=new int[n][n];
		for(int i=1;i<n;i++)
		{
			for(int j=0;j<=i;j++)
			{
				if(j==0)
					b[i][j]=b[i-1][j]+a[i-1][j];
				else if(j==i)
					b[i][j]=b[i-1][j-1]+a[i-1][j-1];
				else
					b[i][j]=Math.max(b[i-1][j-1]+a[i-1][j-1], b[i-1][j]+a[i-1][j]);
			}
		}
		int ans=0;
		for(int i=0;i<n-1;i++)
		{
			if(a[n-1][i]+b[n-1][i]>ans)
				ans=a[n-1][i]+b[n-1][i];
		}
		System.out.println(ans);
	}
}