import java.util.*;
public class migongfinal {
	static int[]xx = {0,1,0,-1};
	static int[] yy = {1,0,-1,0};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int[][]map = new int[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
			map[i][j]=in.nextInt();
			}
		}
		Point n1 = new Point(in.nextInt(),in.nextInt());
		Point n2 = new Point(in.nextInt(),in.nextInt());
		Stack<Point> stack = new Stack<Point>();
		stack.push(n1);
		do {
			Point p = stack.pop();
			for(int i=0;i<4;i++) {
				int x=p.x+xx[i];
				int y=p.y+yy[i];
				if(x>=0&&x<m&&y>=0&&y<n&&map[x][y]==0) {
					stack.push(new Point(x,y));
				}
				if(x==n2.x&&y==n2.y){
					System.out.println("Yes");
					return;
				}
			}
			map[p.x][p.y]=1;
		}while( !stack.isEmpty());
		
		System.out.println("No" );
	}
}
	class Point{
		int x;
		int y;
		public Point(int x,int y) {
			this.x=x;
			this.y=y;
		}
	}