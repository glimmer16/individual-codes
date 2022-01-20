package tree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import java.util.Scanner;
class Node{

    public String data;

    public Node lchild;

    public Node rchild;

    public int ltag = 1;

    public int rtag = 1;

    private static Node parse(String str, int[] count){
        if(str.charAt(count[0]) == '#'){
            count[0] ++;
            return null;
        }
        else{
            Node node = new Node();
            node.data = str.substring(count[0], count[0] + 1);
            count[0] ++;
            node.lchild = parse(str, count);
            node.rchild = parse(str, count);
            return node;
        }
    }

    public static Node parse(String str){
        return parse(str, new int[1]);
    }

    public static String pre_order(Node node){
        if(node == null){
            return "";
        }
        else{
            String str = node.data;
            str += pre_order(node.lchild);
            str += pre_order(node.rchild);
            return str;
        }
    }

     public static String in_order(Node node){
        if(node == null){
            return "";
        }
        else{
            String str = in_order(node.lchild);
            str += node.data;
            str += in_order(node.rchild);
            return str;
        }
    }


    public static String post_order(Node node){
        if(node == null){
            return "";
        }
        else{
            String str = post_order(node.lchild);
            str += post_order(node.rchild);
            str += node.data;
            return str;
        }
    }

    public static int leaf_number(Node node){
        if(node == null){
            return 0;
        }
        else if(node.lchild == null && node.rchild == null){
            return 1;
        }
        else{
            return leaf_number(node.lchild) + leaf_number(node.rchild);
        }
    }

    public static int depth(Node node){
        if(node == null){
            return 0;
        }
        else{
            return 1 + Math.max(depth(node.lchild), depth(node.rchild));
        }
    }
   
    public static String in_order2(Node node){
        String str = "";
        Stack<Node> stack = new Stack<Node>();
        Node p = node;
        while(p != null || stack.isEmpty() == false){
            if(p != null){
                stack.push(p);
                p = p.lchild;
            }
            else{
                p = stack.pop();
                str += p.data;
                p = p.rchild;
            }
        }
        return str;
    }
    public static String level_order(Node node) {
    	String str="";
    	Queue<Node> queue = new LinkedList<>();
    	Node q=node;
    	queue.add(q);
    	while(q!=null&queue.isEmpty()==false) {
    		q=queue.poll();
    		str+=q.data;
    		if(q.lchild!=null) {
    			queue.add(q.lchild);
    		}
    		if(q.rchild!=null) {
    			queue.add(q.rchild);
    		}    		
    	}
    	return str;
    }
    
    public static boolean isCompleteTree(Node node) {
        if(node==null)
            return false;
        Queue<Node> qu = new LinkedList<>();
        qu.add(node);
        Node leftLeaf;
        Node rightLeaf;
        boolean flag = false;
        while(!qu.isEmpty()){
            Node tmp = qu.poll();
            leftLeaf = tmp.lchild;
            rightLeaf = tmp.rchild;
            if(rightLeaf!=null&&leftLeaf==null)
                return false;
            if(flag&&(leftLeaf!=null||rightLeaf!=null))
                return false;
            if(leftLeaf!=null)
                qu.add(leftLeaf);
            if(rightLeaf!=null)
                qu.add(rightLeaf);
            else
                flag = true;
        }
        return true;
    }

//    public static String construct(String pre_order, String in_order){
//    }

     public static void thread(Node node){
        Stack<Node> stack = new Stack<Node>();
        Node p = node;
        Node pre = null;
        while(p != null || stack.isEmpty() == false){
            if(p != null){
                stack.push(p);
                p = p.lchild;
            }
            else{
                p = stack.pop();
                if(p.lchild == null){
                    p.lchild = pre;
                    p.ltag = 2;
                }
                if(pre != null && pre.rchild == null){
                    pre.rchild=  p;
                    pre.rtag = 2;
                }
                pre = p;
                p = p.rchild;
            }
        }
    }

    public static String thread_visit(Node node){
        String str = "";
	    Node p = node;
	    while(p != null){
		    while(p != null && p.ltag == 1) p = p.lchild;
		    boolean flag = true;
		    while(p != null && flag){
                str += p.data;
			    if(p.rtag == 1)
				    flag = false;
			    p = p.rchild;
		    }
        }
        return str;
    }
    public static int depth1(Node node){
        if(node == null){
            return 0;
        }
        else{
            return  1+depth1(node.lchild);
        }
    }
}

public class Test {
	
	public static String str=new String();
	
	public static String construct(String pre_order, String in_order){ 
		if(pre_order.length()==0||in_order.length()==0) {
			return null;
		}
    	str+=pre_order.substring(0,1);
    	int i=0;
    	while(in_order.charAt(i)!=pre_order.charAt(0)){
    		i++;
    	}
    	if(i==0) {
    		str+='#';
    		String pre2=pre_order.substring(i+1);
        	String in2=in_order.substring(i+1);
        	construct(pre2, in2);
    	}
    	
    	if(i==in_order.length()-1) {
    		String pre1=pre_order.substring(1,i+1);
        	String in1=in_order.substring(0,i);
        	construct(pre1, in1);
    		str+='#';
    	}
    	else if(i!=0){
    		String pre1=pre_order.substring(1,i+1);
        	String in1=in_order.substring(0,i);
        	construct(pre1, in1);
        	String pre2=pre_order.substring(i+1);
        	String in2=in_order.substring(i+1);
        	construct(pre2, in2);
    	}
//    	String pre1=pre_order.substring(1,i+1);
//    	String in1=in_order.substring(0,i);
//    	construct(pre1, in1);
//    	String pre2=pre_order.substring(i+1);
//    	String in2=in_order.substring(i+1);
//    	construct(pre2, in2);
    	return str;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
//(1)		String str=in.nextLine();
//		Node root = Node.parse(str);
       // System.out.println(Node.pre_order(root));
//        System.out.println(Node.in_order(root));
//        System.out.println(Node.post_order(root));
//        System.out.println(Node.level_order(root));
//        System.out.println(Node.depth(root));
// (1)       System.out.println(Node.leaf_number(root));
      //  System.out.println(Node.in_order2(root));
        //Node.thread(root);
        //System.out.println(Node.thread_visit(root));
// (2)      String s1=in.nextLine();
//       String s2=in.nextLine();
//       String str=construct(s1,s2);
//       //System.out.println(str);
//       Node root =Node.parse(str);
//       System.out.println(Node.post_order(root));
//       if(Node.isCompleteTree(root)) {
//    	   System.out.println("Yes");
//       }
//       else {
//    	   System.out.println("No");
//  (2)     }
		String s1=in.nextLine();
		String s2=in.nextLine();
		String str=construct(s1,s2);
//		System.out.println(str);
		Node root=Node.parse(str);
//		System.out.println(Node.post_order(root));
//      System.out.println(Node.level_order(root));
		int i=0,t=0;
		int[]a=new int [s1.length()];
		while(root!=null) {
			a[i]=Node.depth1(root);
			i++;
			root=root.rchild;			
		}
		for(int j=0;j<i;j++) {
			if(t<a[j]) {
				t=a[j];
			}			
			//System.out.println(a[j]);
		}
		root=Node.parse(str);
		if(root==null) {
			System.out.println(0);
		}else {
			System.out.println(t);
		}
	}
}
