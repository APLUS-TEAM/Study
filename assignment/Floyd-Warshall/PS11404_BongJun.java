import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new int[n + 1][n + 1];
		for (int y = 0; y < n + 1; y++) {
			Arrays.fill(graph[y], Integer.MAX_VALUE);
			graph[y][y] = 0;
		}
		
		for (int y = 0; y < m; y++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from][to] = Math.min(graph[from][to], cost);
		}
		
		floyd_warshall();
		
		StringBuilder sb = new StringBuilder();
		for (int y = 1; y < n + 1; y++) {
			for (int x = 1; x < n + 1; x++) {
				if (graph[y][x] == Integer.MAX_VALUE ) {
					sb.append('0').append(' ');
					continue;
				}
				sb.append(graph[y][x]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static void floyd_warshall() {
		for (int i = 1; i <= n; i++) {
			for (int from = 1; from <= n; from++) {
				for (int to = 1; to <= n; to++) {
					if (graph[from][i] == Integer.MAX_VALUE || graph[i][to] == Integer.MAX_VALUE) {
						continue;
					}
					graph[from][to] = Math.min(graph[from][to], graph[from][i] + graph[i][to]);
				}
			}
		}
	}
}
