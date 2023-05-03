import java.io.*;
import java.util.*;

public class Baekjoon1083 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(br.readLine());
		for (int i = 0; i < N && S > 0; ++i) {
			int max = arr[i], idx = i;
			for (int j = i+1; j < N && j <= S+i; ++j) {
				if (max < arr[j]) {
					max = arr[j];
					idx = j;
				}
			}
			for (int j = idx; j > i; --j) {
				int temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp;
				--S;
			}
		}
		for (int i : arr) sb.append(i).append(' ');
		System.out.print(sb);
	}
}
