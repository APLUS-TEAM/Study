import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static char[][] map;
	static int[][] countMap;
	static int N;
	static int[] dy = {-1, 0, 1 ,0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		countMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			Arrays.fill(countMap[i], 2501);
		}
		
		dijkstra();
		if (N == 1) {
			countMap[0][0] = 0;
		}
		System.out.println(countMap[N - 1][N - 1]);
	}
	
	private static void dijkstra() {
		int count = 0;
		PriorityQueue<Node> queue = new PriorityQueue<>();
		
		queue.add(new Node(0, 0, 0));
		
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			
			if (current.y == N - 1 && current.x == N - 1) {
				break;
			}
			
			if (countMap[current.y][current.x] < current.count) {
				continue;
			}
			
			for (int direction = 0; direction < 4; direction++) {
				int y = current.y + dy[direction];
				int x = current.x + dx[direction];
				
				if (!isInArray(y, x)) {
					continue;
				}
				if (map[y][x] == '1') {
					if (countMap[y][x] > current.count) {
						queue.add(new Node(y, x, current.count));
						countMap[y][x] = current.count;
					}
				} else {
					if (countMap[y][x] > current.count + 1) {
						countMap[y][x] = current.count + 1;
						queue.add(new Node(y, x, current.count + 1));
					}
				}
			}
		}
	}

	private static boolean isInArray(int y, int x) {
		if (0 <= y && 0 <= x && y < N & x < N) {
			return true;
		}
		return false;
	}

	static class Node implements Comparable<Node>{
		int y, x, count;
		
		Node(int y, int x, int count) {
			this.y = y;
			this.x = x;
			this.count = count;
		}

		@Override
		public int compareTo(Node o) {
			return this.count - o.count;
		}
	}
}
