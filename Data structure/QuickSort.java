import java.util.Scanner;
import java.math.*;
public class QuickSort {
	int j=0;
	public static int m=0;
    private void quickSort(int[] a,int low,int high) {
    	if(j>=m)
    		return;
    	if(low<high){
            int middle = getMiddle(a,low,high);
           	quickSort(a,0,middle-1);
            quickSort(a,middle+1,high);
        }
    }
    private int getMiddle(int[] a, int low, int high) {
        int tep = a[low];
        while (low<high&&j<m){
            while (low<high&&a[high]>=tep){
                high--;
            }
            a[low] = a[high];
            while (low<high&&a[low]<=tep){
                low++;
            }
            a[high] = a[low];
        }
        j++;
        a[low] = tep;
        return low;
    }
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int t=in.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++) {
			a[i]=in.nextInt();
		}
		m=t;
		quickSort.quickSort(a, 0, a.length-1);
        for(int i=0;i<n;i++) {
			System.out.print(a[i]+" ");
		}
    }
}