import java.io.*;
import java.util.*;
public class Main {
	static int N, M, V;
	static boolean[] visit;
	static boolean[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		arr = new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[x][y] = arr[y][x] = true;
		}
		visit = new boolean[N+1];
		dfs(V);
		sb.append('\n');
		visit = new boolean[N+1];
		bfs(V);
		sb.append('\n');
		System.out.println(sb);
	}
	static void dfs(int V) {
		visit[V] = true;
		sb.append(V).append(' ');
		for (int i = 1; i <= N; i++)
			if (!visit[i] && arr[V][i])
				dfs(i);
	}
	static void bfs(int V) {
		Queue<Integer> Q = new LinkedList<>();
		visit[V] = true;
		Q.offer(V);
		while (!Q.isEmpty()) {
			int temp = Q.poll();
			sb.append(temp).append(' ');
			for (int i = 1; i <= N; i++)
				if (!visit[i] && arr[temp][i]) {
					visit[i] = true;
					Q.offer(i);
				}
		}
	}
}