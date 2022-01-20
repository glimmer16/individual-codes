import java.util.*;
 
    class Point2{
        int step = 0,x=0;
        public Point2(int x,int step){
            this.x=x;
            this.step = step;
        }
    }
    public class java120502{
        static int n=0,A=0,B=0,result=0;
        static int[] oprate = {1,-1,2};
        static int[] visit = null;
        public static void main(String[] args) {
            // TODO Auto-generated method stub
          Scanner sc = new Scanner(System.in);
          n=100;
          A=sc.nextInt();
          B=sc.nextInt();
          visit = new int[n+1];
          bfs(A);
          System.out.print(result);
        }
        public static void bfs(int a){
            Queue<Point2> queue = new LinkedList<Point2>();
            visit[a] = 1;
            queue.offer(new Point2(a,0));
            int flag = Math.abs(A-B);
            while(!queue.isEmpty()){
                Point2 temp = queue.poll();
                boolean find = false;
                    for(int i=0;i<3;i++){
                        if(i==2){
                            if(temp.x*2<=n){
                                if(temp.x*2==B){
                                    result = temp.step+1;
                                    find = true;
                                    break;
                                }
                                if(visit[temp.x*2]==0){
                                    visit[temp.x*2]=1;
                                    queue.offer(new Point2(temp.x*2,temp.step+1));
                                }
                            }
                        }else{
                            if(temp.x+oprate[i]<=n){
                                if(temp.x+oprate[i] == B){
                                    result = temp.step+1;
                                    find = true;
                                    break;
                                }
                                if(visit[temp.x+oprate[i]]==0){
                                    visit[temp.x+oprate[i]]=1;
                                    queue.offer(new Point2(temp.x+oprate[i],temp.step+1));
                                }
                            }
                        }
                    }
                if(find){
                    break;
                }
            }
        }
    }