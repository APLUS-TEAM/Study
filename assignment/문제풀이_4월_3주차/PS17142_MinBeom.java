import java.util.*;
import java.io.*;
public class PS17142_MinBeom {
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] visit;
	static ArrayList<Node> graph = new ArrayList<>();
	static ArrayList<Integer> idx = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<>();
	static int min = Integer.MAX_VALUE;
	static int zeroCount = 0;
	static int[] dr = {-1, 1, 0 ,0};
	static int[] dc = {0, 0, -1 ,1};
	static class Node {
		int row, col;
		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2) graph.add(new Node(i,j));
				else if(arr[i][j] == 0) zeroCount++;
			}
		}
		dfs(0,0);
		if(zeroCount == 0) System.out.println(0);
		else if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min-1);
	}
	
	static void dfs(int index, int depth) {
		if(depth == M) {
			for(int i=0; i<M; i++) {
				visit = new boolean[N][N];
				q.offer(graph.get(idx.get(i)).row);
				q.offer(graph.get(idx.get(i)).col);
				visit[graph.get(idx.get(i)).row][graph.get(idx.get(i)).col] = true;
			}
			bfs();
			return;
		}
		for(int i=index; i<graph.size(); i++) {
			idx.add(i);
			dfs(i+1,depth+1);
			idx.remove(idx.size()-1);
		}
	}
	
	static void bfs() {
		int count = zeroCount;
		int[][] cost = new int[N][N];
		for(int i=0; i<N; i++) Arrays.fill(cost[i], 1);
		int max = 0;
		while(!q.isEmpty()) {
			int row = q.poll();
			int col = q.poll();
			for(int i=0; i<4; i++) {
				int dRow = row + dr[i];
				int dCol = col + dc[i];
				if(dRow <0 || dCol <0 || dRow>=N || dCol>=N || visit[dRow][dCol] || arr[dRow][dCol] == 1) continue;
				else if(arr[dRow][dCol] == 0) count--;
				visit[dRow][dCol] = true; 
				cost[dRow][dCol] = cost[row][col]+1;
				max = Math.max(max, cost[dRow][dCol]);
				q.offer(dRow);
				q.offer(dCol);
			}
			
			if(count == 0) {
				while(!q.isEmpty()) q.poll();
				break;
			}
		}
		if(count == 0) min = Math.min(min, max);
	}
}