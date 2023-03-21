import java.io.*;
import java.util.*;

public class Baekjoon15649 {
	static int N, M;
	static int[] arr;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(new BufferedReader(new InputStreamReader(System.in)).readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		visit = new boolean[N+1];
		permutation(0);
		System.out.println(sb);
	}
	static void permutation(int depth) {
		if (depth == M) {
			for (int i : arr) sb.append(i).append(' ');
			sb.append('\n');
			return;
		}
		for (int i = 1; i <= N; ++i) {
			if (!visit[i]) {
				arr[depth] = i;
				visit[i] = true;
				permutation(depth+1);
				visit[i] = false;
			}
		}
	}
}
