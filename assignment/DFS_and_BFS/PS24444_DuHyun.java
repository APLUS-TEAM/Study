package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main24479 {

	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	static Integer[] answer;
	static int cnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		// 이진트리 처럼 정해진게 아니라 arraylist로 받음.
		arr = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		answer = new Integer[N + 1];

		for (int i = 1; i < N + 1; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int temp1 = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			arr[temp1].add(temp2);
			arr[temp2].add(temp1);
		}

		// 오름 차순으로 나타내야 해서 정렬 해줌
		for (int i = 1; i < N + 1; i++)
			Collections.sort(arr[i]);

		cnt = 1;
		bfs(R);

		for (int i = 1; i < N + 1; i++) {

			if (answer[i] != null) {
				sb.append(answer[i]);
			} else {
				sb.append(0);
			}
			sb.append("\n");
		}

		System.out.print(sb);

	}

	static void bfs(int start) {

		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(start);
		visited[start] = true;
		answer[start] = cnt;
		cnt++;

		while (!Q.isEmpty()) {
			int temp = Q.poll();
			for (int e : arr[temp]) {
				if (!visited[e]) {
					Q.offer(e);
					visited[e] = true;
					answer[e] = cnt;
					cnt++;
				}

			}

		}

	}

}
