import java.util.*;
public class java120501 {
		static int n,a,b,flag=0;
			static int[] visit=new int[5100],temp=new int[5100];
			static void bfs(int x){
				Queue<Integer> q=new LinkedList<Integer>();
				q.add(x);
				visit[x]=1;
				flag=0;
				while(!q.isEmpty()){
					int t=q.poll();	
					int ans[]={1,-1,t};
					for(int i=0;i<3;i++){
						int t1=t+ans[i];
						if(t1<0||t1>n)
							continue;
						if(visit[t1]==0){
							visit[t1]=1;
							q.add(t1);
							temp[t1]=temp[t]+1;
						}
						if(t1==b){
							flag=1;
							break;
						}
					}
					if(flag==1)
						break;
					q.peek();
				}
			}
			public static void main(String[] args) {
				// TODO Auto-generated method stub
				Scanner in=new Scanner(System.in);
				n=100;
				a=in.nextInt();
				b=in.nextInt();
				bfs(a);
				System.out.println(temp[b]);
			}
		}

	
