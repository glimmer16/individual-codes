import java.util.*;
import java.io.*;

public class gzfp_ssb {
	public static int n;
	public static int M=1000000;
	public static int[][] cost;

	public static void main(String[ ] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int m = in.nextInt();
		int[][ ] map = new int[n][n];
		cost = new int[n][n];
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				map[i][j]=M;
			}
		}
		for(int i=0;i<m;i++){
			int s = in.nextInt()-1;
			int t = in.nextInt()-1;
			int val = in.nextInt();
			cost[s][t]=map[s][t]=val;
		}
		Init(map);
		System.out.println(Assign(map));
	}

	public static void printarr( int[][]a) {	
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++){
				System.out.printf("%8d" ,a[i][j]);
			}
			System.out.println();
		}
		System.out. println( "print over! ");
	}
	public static int Assign(int[][] a) {
		int[][] zero = new int[n][n];
		int[ ] zr = new int[n];
		int[] zc = new int[n];
		int[]ret = new int[n];
		boolean[ ] row = new boolean[n];
		boolean[] col = new boolean[n];
		ret[0]=-1;
		while( !check(ret)) {
			for(int i=0;i<n;i++) {
				zr[i]=0;
				zc[i]=0;
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(a[i][j]==0) {
						zero[i][j]=1;
						zr[i]++;
						zc[j]++;
					}
					else {
						zero[i][j]=0;
					}
				}
				ret[i]=-1;
				row[i]=true;
				col[i]=true;
			}
			int k=1;
			boolean flag = true;
			do {
				flag=false;
				for(int i=0;i<n;i++) {
					if(zr[i]==k) {
					for(int j=0;j<n;j++) {
						if(zero[i][j]==1&&row[i]&&col[j]){
							ret[i]=j;
							for(int h=0;h<n;h++) {
								if(zero[h][j]==1) {
									zr[h]--;
								}
							}
							flag=true;
							row[i]=false;
							col[j]=false;
							break;
						}
					}
				}
				if(flag&&k!=1) {
					k=1;
					break;
				}
				if(zc[i]==k) {
					for(int j=0;j<n;j++) {
						if(zero[j][i]==1&&row[j]&&col[i]){
							ret[j]=i;
							for(int h=0;h<n;h++) {
								if(zero[j][h]==1) {
									zc[h]--;
								}
							}
							flag=true;
							row[j]=false;
							col[i]=false;
							break;
						}
					}
				}
				if(flag&&k!=1) {
					k=1;
					break ;
				}
			}
			if(!flag) k++;
			}while(haszero(row,col,zero));
			Invert(a,ret);
		}
		int res=0;
		for( int i=0;i<n;i++) {
			if(ret[i]>=0)
			res+=cost[i][ret[i]];
		}
		return res;
	}
	public static boolean haszero(boolean[] row,boolean[] col,int[][] zero){
		for( int i=0;i<n;i++) {
			for(int j=0;j<n;j++){
				if(zero[i][j]==1&&row[i]&&col[j])
					return true;
			}
		}
		return false;
	}
	public static boolean check(int[]a) {
		for(int i=0;i<n;i++) {
			if(a[i]==-1) return false;
		}
		return true;
	}
	public static void Init(int[][] a){
		int min = M;
		for(int i=0;i<n;i++){
			min=M;
			for(int j=0;j<n;j++){
				if(a[i][j]<min){
					min=a[i][j];
				}
			}
			for(int j=0;j<n;j++) {
				a[i][j]-=min;
				}
			}
			for(int j=0;j<n;j++){
				min=M;
				for(int i=0;i<n;i++){
					if(a[i][j]<min){
						min=a[i][j];
					}
				}
				for(int i=0;i<n;i++){
					a[i][j]-=min;
				}
			}
	}
	public static void Invert(int[][] a,int[ ] ret) {
		boolean[ ] liner = new boolean[n];
		boolean[ ] linec = new boolean[n];
		for(int i=0;i<n;i++) {
			liner[i]=true;
			linec[i]=false;
			if(ret[i]==-1) {
				liner[i]=false;
			}
		}
		boolean flag = true;
		while(flag) {
			flag=false;
			for(int i=0;i<n;i++) {
				if( !liner[i]) {
					for(int j=0;j<n;j++){
						if(a[i][j]==0&&ret[i] !=j&&!linec[j]) {
							linec[j]=true;
							flag=true;
						}
					}
				}
			}
			for(int j=0;j<n;j++) {
				if( linec[j]) {
					for(int i=0;i<n;i++) {
						if(ret[i]==j&&liner[i]){
							liner[i]=false;
							flag=true;
						}
					}
				}
			}
		}
			int min=M;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if( !liner[i]&& ! linec[j]&&a[i][j]<min) {
					min=a[i][j];
					}
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++){
					if(linec[j]){
						a[i][j]+=min;
					}
					if( !liner[i]) {
						a[i][j]-=min;
					}
				}
			}					
		}
	}