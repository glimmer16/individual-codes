import java.util.Scanner;
public class java092601copy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Scanner in=new Scanner(System.in);
			int weight,n;
			weight=in.nextInt();
			n=in.nextInt();
			int[] w=new int[n+1];
			int[] v=new int[n+1];
			int[] p=new int[weight+1];
			for(int k=1;k<=n;k++) {
				w[k]=in.nextInt();
				v[k]=in.nextInt();
			}
			int i,j;
			for(i=1;i<=n;i++) {
				for(j=weight;j>=w[i];j--) {
					if(p[j]<=p[j-w[i]]+v[i])
						p[j]=p[j-w[i]]+v[i];
				}
			}
			System.out.println(p[weight]);

	}

}
