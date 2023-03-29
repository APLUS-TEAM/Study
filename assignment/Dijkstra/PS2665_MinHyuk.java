import java.util.*;
import java.io.*;

public class Baekjoon2665 {
	static int N, min;
	static boolean[][] arr, visit;
	static int[][] drdc = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static class Node implements Comparable<Node> {
		int r, c, changeCnt;
		public Node(int r, int c, int changeCnt) {
			this.r = r;
			this.c = c;
			this.changeCnt = changeCnt;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.changeCnt, o.changeCnt);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new boolean[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; ++i) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < N; ++j) arr[i][j] = temp[j] == '1';
		}
		min = Integer.MAX_VALUE;
		dijkstra();
		System.out.println(min);
	}
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visit[0][0] = true;
		pq.offer(new Node(0, 0, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			// 목적지 도달하면 탐색 끝
			// 처음으로 도착한 것이 우선순위큐 때문에 가장 적은 교환횟수를 가짐
			if (cur.r == N-1 && cur.c == N-1) {
				min = cur.changeCnt;
				return;
			}
			for (int i = 0; i < 4; ++i) {
				int nr = cur.r + drdc[i][0];
				int nc = cur.c + drdc[i][1];
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visit[nr][nc]) {
					visit[nr][nc] = true;
					// 흰방이면 바꾸지 않고 넘김
					if (arr[nr][nc]) pq.offer(new Node(nr, nc, cur.changeCnt));
					// 검은방이면 바꾸고 cnt+1해서 넘김
					else pq.offer(new Node(nr, nc, cur.changeCnt+1));
				}
			}
		}
	}
}
