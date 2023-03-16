import java.io.*;
import java.util.*;
public class Main {
	static int N, M;
	static int[][] arr;
	static final int ROW = 0, COL = 1;
	static int[][] drdc = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Queue<int[]> Q = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) Q.offer(new int[] {i, j});
			}
		}
		bfs();
		int max = 0;
		boolean flag = true;
		outer: for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (arr[i][j] == 0) {
					flag = false;
					break outer;
				}
		if (flag)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					max = Math.max(max, arr[i][j]);
		System.out.println(max-1); 
	}
	static void bfs() {
		while (!Q.isEmpty()) {
			int[] cur = Q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[ROW] + drdc[i][ROW];
				int nc = cur[COL] + drdc[i][COL];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && arr[nr][nc] == 0) {
					arr[nr][nc] = arr[cur[ROW]][cur[COL]] + 1;
					Q.offer(new int[] {nr, nc});
				}
			}
		}
	}
}