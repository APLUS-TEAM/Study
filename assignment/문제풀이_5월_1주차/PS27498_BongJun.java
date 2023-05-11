import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;
	static int[][] edges;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		int total = 0;
		edges = new int[M][4];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				edges[i][j] = Integer.parseInt(st.nextToken());
			}
			total += edges[i][2];
		}
		
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[3] == o2[3]) {
					return o2[2] - o1[2];
				}
				return o2[3] - o1[3];
			}
		});
		
		int longestTree = 0;
		int count = 0;
		for (int i = 0; count < N - 1 && i < M; i++) {
			int parentA = findSet(edges[i][0]);
			int parentB = findSet(edges[i][1]);
			if (parentA == parentB) continue;
			parent[parentA] = parentB;
			longestTree += edges[i][2];
		}
		
		System.out.println(total - longestTree);
	}
	private static int findSet(int i) {
		if (i != parent[i]) {
			parent[i] = findSet(parent[i]);
		}
		return parent[i];
	}
}