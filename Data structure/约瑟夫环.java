import java.util.Scanner;
public class Ô¼Éª·ò»· {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				Scanner scan=new Scanner(System.in);
				int m=scan.nextInt();
				int n=scan.nextInt();
				int []a=new int[n];
				int []b=new int[n];
				int k=0;
				for(int i=0;i<n;i++)
				{
					a[i]=scan.nextInt();
					b[i]=i+1;
				}
				int i=0;
				while(n>0)
				{
					
					i+=m;
					k=i%n;
					if(k==0)
						k=n;
					System.out.println(b[k-1]);
					m=a[k-1];
					i=k-1;
					while(k<n)
					{
						a[k-1]=a[k];
						b[k-1]=b[k];
						k++;
					}
					n--;
				}
	}
}
