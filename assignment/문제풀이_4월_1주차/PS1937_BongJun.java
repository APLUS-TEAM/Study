import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[][] dp;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][N];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (dp[y][x] != 0) continue;
				result = Math.max(result, dfs(y, x));
			}
		}
		
		System.out.println(result);
	}

	private static int dfs(int startY, int startX) {
		dp[startY][startX] = 1;
		
		for (int d = 0; d < 4; d++) {
			int y = startY + dy[d];
			int x = startX + dx[d];
			
			if (!isInArray(y, x) || map[startY][startX] <= map[y][x]) continue;
			if (dp[y][x] == 0) {
				dp[y][x] = dfs(y, x);
			}
			dp[startY][startX] = Math.max(dp[startY][startX], dp[y][x] + 1);
		}
		
		return dp[startY][startX];
	}

	private static boolean isInArray(int y, int x) {
		if (0 <= x && 0 <= y && x < N && y < N) {
			return true;
		}
		return false;
	}
}
