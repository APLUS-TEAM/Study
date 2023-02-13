import java.util.Scanner;

public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sb.append((int)Math.pow(2, N) - 1).append('\n');
		hanoi(N, 1, 3);
		System.out.println(sb);
	}

	static void hanoi(int number, int from, int to) {
		int tempPosition = 6 - from - to;
		if (number == 1) {
			sb.append(from).append(' ').append(to).append('\n');
			return ;
		}
		hanoi(number - 1, from, tempPosition);
		sb.append(from).append(' ').append(to).append('\n');
		hanoi(number - 1, tempPosition, to);
	}

}
