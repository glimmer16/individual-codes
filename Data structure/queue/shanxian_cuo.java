
package queue;
import java.util.*;
import java.util.Queue;

public class shanxian_cuo {
	static int[]xx= {0,1,-1};
	static int[]visited=new int[10000];
	public static void main(String[] args) throws NumberFormatException, Exception {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int a=in.nextInt();
		int b=in.nextInt();
		System.out.println(bfs(a,b)-1);
	}
	public static int bfs(int a,int b) throws NumberFormatException, Exception {
		LinkQueue q=new LinkQueue();
		visited[a]=1;
		String x=Integer.toString(a);
		String y=Integer.toString(b);
		q.enqueue(x);
		while(!q.is_empty())
		{
			int head=Integer.parseInt( q.get_head());
			if(head==b)
				return visited[head];
			int zx;
			q.dequeue();
			for(int i=0;i<3;i++)
			{
				if(i==0)zx=head*2;
				else zx=head+xx[i];
				if(zx<0||zx>100000||visited[zx]!=0)
					continue;
				q.enqueue(String.valueOf(zx));
				visited[zx]=visited[head]+1;
			}
		}
		return -1;
	}
}
