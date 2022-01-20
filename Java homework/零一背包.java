import java.util.Scanner;

public class ÁãÒ»±³°ü {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int W=in.nextInt();
		int n=in.nextInt();
		int[]w=new int [n];
		int[]v=new int [n];
		for(int i=0;i<n;i++)
		{
			w[i]=in.nextInt();
			v[i]=in.nextInt();
		}
		int [][]a=new int [n][W/10+1];
		for(int j=0;j<W/10;j++)
		{
			if(w[0]/10>j)
				a[0][j]=0;
			else
				a[0][j]=v[0];
		}
		for(int i=1;i<n;i++)
		{
			for(int j=1;j<=W/10;j++)
			{
				if(j<w[i]/10)
					a[i][j]=a[i-1][j];
				else
					a[i][j]=Math.max(a[i-1][j],a[i-1][j-w[i]/10]+v[i]);
			}
		}
		System.out.println(a[n-1][W/10]);
	}

}
