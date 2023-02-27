package Greedy;

import java.util.Scanner;

public class PS14916_BongJun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int coin5 = (n % 5) % 2 == 0 ? n / 5 : n / 5 - 1;
		if (coin5 < 0) {
			coin5 = 0;
		}
		n -= coin5 * 5;
		if (n % 2 == 1) {
			System.out.println(-1);
			System.exit(0);
		}
		int coin2 = n / 2;
		System.out.println(coin5 + coin2);
	}
}
