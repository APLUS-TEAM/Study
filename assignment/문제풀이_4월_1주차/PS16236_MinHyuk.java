import java.io.*;
import java.util.*;
public class Baekjoon16236 {
	static int N, left, curR, curC, curSize, eatCnt, max;
	static int[][] map;
	static boolean[][] visit;
	static int[][] drdc = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static class Node {
		int r, c, dist;
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
		left = 0;
		eatCnt = 0;
		curSize = 2;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					map[i][j] = 0;
					curR = i;
					curC = j;
				} else if (map[i][j] > 0) ++left;
			}
		}
		int temp;
		while (left > 0) {
			temp = left;
			visit = new boolean[N][N];
			bfs();
			if (temp == left) break;
		}
		System.out.println(max);
	}
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(curR, curC, 1));
		visit[curR][curC] = true;
		int nextR = -1, nextC = -1, nextDist = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (nextDist < cur.dist) break;
			for (int i = 0; i < 4; ++i) {
				int nr = cur.r + drdc[i][0];
				int nc = cur.c + drdc[i][1];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || map[nr][nc] > curSize) continue;
				if (map[nr][nc] != 0 && map[nr][nc] < curSize) {
					if (nextR == -1 && nextC == -1) {
						nextR = nr;
						nextC = nc;
						nextDist = cur.dist;
					} else if (nextR > nr) {
						nextR = nr;
						nextC = nc;
					} else if (nextR == nr && nextC > nc) {
						nextR = nr;
						nextC = nc;
					}
				}
				q.offer(new Node(nr, nc, cur.dist+1));
				visit[nr][nc] = true;
			}
		}
		if (nextR != -1) {
			map[nextR][nextC] = 0;
			max += nextDist;
			curR = nextR;
			curC = nextC;
			--left;
			++eatCnt;
			if (eatCnt == curSize) {
				++curSize;
				eatCnt = 0;
			}
		}
	}
}