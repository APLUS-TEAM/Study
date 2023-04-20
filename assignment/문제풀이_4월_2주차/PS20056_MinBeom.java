import java.util.*;
import java.io.*;

public class PS20056_MinBeom {
	static int N, M, K;
	static class Node {
		int m, d, s;

		public Node(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static ArrayList<Node>[][] graph;
	static int[][] arr;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = new ArrayList<>();
			}
		}
		arr = new int[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			graph[row][col].add(new Node(m, s, d));
			arr[row][col]++;
		}
		while (K-- > 0) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] != 0) move(i, j);
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (graph[i][j].size() >= 2) func(i, j);
					arr[i][j] = graph[i][j].size();
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0) sum += graph[i][j].get(0).m*arr[i][j];
			}
		}
		System.out.println(sum);
	}

	static void move(int row, int col) {
		int num = arr[row][col];
		while(num --> 0) {
			Node now = graph[row][col].get(0);
			int dRow = (row + (N*now.s) + (dr[now.d] * now.s)) % N;
			int dCol = (col + (N*now.s) + (dc[now.d] * now.s)) % N;
			graph[row][col].remove(0);
			arr[row][col]--;
			graph[dRow][dCol].add(new Node(now.m, now.s, now.d));
		}
	}
	static void func(int row, int col) {
		boolean dir = false;		// 전부 홀수나 짝수일시 false 아닐시 true
		int num = 0;
		int mSum = 0;
		int sSum = 0;
		for(int i=0; i<graph[row][col].size(); i++) {
			if(dir) dir=true;
			else if(i >= 1) dir = num != graph[row][col].get(i).d%2 ? true : false;
			else num = graph[row][col].get(i).d%2;
			mSum += graph[row][col].get(i).m;
			sSum += graph[row][col].get(i).s;
		}
		mSum /= 5;
		sSum /= graph[row][col].size();
		num = graph[row][col].size();
		while(num -->0) graph[row][col].remove(0);
		if(mSum == 0) return;
		for(int i=0; i<8; i++) {
			if(!dir && i%2==0) graph[row][col].add(new Node(mSum,sSum,i));
			else if(dir && i%2==1) graph[row][col].add(new Node(mSum,sSum,i));
		}
	}
}