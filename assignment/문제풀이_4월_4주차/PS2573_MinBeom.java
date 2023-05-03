import java.util.*;
import java.io.*;
public class PS2573_MinBeom {
	static int[][] arr;
	static boolean[][] visit;
	static int N,M;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static Queue<Integer> q = new LinkedList<>();
	static ArrayList<int []> graph = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] != 0) graph.add(new int[] {i,j});
			}
		}
		System.out.println(dfs(0));
	}
	static int dfs(int depth) {
		if(graph.size() == 0) return 0;
		visit = new boolean[N][M];
		int count = 0;
		for(int i=0; i<graph.size(); i++) {
			int row = graph.get(i)[0];
			int col = graph.get(i)[1];
			if(arr[row][col] == 0 || visit[row][col]) continue;
			count++;
			if(count > 1) return depth;
			q.add(row);
			q.add(col);
			visit[row][col] = true;
			bfs();
		}
		for(int i=0; i<graph.size(); i++) {
			int row = graph.get(i)[0];
			int col = graph.get(i)[1];
			if(arr[row][col] == 0) graph.remove(i);
		}
		return dfs(depth+1);
	}
	static void bfs() {
		int divNum = 0;
		while(!q.isEmpty()) {
			int row = q.poll();
			int col = q.poll();
			divNum = 0;
			for(int i=0; i<4; i++) {
				int dRow = row + dr[i];
				int dCol = col + dc[i];
				if(dRow <0 || dCol <0 || dRow >=N || dCol >= M || visit[dRow][dCol]) continue;
				if(arr[dRow][dCol] == 0) divNum++;
				else {
					q.add(dRow);
					q.add(dCol);
					visit[dRow][dCol] = true;
				}
			}
			if(arr[row][col] <= divNum) arr[row][col] = 0;
			else arr[row][col] -= divNum;
		}
	}
}