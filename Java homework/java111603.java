import java.util.Scanner;
public class java111603 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		int sum=0,x=1,y=0;
		int []a=new int[m];
		for(int i=0;i<m;i++)
		{
			a[i]=in.nextInt();
		}
		/*while(sum<n)
		{
			while(x<=m&&a[x-1]<=sum)
			{
				sum+=a[x-1];
				x++;
			}
			if(x<=m&&a[x-1]==sum+1)
				x++;
			else
				y++;
		}*/
		int i=0;
		while(x <= n) 
		{
            if(i < m && a[i] <= x) 
            {
                x = x + a[i];
                i++;
            } 
            else 
            {
                x+=x;
                y++;
            }
        }
		System.out.println(y);
	}

}
