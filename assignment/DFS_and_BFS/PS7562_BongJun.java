package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PS7562_BongJun {
	static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[][] visited;
	static int L;
	static Queue<Position> queue = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			L = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Position start = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			st = new StringTokenizer(br.readLine());
			Position goal = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			visited = new int[L][L];
			int count = BFS(start, goal);
//			if (testCase == 2) {
//			for (int y = 0; y < L; y++) {
//				System.out.println(Arrays.toString(visited[y]));
//			}}
			sb.append(count + "\n");
		}
		System.out.println(sb);
	}
	
	static int BFS(Position start, Position goal) {
		if (start.y == goal.y && start.x == goal.x) {
			return 0;
		}
		int count = 0;
		queue.add(start);
		visited[start.y][start.x] = 1; 
		while (!queue.isEmpty()) {
			Position current = queue.poll();
			for (int direction = 0; direction < 8; direction++) {
				int y = current.y + dy[direction];
				int x = current.x + dx[direction];
				if (y == goal.y && x == goal.x) {
					queue.clear();
					return current.depth + 1;
				}
				// dy dx 순서에 따라 달라질 수 있으니까 depth 비교해서 넣어주는 작업이 있어야 할 것 같음
				if (isInBoundary(y, x) && canVisit(y, x, current.depth + 1)) {
					queue.add(new Position(y, x, current.depth + 1));
					visited[y][x] = 1;
				}
			}
		}
		queue.clear();
		return count;
	}
	
	static boolean isInBoundary(int y, int x) {
		if (0 <= y && 0 <= x && y < L && x < L) {
			return true;
		}
		return false;
	}
	
	static boolean canVisit(int y, int x, int depth) {
		if (visited[y][x] == 0) {
			return true;
		}
		if (visited[y][x] > depth) {
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
