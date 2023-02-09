package ps;

import java.util.Scanner;

public class PS11729_bongjun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(hanoi(N));
	}
	
	static int hanoi(int number) {
		if (number == 1) {
			return 1;
		} else if (number == 2) {
			return 3;
		}
		return 2 * hanoi(number - 1) + 1;
	}
}
