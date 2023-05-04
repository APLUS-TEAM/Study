package day010;

import java.io.*;
import java.util.*;

public class Main1083 {
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int S = Integer.parseInt(br.readLine());

		int start = -1;

		while (S > 0 && start < N - 1) {

			start++;
			int max = arr[start];
			int index = start;
			int C = Math.min(N - 1, start + S);

			for (int i = start + 1; i <= C; i++) {
				max = Math.max(max, arr[i]);
				if (arr[i] == max)
					index = i;
			}

			if (max == arr[start])
				continue; // 자기가 젤 큰수면 이동 필요 X;

			if (start + S >= index) {
				S -= (index - start);
				
				for (int i=index;i>start;i--) {
					swap(i,i-1);
				}
			}

		}

		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
		}

		System.out.println(sb);
	}

	static void swap(int a, int b) {

		int temp1 = arr[a];
		int temp2 = arr[b];
		arr[a] = temp2;
		arr[b] = temp1;
		return;
	}

}