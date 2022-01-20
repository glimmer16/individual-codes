import java.util.Scanner;
public class java102702 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        Scanner in = new Scanner(System.in);
		        int n = in.nextInt();
		        int m = in.nextInt();
		        int[] a = new int[m];
		        for(int i=0;i<m;i++) {
		            a[i] = in.nextInt();
		        }
		        int[] dp = new int[32800];
		        dp[0] = 1;
		        for(int i = 0;i<a.length;i++)
		        {
		            for(int j = a[i];j<=n;j++) {
		                dp[j]=dp[j]+dp[j-a[i]];
		            }
		        }
		        System.out.println(dp[n]);
		    }
		 
		}