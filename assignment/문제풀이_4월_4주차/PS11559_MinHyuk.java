import java.io.*;
import java.util.*;
public class Baekjoon11559 {
	static char[][] map = new char[12][6];
	static int[][] drdc = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static boolean flag = true;
	static class Node {
		char ch;
		int r, c;
		public Node(char ch, int r, int c) {
			this.ch = ch;
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 12; ++i) map[i] = br.readLine().toCharArray();
		int cnt = 0;
		while (flag) {
			flag = false; // 터지는 뿌요가 하나라도 있으면 flag가 true로 바뀜
			for (int i = 0; i < 12; ++i)
				for (int j = 0; j < 6; ++j)
					if (map[i][j] != '.') {
						char[][] copyMap = copy(map);
						if (bfs(i, j, copyMap)) map = copyMap;
					}
			drop(map); // 한바퀴 돌면서 다 터트리고 떨굼
			++cnt;
		}
		// flag가 false인 상태로 끝나도(터지는 뿌요가 없더라도)
		// 한 번 더 cnt++하면서 while문이 끝나므로 cnt-1을 출력해줘야 함
		System.out.println(cnt-1); 
	}
	static boolean bfs(int r, int c, char[][] map) {
		int cnt = 1;
		boolean boomFlag = false;
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(map[r][c], r, c));
		map[r][c] = '.';
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (cnt >= 4) {
				boomFlag = true;
				flag = true;
			}
			for (int i = 0; i < 4; ++i) {
				int nr = node.r + drdc[i][0];
				int nc = node.c + drdc[i][1];
				if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6 || map[nr][nc] != node.ch) continue;
				q.offer(new Node(map[nr][nc], nr, nc));
				map[nr][nc] = '.';
				++cnt;
			}
		}
		return boomFlag;
	}
	static char[][] copy(char[][] map) {
		char[][] copyMap = new char[12][6];
		for (int i = 0; i < 12; ++i) copyMap[i] = map[i].clone();
		return copyMap;
	}
	static void drop(char[][] map) {
		for (int c = 0; c < 6; ++c) {
			Queue<Character> q = new LinkedList<>();
			int r = 12;
			while (--r >= 0) {
				if (map[r][c] != '.') {
					q.offer(map[r][c]);
					map[r][c] = '.';
				}
			}
			r = 12;
			while (!q.isEmpty()) map[--r][c] = q.poll();
		}
	}
}