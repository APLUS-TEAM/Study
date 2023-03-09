package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon2178 {
	static int N, M;
	static int[][] arr;
	static int[][] drdc = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++)
				arr[i][j] = temp[j] - '0';
		}
		bfs(0, 0);
		System.out.println(arr[N-1][M-1]);
	}
	static void bfs(int i, int j) {
		Queue<int[]> Q = new LinkedList<>();
		Q.offer(new int[] {i, j});
		while (true) {
			int[] cur = Q.poll();
			if (cur[0] == N-1 && cur[1] == M-1) return;
			for (int k = 0; k < 4; k++) {
				int nr = cur[0] + drdc[k][0];
				int nc = cur[1] + drdc[k][1];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && arr[nr][nc] == 1) {
					arr[nr][nc] = arr[cur[0]][cur[1]] + 1;
					Q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}