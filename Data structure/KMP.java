import java.util.Scanner;
import java.util.*;
public class KMP {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String t = in.nextLine();
        System.out.println(kmp_indexof(s,t));
	}
	public static int kmp_indexof(String s, String t){
		int[] next = get_nextval(t);
		int i = 0, j = 0;
		while(i < s.length() && j < t.length()){
			if(j == -1 || s.charAt(i) == t.charAt(j)){
				i++;
				j++;
			}
			else
				j = next[j];
		}
		if(j >= t.length())
			return i - t.length();
		else
			return -1;
	}
	public static int[] get_nextval(String t){
		int[] next = new int[t.length()];
		next[0] = -1;
		int i = 0, j = -1;
		while(i < t.length() - 1){
			if(j == -1 || t.charAt(i) == t.charAt(j)){
				i++;
				j++;
				if(t.charAt(i) != t.charAt(j))
					next[i] = j;
				else
					next[i] = next[j];
				}
			else{
				j = next[j];
			}
		}
		return next;
	}
}