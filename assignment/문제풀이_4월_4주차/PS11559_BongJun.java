import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	static char[][] map = new char[12][6];
	static int result = 0;
	static boolean[][] visited = new boolean[12][6];
	static boolean boomFlag;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static Queue<int[]> targets = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		while (scan()) {
			arrange();
//			print();
//			System.out.println("정리후");
			result++;
		}

		System.out.println(result);
	}

	private static void print() {
		for (char[] a : map) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println(result);
	}

	private static boolean scan() {
		visited = new boolean[12][6];
		boomFlag = false;
		for (int x = 0; x < 6; x++) {
			if (visited[11][x] || map[11][x] == '.')
				continue;
			boomFlag |= dfs(11, x);
		}
//		print();
//		for(boolean[] a: visited) {
//			System.out.println(Arrays.toString(a));
//		}
		return boomFlag;
	}

	private static boolean dfs(int startY, int startX) {
		boolean boomFlag = false;
		Queue<Node> queue = new LinkedList<>();
		Node start = new Node(startY, startX);
		queue.add(start);
		visited[startY][startX] = true;
		Queue<Node> targets = new LinkedList<>();
		targets.add(start);
		while (!queue.isEmpty()) {
			Node current = queue.poll();

			for (int d = 0; d < 4; d++) {
				int y = current.y + dy[d];
				int x = current.x + dx[d];

				if (!isInArray(y, x) || visited[y][x] || map[startY][startX] != map[y][x])
					continue;
				visited[y][x] = true;
				Node next = new Node(y, x);
				queue.add(next);
				targets.add(next);
			}
		}

		int count = targets.size();
		if (count >= 4) {
			boomFlag = true;
		}
		while (!targets.isEmpty()) {
			Node next = targets.poll();
			if (count >= 4) {
				map[next.y][next.x] = '.';
			}
			for (int d = 0; d < 4; d++) {
				int y = next.y + dy[d];
				int x = next.x + dx[d];
				if (!isInArray(y, x) || visited[y][x] || map[y][x] == '.')
					continue;
				boomFlag |= dfs(y, x);
			}
		}
		return boomFlag;
	}

	private static boolean isInArray(int y, int x) {
		return 0 <= y && 0 <= x && y <= 11 && x <= 5;
	}

	private static void arrange() {
		for (int x = 0; x < 6; x++) {
			int y = 11;
			int arrangedY = 11;
			while (y >= 0 && map[y][x] == '.') {
				y--;
			}
			for (; y >= 0; y--) {
				if (map[y][x] == '.')
					continue;
				map[arrangedY][x] = map[y][x];
				if (arrangedY != y) {
					map[y][x] = '.';
				}
				arrangedY--;
			}
		}
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
}