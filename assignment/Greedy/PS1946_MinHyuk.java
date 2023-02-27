package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon1946 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-->0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				// 서류심사 기준으로 정렬
				arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}
			int cnt = 1; // 서류 1등은 무조건 선발가능 인원
			int max = arr[1]; // 현재 면접등수 가장 높은 사람
			for (int i = 2; i <= N; i++) 
				// i번째 사람의 면접 등수가 현재 면접등수 가장 높은 사람보다 높으면
				if (max > arr[i]) {
					// 선발가능인원 + 1
					cnt++;
					// 현재 면접등수 가장 높은 사람 변경
					max = arr[i];
				}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}