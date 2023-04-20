import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int N, M, K, maxSpeed;
	static final int MASS = 0, COUNT = 1, D = 2, D_SUM = 3, S = 4;
	static int[][][] map;
	static List<Ball> balls = new ArrayList<>();
	static Set<Integer> positions = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		maxSpeed = 1000 - (1000 % N) + N;
		map = new int[N][N][5];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			balls.add(new Ball(Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}
		
		while (K-- > 0) {
			simulation();
			makeFireball();
		}
		
		int result = 0;
		for (Ball curr : balls) {
			result += curr.m;
		}
		
		System.out.println(result);
	}
	
	private static void makeFireball() {
		for (Integer position : positions) {
			int y = position / 100;
			int x = position % 100;
			if (map[y][x][COUNT] == 1) {
				balls.add(new Ball(y, x, map[y][x][MASS], map[y][x][S], map[y][x][D]));
				clearMap(y, x);
				continue;
			}
			
			int nextMass = map[y][x][MASS] / 5;
			if (nextMass == 0) {
				clearMap(y, x);
				continue;
			}
			int nextSpeed = map[y][x][S] / map[y][x][COUNT];
			int d = 1;
			if (isDirectionsSameMod(map[y][x][D_SUM], map[y][x][D])) {
				d = 0;
			}
			for (; d < 8; d += 2) {
				balls.add(new Ball(y, x, nextMass, nextSpeed, d));
			}
			clearMap(y, x);
		}
		positions.clear();
	}

	private static void clearMap(int y, int x) {
		for (int i = 0; i < 5; i++) {
			map[y][x][i] = 0;
		}
	}
	
	private static void simulation() {
		for (Ball curr : balls) {
			int y = (curr.y + dy[curr.d] * curr.s + maxSpeed) % N;
			int x = (curr.x + dx[curr.d] * curr.s + maxSpeed) % N;
			
			map[y][x][MASS] += curr.m;
			map[y][x][D] = curr.d;
			map[y][x][COUNT]++;
			map[y][x][D_SUM] |= 1 << curr.d;
			map[y][x][S] += curr.s;
			positions.add(y * 100 + x);
		}
		
		balls.clear();
	}

	private static boolean isDirectionsSameMod(int sum, int d) {
		for (int i = (d + 1) % 2; i < 8; i += 2) {
			int direction = 1 & (sum >> i);
			if (direction == 1) {
				return false;
			}
		}
		return true;
	}

	static class Ball {
		int m, s, d;
		int y, x;
		public Ball(int y, int x, int m, int s, int d) {
			this.y = y;
			this.x = x;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
