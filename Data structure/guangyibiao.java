import java.util.*;
public class guangyibiao {
	 public static void main (String[] args) {
	        Scanner scan=new Scanner(System.in);
	        int n=scan.nextInt();
	        String[] str =new String[n];
	        char[] name=new char[n];
	        int[] result=new int[n];
	        for(int i=0;i<n;i++){
	            str[i]=scan.next();
	            name[i]=str[i].charAt(0);
	            str[i]=str[i].substring(2);
	        }
	        scan.close();
	        for(int i=0;i<n;i++){
	            for(int j=0;j<str[i].length();j++){
	                if(str[i].charAt(j)==name[i]){
	                    result[i]=-1;
	                }
	            }
	        }
	        int t=n;
	        while(t>0){
	            for(int i=0;i<n;i++){
	                if(result[i]==0){
	                    char[] mark=new char[n];
	                    result[i]=getDepth(str[i],name,result,mark,str,i);
	                }
	            }
	            t--;
	        }
	        for(int i=0;i<n;i++){
	            if(result[i]==-1||result[i]==0){
	                System.out.println("infity");
	            }
	            else {
	                System.out.println(result[i]);
	            }
	        }
	    }
	    public static int getDepth(String strSon,char[] name,int[] result,char[] mark,String[] str,int k){
	        int depth=0;
	        for(int i=0;i<strSon.length();i++){
	            char term=strSon.charAt(i);
	            if(term=='('){
	                depth++;
	                if(depth>result[k]){
	                    result[k]=depth;
	                }
	            }
	            else if(term==')'){
	                depth--;
	                if(depth>result[k]){
	                    result[k]=depth;
	                }
	            }
	            else if(Character.isUpperCase(term)){
	                boolean judge=true;
	                int j;
	                for(j=0;j<name.length;j++){
	                    if(name[j]==term){
	                        break;
	                    }
	                }
	                if(result[j]==-1){
	                    return -1;
	                }
	                else if(result[j]>0){
	                    if(depth+result[j]>result[k]){
	                        result[k]=depth+result[j];
	                    }
	                }
	                else if(result[j]==0){
	                	return 0;
	                }
	            }
	            else{
	                continue;
	            }
	        }
	        return result[k];
	   }
}