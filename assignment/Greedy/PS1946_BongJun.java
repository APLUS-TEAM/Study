package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS1946_BongJun {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] scores = new int[N][2];
			for (int index = 0; index < N; index++) {
				st = new StringTokenizer(br.readLine());
				scores[index] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
				scores = countingSort(scores);
				int count = 0;
				int level = N + 1;
				for (int index = 0; index < N; index++) {
					if (level > scores[index][1]) {
						count++;
						level = scores[index][1];
					}
				}
				sb.append(count).append('\n');
			}
		System.out.println(sb);
		}
	
	static int[][] countingSort(int[][] arr) {
		int size = arr.length;
		int[] counts = new int[size + 1];
		for (int index = 0; index < size; index++) {
			counts[arr[index][0]]++;
		}
		for (int index = 1; index < size + 1; index++) {
			counts[index] = counts[index] + counts[index - 1];
		}
		int[][] newArr = new int[size][];
		for (int index = size - 1; index >= 0; index--) {
			newArr[counts[arr[index][0]] - 1] = arr[index];
		}
		return newArr;
	}
}
