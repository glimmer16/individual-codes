//package map;

import java.util.Stack;

import java.util.Scanner;
class Node {


    public int data;

    public Node next;

    public Node(){
        data = 0;
        next = null;
    }
}
 class LinkQueue{

    public Node head;

    public Node rear;

    public int n;

    public LinkQueue(){
        head = new Node();
        rear = head;
        n = 0;
    }

    public void clear(){
        head.next = null;
        rear = head;
        n = 0;
    }

    public boolean is_empty(){
        if(n == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int length(){
        return n;
    }

    public int get_head() throws Exception{
        if(n == 0){
            throw new Exception("wrong");
        }
        return head.next.data;
    }

    public void enqueue(int a){
        Node node = new Node();
        node.data = a;
        rear.next = node;
        rear = node;
        n ++;
    }

    public int dequeue() {//throws Exception{
//        if(n == 0){
//            throw new Exception("wrong");
//        }
        int a = head.next.data;
        head.next = head.next.next;
        n--;
        if(n == 0){
            rear = head;
        }
        return a;
    }
}
public class picture2 {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int m=scan.nextInt();
        int[][] a=new int[n][n];
        for(int i=0;i<m;i++){
            int x=scan.nextInt();
            int y=scan.nextInt();
            a[x][y]=1;
            a[y][x]=1;
        }
        scan.close();
        String dfs=dfs(a,n);
        System.out.println(dfs);
        String bfs=bfs(a,n);
        System.out.println(bfs);
    }
    public static String dfs(int[][] a,int n){
        String str="";
        Stack<Integer> t=new Stack<>();
        t.add(0);
        str=str+"0";
        int[] mark=new int[n];
        mark[0]=1;
        while(t.size()>0){
            int term=t.peek();
            int i=0;
            for(;i<n;i++){
                if(a[term][i]==1&&mark[i]==0){
                    t.add(i);
                    mark[i]=1;
                    str=str+" "+i;
                    break;
                }
            }
            if(i==n){
                t.pop();
            }
        }
        return str;
    }

    public static String bfs(int[][] a,int n) {
        String str="";
        LinkQueue queue=new LinkQueue();
        queue.enqueue(0);
        str=str+"0";
        int[] mark=new int[n];
        mark[0]=1;
        while(!queue.is_empty()){
            int term=queue.dequeue();
            for(int i=0;i<n;i++){
                if(a[term][i]==1&&mark[i]==0){
                    queue.enqueue(i);
                    str=str+" "+i;
                    mark[i]=1;
                }
            }
        }
        return str;
    }
}

