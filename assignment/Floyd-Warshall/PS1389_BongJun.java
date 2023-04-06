import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] edges;
	static final int MAX = Integer.MAX_VALUE / 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) { // 초기화
			Arrays.fill(edges[i], MAX);
			edges[i][i] = 0;
		}
		
		for (int y = 0; y < M; y++) { // 입력
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a][b] = 1;
			edges[b][a] = 1;
		}
		
		for (int n = 1; n <= N; n++) { // Floyd-Warshall
			for (int y = 1; y <= N; y++) {
				for (int x = 1; x <= N; x++) {
					edges[y][x] = Math.min(edges[y][x], edges[y][n] + edges[n][x]);
				}
			}
		}
		
		int min = MAX;
		int idx = 0;
		for (int n = 1; n <= N; n++) { // 최소합 찾기
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				sum += edges[n][i];
			}
			if (min > sum) {
				min = sum;
				idx = n;
			}
		}
		System.out.println(idx);
	}
}
