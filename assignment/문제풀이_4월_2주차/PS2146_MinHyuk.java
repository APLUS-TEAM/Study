import java.io.*;
import java.util.*;
public class Baekjoon2146 {
	static int[][] map;
	static boolean[][] visit;
	static boolean[] landVisit;
	static int N, min = Integer.MAX_VALUE;
	static int[][] drdc = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static class Node {
		int r, c, dist;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		Node(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) map[i][j] = Integer.parseInt(st.nextToken());
		}
		int num = 1;
		visit = new boolean[N][N];
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				if (map[i][j] != 0 && !visit[i][j]) numbering(i, j, num++);
		landVisit = new boolean[num+1];
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				if (map[i][j] != 0 && !landVisit[map[i][j]]) {
					landVisit[map[i][j]] = true;
					visit = new boolean[N][N];
					bfs(i, j);
				}
		System.out.println(min);
	}
	static void numbering(int r, int c, int num) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c));
		map[r][c] = num;
		visit[r][c] = true;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; ++i) {
				int nr = cur.r + drdc[i][0];
				int nc = cur.c + drdc[i][1];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 0 || visit[nr][nc]) continue;
				q.offer(new Node(nr, nc));
				map[nr][nc] = num;
				visit[nr][nc] = true;
			}
		}
	}
	static void bfs(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c, 0));
		visit[r][c] = true;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.dist >= min) continue;
			for (int i = 0; i < 4; ++i) {
				int nr = cur.r + drdc[i][0];
				int nc = cur.c + drdc[i][1];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) continue;
				if (map[nr][nc] == 0) {
					q.offer(new Node(nr, nc, cur.dist+1));
					visit[nr][nc] = true;
				} else if (map[nr][nc] == map[r][c]) {
					q.offer(new Node(nr, nc, 0));
					visit[nr][nc] = true;
				} else {
					min = cur.dist;
				}
			}
		}
	}
}