import java.io.*;
import java.util.*;

public class Baekjoon10819 {
	static int N, max = 0;
	static int[] arr, per;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		per = new int[N];
		visit = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());
		find(0);
		System.out.println(max);
	}
	static void find(int depth) {
		if (depth == N) {
			System.out.println(Arrays.toString(per));
			int temp = 0;
			for (int i = 1; i < N; ++i) temp += Math.abs(per[i-1]-per[i]);
			max = Math.max(max, temp);
			return;
		}
		for (int i = 0; i < N; ++i) {
			if (!visit[i]) {
				per[depth] = arr[i];
				visit[i] = true;
				find(depth+1);
				visit[i] = false;
			}
		}
	}
}
