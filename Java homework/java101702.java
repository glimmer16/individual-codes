import java.util.Scanner;
public class java101702 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int sum=0;
		int[] m=new int[n];
		for(int i=0;i<n;i++)
		{
			m[i]=in.nextInt();
			sum+=m[i];
		}
		int [][] dp=new int[n+1][sum/2+1];
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=sum/2;j++)
			{
				if(j>=m[i-1])
				{
					dp[i][j]=Math.max(dp[i-1][j], dp[i-1][j-m[i-1]]+m[i-1]);
				}
				else
				{
					dp[i][j]=dp[i-1][j];
				}
			}
		}
		System.out.println(sum-dp[n][sum/2]);
	}

}
