import java.util.Scanner;
public class java103101 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[] prices = new int[N];
		int before;
		int after;
		int max = 0;
		for(int i=0;i<N;i++) {
			prices[i] = in.nextInt();
		}
		for(int i = 0;i<N;i++) {
			before = 0;
			after = 0;
			for(int j = 0;j<i;j++) {
				for(int m=0;m<j;m++) {
					if((prices[j]-prices[m])>before)
					{
						before = prices[j]-prices[m];
					}
				}
			}
			for(int k= i+1;k<N;k++)
			{
				if((prices[k]-prices[i])>after)
				{
					after = prices[k]-prices[i];
				}
			}
			if((before+after)>max)
			{
				max = before + after;
			}
		}
		System.out.println(max);
	}

}
