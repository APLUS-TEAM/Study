import java.util.*;
import java.io.*;

public class PS7576_MinBeom {
	static int[][] tomato;
	static boolean[][] visit;
	static int N;
	static int M;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int max = 0;
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		tomato = new int[N][M];
		visit = new boolean[N][M];
		int startRow = 0;
		int startCol = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int k = 0; k < M; k++) {
				tomato[i][k] = Integer.parseInt(st.nextToken());
				if (tomato[i][k] == 1) {
					queue.add(i);
					queue.add(k);
					startRow = i;
					startCol = k;
				}
			}
		}
		visit[startRow][startCol] = true;
		bfs(startRow, startCol);
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (tomato[i][k] == 0) {
					max = -1;
					break;
				}
			}
		}
		if(max == -1) System.out.println(max);
		else if(max == 0) System.out.println(max);
		else System.out.println(max-1);
	}

	static void bfs(int a, int b) {
		int row = 0;
		int col = 0;
		int bRow = 0;
		int bCol = 0;
		while (!queue.isEmpty()) {
			row = queue.poll();
			col = queue.poll();
			for (int i = 0; i < 4; i++) {
				bRow = row + dr[i];
				bCol = col + dc[i];
				if (bRow >= N || bCol >= M || bRow < 0 || bCol < 0) continue;
				else {
					if (!visit[bRow][bCol] && tomato[bRow][bCol] == 0) {
						tomato[bRow][bCol] = tomato[row][col] + 1;
						max = tomato[bRow][bCol] > max ? tomato[bRow][bCol] : max;
						queue.add(bRow);
						queue.add(bCol);
						visit[bRow][bCol] = true;
					}
				}
			}
		}

	}
}