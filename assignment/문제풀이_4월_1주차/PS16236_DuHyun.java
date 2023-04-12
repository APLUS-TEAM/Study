package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class PS16236_DuHyun {

	static int N;
	static int[][] arr;
	static int time;
	static int cnt;
	static int total;
	static int shark;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static class Node implements Comparable<Node> {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node o) {
			if (this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		Node start = null;
		time = 0; // 엄마 부르는 시간
		cnt = 0; // 상어가 먹어치운 물고기 수
		shark = 2; // 상어 크기

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) start = new Node(i, j);
			}
		}

		arr[start.x][start.y]=0;
		bfs(start);

		System.out.println(time);

	}

	private static void bfs(Node start) {
		
		if (check()) return;
		

		Queue<Node> Q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		List<Node> list = new ArrayList<>();
		Q.offer(start);
		visited[start.x][start.y] = true;
		int cnt2 = 0;

		while (!Q.isEmpty()) {

			if (!list.isEmpty())
				break;

			int size = Q.size();

			// 시간마다 BFS 진행
			cnt2++;
			for (int i = 0; i < size; i++) {
				Node temp = Q.poll();

				for (int j = 0; j < 4; j++) {

					int x = temp.x + dx[j];
					int y = temp.y + dy[j];

					if (0 <= x && x < N && 0 <= y && y < N && !visited[x][y] && arr[x][y] <= shark) {
						visited[x][y] = true;
						Q.offer(new Node(x, y));
						if (0 < arr[x][y] && arr[x][y] < shark)
							list.add(new Node(x, y));
					}

				}
			}

		}

		if (!list.isEmpty()) {
			time += cnt2;
			Collections.sort(list); // 리스트 정렬
			Node temp = list.get(0); // 가장 앞 list값 반환
			arr[temp.x][temp.y] = 0;
			cnt++;
			if (cnt == shark) {
				shark++;
				cnt = 0;
			}

			bfs(temp);

		}
		// 이렇게 해도 비었으면 걍 엄마 상어 호출
		
		

	}
	
	// 전체 배열 확인해서 하나라도 샤크보다 작은 물고기 있음 true
	public static boolean check() {
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            if (0<arr[i][j]&&arr[i][j] < shark) {  
	                return false;      
	            }
	        }
	    }
	    return true;  
	}

}