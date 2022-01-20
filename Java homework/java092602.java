import java.util.Scanner;
public class java092602 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int U=in.nextInt();
		int V=in.nextInt();
		int N=in.nextInt();
		int[][] dp=new int[U+1][V+1];
		int[] num=new int[N+1];
		int[] vol=new int[N+1];
		int[] wei=new int[N+1];
		int[] pri=new int[N+1];
		for (int i = 1; i <= N; i++)
		{
			wei[i]=in.nextInt();
			vol[i]=in.nextInt();
			pri[i]=in.nextInt();
			num[i]=in.nextInt();
		}	
		for (int i = 1; i <=N; i++)
		{
			int numb = Math.min(num[i], U / wei[i]);
		    for (int k = 1; numb > 0; k*=2)
		    {
		        if (k > numb) k = numb;
		        numb -= k;
		        for (int j =U; j >= wei[i] * k; --j)
		        	for(int l=V;l>=vol[i]*k;l--)
		                dp[j][l] = Math.max(dp[j][l], dp[j - wei[i] * k][l - vol[i] * k] + pri[i] * k);
		    }
		}
		System.out.println(dp[U][V]);
	}
}
