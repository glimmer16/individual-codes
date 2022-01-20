import java.util.Scanner;

public class 最大的数 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int []a=new int [n];
		String []b=new String [n];
		for(int i=0;i<n;i++)
		{
			a[i]=in.nextInt();
			b[i]=""+a[i];
		}
		String temp="";
		for(int i=0;i<n-1;i++)
		{
			for(int j=i+1;j<n;j++)
			{
				if((b[i]+b[j]).compareTo(b[j]+b[i])==-1)
				{
					temp=b[i];
					b[i]=b[i+1];
					b[i+1]=temp;
				}
			}
		}
		String out="";
		for(int i=0;i<n;i++)
		{
			out=out+b[i];
		}
		System.out.println(out);
	}

}
