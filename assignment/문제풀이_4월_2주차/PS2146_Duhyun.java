package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2146 {

	static int N;
	static int num; // 나라 번호
	static int min; // 최단 거리
	static boolean[][] map;
	static int[][] arr1; // 나라만 표시 2부터 시작~
	static boolean[][] visited1;// 나라 표시시 방문 표시
	static boolean[][] visited2;// 나라 표시시 방문 표시
	static Integer[][] arr2; // 나라마다 거리 표시
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Node2 implements Comparable<Node2>{
		int x, y, cost;
		public Node2(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node2 o) {
			return this.cost-o.cost;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 초기 지도 , 땅이면 true, 땅 아니면 false;
		map = new boolean[N][N];
		arr1 = new int[N][N];
		arr2 = new Integer[N][N];
		visited1 = new boolean[N][N];
		visited2 = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1)
					map[i][j] = true;
			}
		}

		num = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] && !visited1[i][j])
					bfs1(i, j);
			}
		}

		// 나라 표시 완료;
		min = 10000;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr1[i][j] >= 2 && !visited2[i][j])
					bfs2(i, j);
			}
		}
		
		System.out.println(min);

	}

	private static void bfs2(int i, int j) {
		PriorityQueue<Node2> PQ = new PriorityQueue<>(); 
		PQ.offer(new Node2(i, j , 0));
		visited2[i][j] = true;
		arr2[i][j] = 0;
		while (!PQ.isEmpty()) {
			Node2 temp = PQ.poll();
			for (int k=0;k<4;k++) {
				int x = temp.x + dx[k];
				int y = temp.y + dy[k];
				
				if (0 <= x && x < N && 0 <= y && y < N) {
					// 물 만날때 
					if (!map[x][y]) {
						int cost = temp.cost+1;
						if (arr2[x][y]==null||arr2[x][y]>cost) {
							arr2[x][y] = cost;
							PQ.offer(new Node2(x,y,cost));
						}
					} else if (arr1[x][y]==arr1[i][j]&&!visited2[x][y]) { // 같은 나라일 때 
						arr2[x][y] = 0;
						visited2[x][y] = true;
						PQ.offer(new Node2(x,y,0));
					} else if (arr1[x][y]!=arr1[i][j]) { // 다른 나라일 때 
						min = Math.min(min, temp.cost);
					}
				} 
			}

		}

	}

	private static void bfs1(int i, int j) {
		Queue<Node> Q = new LinkedList<>();
		Q.offer(new Node(i, j));
		visited1[i][j] = true;
		arr1[i][j] = num;
		while (!Q.isEmpty()) {
			Node temp = Q.poll();
			for (int k = 0; k < 4; k++) {
				int x = temp.x + dx[k];
				int y = temp.y + dy[k];

				if (0 <= x && x < N && 0 <= y && y < N && map[x][y] && !visited1[x][y]) {
					visited1[x][y] = true;
					arr1[x][y] = num;
					Q.offer(new Node(x, y));
				}
			}
		}
		num++;
	}

}
