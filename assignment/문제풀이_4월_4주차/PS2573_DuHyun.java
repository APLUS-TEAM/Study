package day010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2573 {

	static class nojam {
		int x, y, height;

		public nojam(int x, int y, int height) {
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}

	static int N;
	static int M;
	static int cnt;
	static boolean[][] arr;
	static Queue<nojam> Q;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Q = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new boolean[N][M];
		cnt = 0; // 총 빙산개수
		int day = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int A = Integer.parseInt(st.nextToken());
				if (A != 0) {
					Q.offer(new nojam(i, j, A));
					arr[i][j] = true;
					cnt++;
				}
			}
		}

		while (Q.size() > 0) {
			bfs();
			day++;
			if (Q.size()==0) {
				day = 0;
				break;
			}
			if (check()) {
				break;
			}
		}

		System.out.println(day);

	}

	private static boolean check() {

		nojam temp = Q.peek();
		boolean[][] visited = new boolean[N][M];
		Queue<nojam> Q2 = new LinkedList<>();
		Q2.offer(temp);
		visited[temp.x][temp.y] = true;
		
		int cnt3 = 1;
		
		while (!Q2.isEmpty()) {
			
		    temp = Q2.poll();
			
			for (int i = 0; i < 4; i++) {
				int x = temp.x + dx[i];
				int y = temp.y + dy[i];
				if (0 <= x && x < N && 0 <= y && y < M && arr[x][y] && !visited[x][y]) {
					cnt3++;
					visited[x][y] = true;
					Q2.offer(new nojam(x, y, 0));
				}
			}
		}

		if (cnt3 == cnt) return false;
		else return true;

	}

	private static void bfs() {

		int size = Q.size();
		List<nojam> list = new LinkedList<>();

		for (int i = 0; i < size; i++) {
			nojam temp = Q.poll();
			int cnt2 = 0;

			for (int j = 0; j < 4; j++) {
				int x = temp.x + dx[j];
				int y = temp.y + dy[j];
				if (0 <= x && x < N && 0 <= y && y < M && !arr[x][y])
					cnt2++;
			}

			if ((temp.height - cnt2) > 0) {
				Q.offer(new nojam(temp.x, temp.y, temp.height - cnt2));
			} else {
				list.add(new nojam(temp.x, temp.y, 0));
				cnt--;
			}
		}

		for (nojam c : list) {
			arr[c.x][c.y] = false;
		}

	}

}
