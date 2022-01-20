import java.util.Scanner;

public class quick_sorting_encompleted {
	int j=0;
	public void sort(int[]a,int n){
		
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++) {
			a[i]=in.nextInt();
		}
		int x=0,y=n-1;
		for(int i=0;i<m;i++) {
			int k=a[0];
			while(x<y) {
				while(k<=a[y]) {
					y--;
					if(x==y) {
						a[x]=k;
						break;
					}
				}
				if(k>a[y]) {
					a[x]=a[y];
					x++;
				}
				while(k>=a[x]) {
					x++;
					if(x==y) {
						a[x]=k;
						break;
					}
				}
				if(k<a[x]) {
					a[y]=a[x];
					y--;		
				}
			}
		}
		for(int i=0;i<n;i++) {
			System.out.print(a[i]+" ");
		}
	}

}
