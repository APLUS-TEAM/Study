import java.io.*;
import java.util.*;
public class Baekjoon17142 {
	static int N, M, blankCnt, min;
	static int[][] map;
	static Node[] onVirus;
	static ArrayList<Node> virusList = new ArrayList<>();
	static int[][] delta = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static class Node {
		int r, c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) ++blankCnt;
				else if (map[i][j] == 2) virusList.add(new Node(i, j));
			}
		}
		if (blankCnt == 0) System.out.println(0);
		else {
			min = Integer.MAX_VALUE;
			onVirus = new Node[M];
			combination(0, 0, virusList.size());
			System.out.println(min==Integer.MAX_VALUE?-1:min);
		}
	}
	static void combination(int idx, int depth, int size) {
		if (idx == M) {
			bfs();
			return;
		}
		for (int i = depth; i < size; ++i) {
			onVirus[idx] = virusList.get(i);
			combination(idx+1, i+1, size);
		}
	}
	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		for (Node node : onVirus) {
			visit[node.r][node.c] = true;
			q.offer(node);
		}
		int cnt = 0;
		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-->0) {
				Node cur = q.poll();
				for (int i = 0; i < 4; ++i) {
					int nr = cur.r + delta[i][0];
					int nc = cur.c + delta[i][1];
					if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 1 || visit[nr][nc]) continue;
					if (map[nr][nc] == 0) ++cnt;
					visit[nr][nc] = true;
					q.offer(new Node(nr, nc));
				}
			}
			if (min <= ++time) return;
			if (cnt == blankCnt) {
				min = time;
				return;
			}
		}
	}
}