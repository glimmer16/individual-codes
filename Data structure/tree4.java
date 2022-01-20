import java.util.Scanner;

public class tree4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		String s=in.nextLine();
		int n=s.length();
		char []a=new char[n];
		int []b=new int[n];
		int k=0,t=0;
		for(int i=0;i<n;i++) {
			t=0;
			for(int j=0;j<=k;j++) {
				if(a[j]==s.charAt(i)) {
					b[j]++;
					t=1;
					break;
				}
			}
			if(t==0)
			{
				a[k]=s.charAt(i);
				b[k]++;
				k++;
			}
		}
//		for(int i=0;i<k;i++) {
//			System.out.print(a[i]);
//			System.out.print(b[i]+" ");
//		}
		for(int i=0;i<k-1;i++) {  
			for(int j=0;j<k-i-1;j++) {
				if(b[j]<b[j+1]) {
					t=b[j];
					b[j]=b[j+1];
					b[j+1]=t;
				}
			}
		}
//		for(int i=0;i<k;i++) {
//			System.out.print(b[i]+" ");
//		}
		int sum=0;
		for(int i=0;i<k;i++) {
            sum+=b[i]*(i+1);
        }
        if(k!=1) {
            sum-=b[k-1];
        }
		System.out.println(sum);
	}
}
