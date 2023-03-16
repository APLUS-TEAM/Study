package DFSBFS;

import java.io.*;
import java.util.*;

public class PS7576_BongJun {
	static int N, M, totalTomato, days;
	static int[][] map;
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static Queue<Tomato> agedTomato = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		totalTomato = N * M;
		map = new int[N][M];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == -1) {
					totalTomato--;
				} else if (value == 1) {
					totalTomato--;
					agedTomato.add(new Tomato(y, x, 0));
				}
				map[y][x] = value;
			}
		}
		
		if (totalTomato == 0) {
			System.out.println(0);
			System.exit(0);
		}
		
		agingDays();
		
		if (totalTomato != 0) {
			days = -1;
		}
		
		System.out.println(days);
	}
	
	private static void agingDays() {
		while (!agedTomato.isEmpty()) {
			Tomato tomato = agedTomato.poll();
			
			for (int direction = 0; direction < 4; direction++) {
				int y = tomato.y + dy[direction];
				int x = tomato.x + dx[direction];
				if (0 > y || 0 > x || y == N || x == M) {
					continue;
				}
				if (map[y][x] == 0) {
					agedTomato.add(new Tomato(y, x, tomato.days + 1));
					days = tomato.days + 1;
					map[y][x] = 1;
					totalTomato--;
				}
			}
		}
	}

	static class Tomato {
		int y, x, days;
		Tomato(int y, int x, int days) {
			this.y = y;
			this.x = x;
			this.days = days;
		}
	}
}
