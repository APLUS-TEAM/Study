package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7576 {

	static int M;
	static int N;
	static int H;
	static int[][][] tomato;
	static boolean[][][] visited;
	static int day;
	static int result;
	static Queue<pointer> Q;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 세로(가로칸의 수)
		N = Integer.parseInt(st.nextToken()); // 가로(세로칸의 수)
		H = Integer.parseInt(st.nextToken()); // 상자의 수
		tomato = new int[N][M][H];
		visited = new boolean[N][M][H];
		day = -1;
		int cnt = 0; // 토마토 총 갯수
		result = 0;
		Q = new LinkedList<pointer>();

		for (int tc = 0; tc < H; tc++) {

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					tomato[i][j][tc] = Integer.parseInt(st.nextToken());
					if (tomato[i][j][tc] == 0 ) cnt++;
					if (tomato[i][j][tc] == 1) {
						cnt++;
						Q.offer(new pointer(i,j,tc));
					}
				}
			}

		}

		bfs();

		if (result == cnt)
			System.out.println(day);
		else
			System.out.println(-1);

	}
	
	static void bfs() {
		
		while (!Q.isEmpty()) {
			
			
			
			
		}
		
		
		
	}

}

class pointer {
	int x;
	int y;
	int z;

	pointer(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

}