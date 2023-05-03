import java.io.*;
import java.util.*;

public class Baekjoon2573 {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) map[i][j] = Integer.parseInt(st.nextToken());
		}
		int cnt;
		int year = 0;
		while (true) {
			++year;
			melt();
			cnt = 0;
			visit = new boolean[N][M];
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < M; ++j) {
					if (map[i][j] != 0 && !visit[i][j]) {
						dfs(i, j);
						if (++cnt > 1) {
							System.out.println(year);
							System.exit(0);
						}
					}
				}
			}
			if (cnt == 0) {
				System.out.println(0);
				break;
			}
		}
	}
	static void melt() {
		visit = new boolean[N][M];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (map[i][j] != 0) {
					visit[i][j] = true;
					int cnt = 0;
					for (int k = 0; k < 4; ++k) {
						int nr = i + delta[k][0];
						int nc = j + delta[k][1];
						if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc]) continue;
						if (map[nr][nc] == 0) ++cnt;
					}
					map[i][j] -= cnt;
					if (map[i][j] < 0) map[i][j] = 0;
				}
			}
		}
	}
	static void dfs(int r, int c) {
		visit[r][c] = true;
		for (int i = 0; i < 4; ++i) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] || map[nr][nc] == 0) continue;
			dfs(nr, nc);
		}
	}
}
