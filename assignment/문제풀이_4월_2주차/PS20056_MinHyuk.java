import java.io.*;
import java.util.*;
public class Baekjoon20056 {
	static int N;
	static Queue<Fireball>[][] map;
	static class Fireball {
		int r, c, mass, speed, dir;
		boolean move;
		Fireball(int r, int c, int mass, int speed, int dir, boolean move) {
			this.r = r;
			this.c = c;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
			this.move = move;
		}
	}
	static int[][] drdc = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new LinkedList[N][N];
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				map[i][j] = new LinkedList<>();
		while (M-->0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int mass = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			map[r][c].offer(new Fireball(r, c, mass, speed, dir, false));
		}
		while (K-->0) {
			move();
			divide();
		}
		System.out.println(sum());
	}
	static int sum() {
		int massSum = 0;
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				while (!map[i][j].isEmpty())
					massSum += map[i][j].poll().mass;
		return massSum;
	}
	static void move() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				int size = map[i][j].size();
				while (size-->0) {
					Fireball cur = map[i][j].poll();
					if (cur.move) {
						map[i][j].offer(cur);
						continue;
					}
					cur.r = (cur.r + (cur.speed%N) * drdc[cur.dir][0] + N)%N;
					cur.c = (cur.c + (cur.speed%N) * drdc[cur.dir][1] + N)%N;
					cur.move = true;
					map[cur.r][cur.c].offer(cur);
				}
			}
		}
	}
	static void divide() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				int size = map[i][j].size();
				if (size <= 1) {
					if (size == 1) map[i][j].peek().move = false;
					continue;
				}
				int massSum = 0;
				int speedSum = 0;
				boolean oddFlag = true;
				boolean evenFlag = true;
				for (int k = 0; k < size; ++k) {
					Fireball cur = map[i][j].poll();
					massSum += cur.mass;
					speedSum += cur.speed;
					if ((cur.dir&1)==0) oddFlag = false;
					else evenFlag = false;
				}
				int newMass = massSum/5;
				if (newMass == 0) continue;
				int newSpeed = speedSum/size;
				if (oddFlag || evenFlag) 
					for (int dir = 0; dir <= 6; dir+=2) 
						map[i][j].offer(new Fireball(i, j, newMass, newSpeed, dir, false));
				else
					for (int dir = 1; dir <= 7; dir+=2)
						map[i][j].offer(new Fireball(i, j, newMass, newSpeed, dir, false));
			}
		}
	}
}