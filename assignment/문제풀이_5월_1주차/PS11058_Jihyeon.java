package swea;

import java.util.Scanner;

public class PS11058_Jihyeon {

	public static void main(String[] args) {
		// 컨에이-씨-븨는 한묶음
		// 적어도 4부터는 복사한 값을 사용할 수 있는 것

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		long[] V = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			V[i] = V[i - 1] + 1; // A로 꽉채우는 것을 기본으로 두고 시작

			for (int j = 1; j <= i - 3; j++) {// j => v를 누르는 횟수.
				long temp = V[i - (j + 2)] * (j + 1);
				V[i] = Math.max(V[i], temp);
			}

		}
		System.out.println(V[N]);

	}
}