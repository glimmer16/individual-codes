package search;

import java.util.Scanner;

public class Main{

    public static void main(String[] args){
        BinarySortTree tree = new BinarySortTree();
        String s=new String();
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        in.nextLine();
        for(int i=0;i<5;i++) {
        	s=in.nextLine();
//        	System.out.println(s);
        	if(s.charAt(0)=='I') {
        		tree.insert(Integer.parseInt(String.valueOf(s.charAt(2))), null);
        	}
        	else {
        		tree.delete(Integer.parseInt(String.valueOf(s.charAt(2))));
        	}
        }
        System.out.println(tree.pre_order());
        System.out.println(tree.in_order());
    }
}