import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7576 {

	static int[][] arr;
	static int N;
	static int M;
	static int cnt;
	static Queue<Point> Q;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int day = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		cnt = 0;
		int cnt2 = 0;
		Q = new LinkedList<Point>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					cnt++;
					Q.offer(new Point(i, j));
				} else if (arr[i][j] == -1) {
					cnt2++;
				}
			}
		}

		if (cnt == 0)
			System.out.println(-1);
		else if (cnt == N * M - cnt2) {
			System.out.println(0);
		} else {
			bfs();
			if (cnt == N * M - cnt2) {
				System.out.println(day-1);
			} else
				System.out.println(-1);
		}

	}

	static void bfs() {

		while (!Q.isEmpty()) {

			int A = Q.size();
			day++;

			for (int j = 0; j < A; j++) {
				Point p = Q.poll();

				for (int i = 0; i < 4; i++) {
					int x1 = p.x + dx[i];
					int y1 = p.y + dy[i];

					if (0 <= x1 && x1 < N && 0 <= y1 && y1 < M && arr[x1][y1] == 0) {
						Q.offer(new Point(x1, y1));
						arr[x1][y1] = 1;
						cnt++;
					}

				}
			}
		}

	}

}
