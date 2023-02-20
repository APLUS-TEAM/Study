package codeTest.baekjoon;

import java.util.Scanner;

public class Q14916 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int money = sc.nextInt();
		int count = 0;

		if(money < 4) {
			if(money % 2 == 0) {
				count = 1;
			} else {
				count = -1;
			}
		} else {
			count = money / 5;

			money = money - 5 * count;

			if (money % 2 == 1) {
				money += 5;
				count--;
			}

			count += money / 2;
		}

		System.out.println(count);
	}

}
