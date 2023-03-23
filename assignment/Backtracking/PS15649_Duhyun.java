package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15649 {

	static StringBuilder sb = new StringBuilder();
	static int N;
	static int M;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //1~N까지
		M = Integer.parseInt(st.nextToken()); // M개 고르기
		arr = new int[M + 1];
		def(0,1);
		System.out.println(sb);

	}

	public static void def(int K, int T) {

		if (K == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if ((T & 1<<i) != 0) continue;
			arr[K] = i;
			def(K+1,T|1<<i);

		}

	}

}
