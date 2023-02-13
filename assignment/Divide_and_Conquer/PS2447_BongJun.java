import java.util.Scanner;

public class PS2447_BongJun {
	static char[][] board = new char[4617][4617];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				board[y][x] = ' ';
			}
		}
		drawStar(N, 0, 0);
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				sb.append(board[y][x]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static void drawStar(int length, int yPosition, int xPosition ) {
		if (length == 3) {
			for (int y = 0; y < 3; y++) {
				for (int x = 0; x < 3; x++) {
					board[yPosition + y][x + xPosition] = '*';
				}
			}
			board[yPosition + 1][xPosition + 1] = ' ';
			return ;
		}
		int nextLength = length / 3;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (x == 1 && y == 1) {
					continue;
				}
				drawStar(nextLength , yPosition + y * nextLength, xPosition + x * nextLength);
			}
		}
	}
}
