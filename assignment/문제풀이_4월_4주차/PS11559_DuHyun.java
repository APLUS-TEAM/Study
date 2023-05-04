package day010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main11559 {
	
	static char[][] arr;
	static boolean[][] visited;
	static int chain;
	static int boom;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	static class nojam{
		int x,y;
		public nojam(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[12][6];
		chain = 0;

		for (int i = 0; i < 12; i++) arr[i] = br.readLine().toCharArray();
		
		while (true) {
			boom = 0;
			visited = new boolean[12][6];
			for (int i=0;i<12;i++) {
				for (int j=0;j<6;j++) {
					if (arr[i][j]!='.'&&!visited[i][j]) bfs(i,j);					
				}
			}
			
			if (boom>0) {
				chain++;
				clear();
			}
			if (boom==0) break;
		}
		
		System.out.println(chain);

	}

	private static void clear() {
		
		for (int i=10;i>=0;i--) {
			for (int j=0;j<6;j++) {
				if (arr[i][j]!='.'&&arr[i+1][j]=='.') {
					
					int index = 1;
					while (i+index<12&&arr[i+index][j]=='.') {
						index++;
					}
					char temp = arr[i][j];
					arr[i+index-1][j] = temp;
					arr[i][j] = '.';					
				}
			}
		}
		
	}

	private static void bfs(int a, int b) {
		
		int cnt = 1;
		Queue<nojam> Q = new LinkedList<>();
		Q.offer(new nojam(a,b));
		visited[a][b] = true;
		List<nojam> list = new ArrayList<>();
		list.add(new nojam(a,b));
		
		while (!Q.isEmpty()) {
			
			nojam temp = Q.poll();
			for (int i=0;i<4;i++) {
				
				int x = temp.x+dx[i];
				int y = temp.y+dy[i];
				
				if (0<=x&&x<12&&0<=y&&y<6&&!visited[x][y]&&arr[x][y]==arr[a][b]) {
					visited[x][y] = true;
					cnt++;
					list.add(new nojam(x,y));
					Q.offer(new nojam(x,y));
				}
			}
		}
		
		if (cnt>=4) {
			boom++;
			for (nojam c: list) {
				arr[c.x][c.y] = '.';
			}
		} 
	}
	
}
