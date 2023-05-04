package day010;

import java.io.*;
import java.util.*;

public class Main1022 {

	static int[][] arr;
	static int A;
	static int B;
	static int sum;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());

		A = r2 - r1 + 1;
		B = c2 - c1 + 1;
		int[][] arr = new int[A][B];
		sum = B * A;
		int max = 0;

		for (int i = r1; i <= r2; i++) {
			for (int j = c1; j <= c2; j++) {
				arr[i-r1][j-c1] = def(i, j);
				max = Math.max(max, arr[i-r1][j-c1]);
			}
		}
		
		String S = max+"";
		int length = S.length();
		
		
		for (int i=0;i<A;i++) {
			for (int j=0;j<B;j++) {
				sb.append(String.format("%"+length+"d", arr[i][j])).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

	static int def(int i, int j) {
			

		int max = Math.max(Math.abs(i), Math.abs(j)); // 0에서까지 최대 길이가 중요!
		

		int K = max * 2 + 1;

		if (i == max) {
			return K * K - max + j;
		}

		if (i == -max) {
			return K * K - max * 5 - j;
		}

		if (j == max) {

			return K * K - max * 7 - i;
		}

		if (j == -max) {

			return K * K - max * 3 + i;
		}

		return 0;
	}

}
