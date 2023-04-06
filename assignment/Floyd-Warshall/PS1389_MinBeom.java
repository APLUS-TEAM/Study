import java.util.*;
import java.io.*;
public class PS1389_MinBeom {
	static int N;
	static int M;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		final int INF = 1000000000;
		int[][] dist = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i!=j) dist[i][j] = INF;
			}
		}
		int[] arr = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			dist[start][end] = dist[end][start] = 1;
		}
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				for(int k=1; k<=N; k++) {
					if(j == k) continue;
					dist[j][k] = Math.min(dist[j][k], dist[j][i]+dist[i][k]);
				}
			}
		}
		int min = INF;
		int minIdx = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i != j) arr[i] += dist[i][j];
			}
			if(min > arr[i]) {
				min = arr[i];
				minIdx = i;
			}
		}
		System.out.println(minIdx);
	}
}