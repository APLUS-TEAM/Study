import java.util.*;
import java.io.*;
public class PS11404_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int M = Integer.parseInt(bf.readLine());
		int[][] arr = new int[N][N];
		final int INF = 1000000000;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i!=j) arr[i][j] = INF;
			}
		}
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			arr[start-1][end-1] = Math.min(arr[start-1][end-1], cost);
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					arr[j][k] = Math.min(arr[j][k], arr[j][i]+arr[i][k]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] == INF) sb.append(0).append(' ');
				else sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}