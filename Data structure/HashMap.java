import java.util.Scanner;

public class HashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		int a[]=new int[n];
		int b[]=new int[m];
		int c[]=new int[m];
		int d[]=new int[m];
		int e[]=new int[m];
		int k=0,t=0;
		double sum1=0,sum2=0;
		
		for(int i=0;i<n;i++) {
			a[i]=in.nextInt();
			k=a[i]%m;
			t=0;
			if(b[k]==0) {
				b[k]=a[i];
				c[k]++;
			}
			else {
				while(b[k]!=0) {
					k++;
					if(k>=m) {
						k-=m;
					}
					t++;
				}
				b[k]=a[i];
				c[k]=t+1;
			}
		}
		for(int i=0;i<m;i++) {
			sum1+=c[i];
		}
		
		int p=0,l=0;
		for(int i=0;i<n;i++) {
			k=a[i]%m;
			t=0;
			p=0;					
			l=k;
			if(d[k]==0) {
				d[k]=a[i];
				e[k]++;
			}
			else {
				while(d[k]!=0) {
					p++;
					k=l+p*p;
					if(k>=m) {
						k-=m;
					}
					t++;
					if(d[k]==0) {
						break;
					}
					else {
						k=l-p*p;
						if(k<0) {
							k+=m;
						}
						t++;
					}
				}
				d[k]=a[i];
				e[k]=t+1;
			}
		}
		for(int i=0;i<m;i++) {
//			System.out.print(d[i]+" ");
//			System.out.print(e[i]+" ");
			sum2+=e[i];
		}
		System.out.println(String.format("%.2f", sum1/n)+" "+String.format("%.2f", sum2/n));
	}

}
