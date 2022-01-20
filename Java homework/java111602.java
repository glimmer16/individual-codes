import java.util.Scanner;
import java.util.Arrays;
public class java111602 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int sum=0;
		int []a=new int [n];
		for(int i=0;i<n;i++)
		{
			a[i]=in.nextInt();
		}
		Arrays.sort(a);
		while(n>0)
		{
			if(n==1)
			{
				sum+=a[0];
				break;
			}
			else if(n==2)
			{
				sum+=a[1];
				break;
			}
			else if(n==3)
			{
				sum+=a[0]+a[1]+a[2];
				break;
			}
			else
			{
				if(a[n-2]+a[0]<=2*a[1])
				{	
					sum+=a[n-1]+a[n-2]+2*a[0];
				}
				else
				{
					sum+=a[n-1]+a[0]+2*a[1];
				}
				n-=2;
			}
		}
		System.out.println(sum);
	}

}
