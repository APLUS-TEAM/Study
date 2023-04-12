import java.io.*;
import java.util.*;
public class Baekjoon1937 {
	static int N, max;
	static int[][] map, dp;
	static int[][] drdc = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = 1;
			}
		}
		max = 1;
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				dfs(i, j);
		System.out.println(max);
	}
	static int dfs(int r, int c) {
		if (dp[r][c] != 1) return dp[r][c];
		for (int i = 0; i < 4; ++i) {
			int nr = r + drdc[i][0];
			int nc = c + drdc[i][1];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] <= map[r][c]) continue;
			dp[r][c] = Math.max(dp[r][c], dfs(nr, nc)+1);
		}
		max = Math.max(max, dp[r][c]);
		return dp[r][c];
	}
}