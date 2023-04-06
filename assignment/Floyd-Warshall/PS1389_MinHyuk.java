import java.io.*;
import java.util.*;
public class Baekjoon1389 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		final int INF = 5001;
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				if (i != j) dist[i][j] = INF;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			dist[a][b] = dist[b][a] = 1;
		}
		for (int m = 0; m < N; ++m)
			for (int s = 0; s < N; ++s)
				for (int e = 0; e < N; ++e)
					dist[s][e] = Math.min(dist[s][e], dist[s][m] + dist[m][e]);
		int[] minNum = new int[N+1];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; ++i) {
            int sum = 0;
            for (int j = 0; j < N; ++j) sum += dist[i][j];
            minNum[i] = sum;
            min = Math.min(min, sum);
        }
        for (int i = 0; i < N; ++i)
            if (minNum[i] == min) {
                System.out.println(i+1);
                break;
            }
	}
}