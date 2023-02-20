package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PS1339_DuHyun {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 각 문자별 가중치를 줄 예정이다~
		int[] arr = new int[26];

		int sum = 0;
		for (int i = 0; i < N; i++) {

			String s = br.readLine();
			for (int j = 0; j < s.length(); j++) {

				arr[s.charAt(j) - 'A'] += (int) Math.pow(10, s.length()-j-1);

			}
		}

		Arrays.sort(arr);
		int A = 9;

		for (int j = 25; j > 0; j--) {

			if (arr[j] == 0)
				break;

			sum += A * arr[j];
			A--;

		}

		System.out.println(sum);

	}

}