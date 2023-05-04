package day010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17142 {

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int M;
	static int K;
	static int min;
	static int cnt;
	static int[][] map;
	static List<Node> virus = new ArrayList<>();
	static boolean[] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = 0;
		cnt = 0;
		min = 100000;
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int A = Integer.parseInt(st.nextToken());
				if (A == 2) {
					virus.add(new Node(i, j));
					K++; // 전체 바이러스 수
				} else if (A == 0)
					cnt++; // 감염 시켜야하는 숫자
				map[i][j] = A;
			}
		} // 입력 끝;
		visited = new boolean[K];

		// 재귀로 컴비네이션 선택
		bfs(0, 0);

		if (min == 100000)
			min = -1;
		System.out.println(min);

	}

	private static void bfs(int A, int B) {

		if (B == M) { // bfs 연산 진행 ㄱㄱ

			Queue<Node> Q = new LinkedList<>();
			int time = 0;
			int cnt2 = 0;
			boolean[][] arr = new boolean[N][N];

			for (int i = 0; i < K; i++) {
				if (visited[i]) {
					Q.offer(virus.get(i));
					arr[virus.get(i).x][virus.get(i).y] = true;
				}
			}

			while (!Q.isEmpty()) {
				if (cnt == cnt2)
					break;
				int size = Q.size();
				for (int i = 0; i < size; i++) {

					Node temp = Q.poll();

					for (int j = 0; j < 4; j++) {
						int x = temp.x + dx[j];
						int y = temp.y + dy[j];

						if (0 <= x && x < N && 0 <= y && y < N && map[x][y] != 1 && !arr[x][y]) {
							// 범위에 있고 벽이 아니면
							arr[x][y] = true;
							if (map[x][y] == 0)
								cnt2++;
							Q.offer(new Node(x, y));
						}

					}

				}
				time++; // 하루 경과
				if (time > min)
					return;
			}
			if (cnt2 == cnt)
				min = Math.min(time, min);
			return;
		} 
		
		if (A >= K) return;

		
		if (!visited[A]) {
			visited[A] = true;
			bfs(A + 1, B + 1);
			visited[A] = false;
			bfs(A + 1, B);
		}
	}
}
