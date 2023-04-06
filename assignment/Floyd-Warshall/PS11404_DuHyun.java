package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11404 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] dist = new int[N+1][N+1];
		final int inf = 1000000000;
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				if (i!=j) dist[i][j] = inf;
			}
		}
		
		for (int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			// 왕복 
			dist[a][b] = Math.min(dist[a][b], cost);
		}
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				for (int k=1;k<=N;k++) {
					dist[j][k] = Math.min(dist[j][k], dist[j][i]+dist[i][k]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				if (dist[i][j]!=inf) {
					sb.append(dist[i][j]).append(" ");
				} else {
					sb.append(0).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}

}