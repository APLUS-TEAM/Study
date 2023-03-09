package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PS2178_BongJun {
	static int[][] map;
	static int N, M;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				map[y][x] = br.read() - '0';
			}
			br.readLine();
		}
		BFS(new Position(0, 0, 1));
		System.out.println(map[N - 1][M - 1]);
	}
	
	static void BFS(Position start) {
		Queue<Position> queue = new LinkedList<>();
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Position next = queue.poll();
			for (int direction = 0; direction < 4; direction++) {
				int y = next.y + dy[direction];
				int x = next.x + dx[direction];
				if (isInBoundary(y, x) && !isItVisited(y, x)) {
					queue.add(new Position(y, x, next.depth + 1));
					map[y][x] = next.depth + 1;
				}
			}
		}
	}
	static boolean isItVisited(int y, int x) {
		if (map[y][x] == 1) {
			return false;
		}
		return true;
	}
	
	static boolean isInBoundary(int y, int x) {
		if (0 <= y && 0 <= x && y < N && x < M) {
			return true;
		}
		return false;
	}
	
	static class Position {
		int y, x, depth;
		Position(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}
}
