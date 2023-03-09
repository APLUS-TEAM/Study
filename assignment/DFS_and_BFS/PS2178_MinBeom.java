import java.util.*;
import java.io.*;
public class PS2178_MinBeom {
	static boolean[][] visit;
	static int[][] arr;
	static int[][] graph;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		arr = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String str = bf.readLine();
			for(int k=0; k<M; k++) {
				graph[i][k] = str.charAt(k) - '0';
			}
		}
		visit[0][0] = true;
		bfs(0,0);
		System.out.println(arr[N-1][M-1]);
	}
	
	static void bfs(int a, int b) {
		Queue<Integer> graphRow = new LinkedList<>();
		Queue<Integer> graphCol = new LinkedList<>();
		graphRow.add(a);
		graphCol.add(b);
		arr[a][b] = 1;
		while(!graphRow.isEmpty()) {
			int row = graphRow.poll();
			int col = graphCol.poll();
			if(row == N-1 && col == M-1) return;
			if(row+1<N && graph[row+1][col] != 0) {
				if(!visit[row+1][col]) {
					visit[row+1][col] = true;
					graphRow.add(row+1);
					graphCol.add(col);
					arr[row+1][col] += arr[row][col]+1;
				}
			}
			if(col+1<M && graph[row][col+1] != 0) {
				if(!visit[row][col+1]) {
					visit[row][col+1] = true;
					graphRow.add(row);
					graphCol.add(col+1);
					arr[row][col+1] += arr[row][col]+1;
				}
			}
			if(row-1>=0 && graph[row-1][col] != 0) {
				if(!visit[row-1][col]) {
					visit[row-1][col] = true;
					graphRow.add(row-1);
					graphCol.add(col);
					arr[row-1][col] += arr[row][col]+1;
				}
			}
			if(col-1>=0 && graph[row][col-1] != 0) {
				if(!visit[row][col-1]) {
					visit[row][col-1] = true;
					graphRow.add(row);
					graphCol.add(col-1);
					arr[row][col-1] += arr[row][col]+1;
				}
			}
		}
	}
}