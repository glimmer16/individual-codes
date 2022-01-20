import java.util.*;
public class luoxuanfangzhen {
	public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int number = input.nextInt();

    int[][] matrix = new int[number][number];
    int max = number * number;
    int row = 0, col = 0;
    int direction = 0;
    for (int j = 1; j <= max; j++) {
        matrix[row][col] = j;
        switch (direction) {
            case 0 :
                if (col + 1 >= number || matrix[row][col + 1] > 0) {
                    direction += 1;
                    direction %= 4;
                    row += 1;
                } else {
                    col = col + 1;
                }
                break;
            case 1 :
                if (row + 1 >= number || matrix[row + 1][col] > 0) {
                    direction += 1;
                    direction %= 4;
                    col -= 1;
                } else {
                    row = row + 1;
                }
                break;
            case 2 :
                if (col - 1 < 0 || matrix[row][col - 1] > 0) {
                    direction += 1;
                    direction %= 4;
                    row = row - 1;
                } else {
                    col = col - 1;
                }
                break;
            case 3 :
                if (row - 1 < 0 || matrix[row - 1][col] > 0) {
                    direction += 1;
                    direction %= 4;
                    col += 1;
                } else {
                    row = row - 1;
                }
                break;
        }
    }
    for (int j = 0; j < number; j++) {
        for (int k = 0; k < number; k++) {
            System.out.print(matrix[j][k]+" ");        

        }
        	System.out.print("\n");
    }
	}
}