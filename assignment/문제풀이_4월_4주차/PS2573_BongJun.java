import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, day;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static Queue<Node> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		day = 0;
		while (isSeparated()) {
			visited = new boolean[N][M];

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < M; x++) {
					if (map[y][x] == 0) {
						queue.add(new Node(y, x));
					}
				}
			}
			melt();
			day++;
		}
		System.out.println(day);
	}

	private static void melt() {
		while (!queue.isEmpty()) {
			Node current = queue.poll();

			for (int d = 0; d < 4; d++) {
				int y = current.y + dy[d];
				int x = current.x + dx[d];

				if (!isInArray(y, x) || map[y][x] == 0)	continue;
				map[y][x]--;
			}
		}
	}

	private static boolean isInArray(int y, int x) {
		return 0 <= y && 0 <= x && y < N && x < M;
	}

	private static boolean isSeparated() {
		int count = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 0 || visited[y][x])
					continue;
				check(y, x);
				count++;
			}
		}
		if (count == 0) {
			day = 0;
		}
		return count == 1 ? true : false;
	}

	private static void check(int Y, int X) {
//		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(Y, X));

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			for (int d = 0; d < 4; d++) {
				int y = current.y + dy[d];
				int x = current.x + dx[d];
				
				if (!isInArray(y, x) || visited[y][x] || map[y][x] == 0) continue;
				queue.add(new Node(y, x));
				visited[y][x] = true;
			}
		}
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}