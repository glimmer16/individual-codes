import java.util.Scanner;
public class first{

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int foot;
		int inch;
		Scanner in = new Scanner(System.in);
		foot=in.nextInt();
		inch=in.nextInt();
		System.out.println ((foot+inch/12.0)*0.3048);
	}
}