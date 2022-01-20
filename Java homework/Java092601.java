import java.util.Scanner;
public class Java092601 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int wei=in.nextInt();
		int num=in.nextInt();
		int  [][] a = new int [num][2];
		for(int i=0;i<num;i++)
		{
			a[i][0]=in.nextInt();
			a[i][1]=in.nextInt();
		}
		int [][] b=new int[num+1][wei+1];
        int i=0,j=0;
		for( i=1;i<num+1;i++) 
		{
			for( j=1;j<wei+1;j++)
			{
				if(a[i-1][0]<=j)
				{
					if(b[i-1][j]<(b[i-1][j-a[i-1][0]]+a[i-1][1]))
						b[i][j]=b[i-1][j-a[i-1][0]]+a[i-1][1];
					else
						b[i][j]=b[i-1][j];
				}
				else
					b[i][j]=b[i-1][j];
			}
		}
		System.out.println(b[i-1][j-1]);
		/*int x[]=new int[num];
		for(int i=num;i>0;i--)
		{
			if(b[i][wei]>b[i-1][wei])
			{
				x[i-1]=1;
				wei-=a[i-1][0];
			}
		}
		for(int j=0;j<num;j++)
		{
			System.out.println(x[j]);
		}*/
	}
}


