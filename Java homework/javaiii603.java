import java.util.Scanner;
public class javaiii603 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int [][]a=new int [n][2];
		for(int i=0;i<n;i++)
		{
			a[i][0]=in.nextInt();
			a[i][1]=in.nextInt();
		}
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a.length-1-i;j++)
			{
				if(a[j][1]>a[j+1][1])
				{
					int tmp1=a[j+1][1];
					int tmp2=a[j+1][0];
					a[j+1][1]=a[j][1];
					a[j+1][0]=a[j][0];
					a[j][1]=tmp1;
					a[j][0]=tmp2;
				}
			}
		}
		int num=1,i=0;
		for(int j=1;j<n;j++)
		{
			if(a[j][0]>//=
			a[i][1])
			{
				i=j;
				num++;
			}
		}
		System.out.println(num);
	}

}
