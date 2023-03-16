package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon7562 {
	static int N, ansR, ansC;
	static int[][] arr;
	static int[][] drdc = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-->0) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int curR = Integer.parseInt(st.nextToken()), curC = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ansR = Integer.parseInt(st.nextToken()); ansC = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			bfs(curR, curC);
			sb.append(arr[ansR][ansC]).append('\n');
		}
		System.out.println(sb);
	}
	static void bfs(int curR, int curC) {
		Queue<int[]> Q = new LinkedList<>();
		Q.offer(new int[] {curR, curC});
		while (true) {
			int[] cur = Q.poll();
			if (cur[0] == ansR && cur[1] == ansC) return;
			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + drdc[i][0];
				int nc = cur[1] + drdc[i][1];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && arr[nr][nc] == 0) {
					arr[nr][nc] = arr[cur[0]][cur[1]] + 1;
					Q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}