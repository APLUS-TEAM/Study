package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PS14916_DuHyun {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		int cnt = 0;
		int A = T / 5;
		int B = T % 5;

		if (A == 0) {

			if (B % 2 == 0) {
				cnt += B / 2;
			} else
				cnt = -1;

		} else if (B == 0 || B == 2 || B == 4) {
			
			cnt += A;
			cnt += B / 2;

		} else {
			cnt += A - 1;
			cnt += (B + 5) / 2;
		}

		System.out.println(cnt);

	}

}