package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2178 {

	// 오,아,왼,위
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static Integer[][] arr;
	static int min;
	static boolean[][] visited;
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new Integer[N + 1][M + 1];
		min = N * M;
		visited = new boolean[N + 1][M + 1];

		for (int i = 1; i < N + 1; i++) {
			String s = br.readLine();
			for (int j = 1; j < M + 1; j++) {
				arr[i][j] = s.charAt(j-1)-'0';
			}
		}

		bfs(1,1);
		System.out.println(arr[N][M]);

	}

	// 밑에는 dfs로 푼것 당연히 시간 초과 나옴 
//	static void dfs(int x, int y, int k) {
//		
//		
//
//		if (x == N && y == M) {
//			min = Math.min(min, k);
//			return;
//		}
//		
//		for (int i = 0; i < 4; i++) {
//
//			if (1 <= x + dx[i] && x + dx[i] <= N && 1 <= y + dy[i] && y + dy[i] <= M
//				&& arr[x+dx[i]][y+dy[i]]=='1'&&!visited[x + dx[i]][y + dy[i]]) {
//				
//				visited[x + dx[i]][y + dy[i]] = true;
//				bfs(x + dx[i], y + dy[i], k + 1);
//				visited[x + dx[i]][y + dy[i]] = false;
//			}
//
//		}
//	}

	static void bfs(int x,int y) {
		
		Queue<Integer[]> Q = new LinkedList<Integer[]>();
		Integer[] temp = new Integer[2];
		temp[0] = x;
		temp[1] = y;
		Q.offer(temp);
		int cnt=0;
		
		while (!Q.isEmpty()) {
			Integer[] temp2 = Q.poll();
			for (int i=0;i<4;i++) {
				int nextx = temp2[0] +dx[i]; 
				int nexty = temp2[1] +dy[i]; 
				
				if (nextx<1||N<nextx||nexty<1||M<nexty) {
					continue;
				}
				
				if (arr[nextx][nexty]!=0&&!visited[nextx][nexty]) {
					Integer[] temp3 = new Integer[2];
					temp3[0] = nextx;
					temp3[1] = nexty;
					Q.offer(temp3);
					visited[nextx][nexty]= true;
					arr[nextx][nexty] = arr[temp2[0]][temp2[1]]+1;
				}
				
			}
			
		}
		
	}
	
	
}
