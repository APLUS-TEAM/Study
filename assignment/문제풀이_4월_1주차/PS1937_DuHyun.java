package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS1937_DuHyun {
	
	static int N;
	static int[][] arr;
	static int[][] dp;
	static int max;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[N][N];
		max = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝!
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				dfs(i,j);
			}
		}
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (dp[i][j]>max) max= dp[i][j];
			}
		}
		
		System.out.println(max);
	}
	
	static int dfs(int x, int y) {
		
		if (dp[x][y] !=0) return dp[x][y];
		
		for (int i=0;i<4;i++) {
			
			int A = x +dx[i];
			int B = y +dy[i];
			
			if (0<=A&&A<N&&0<=B&&B<N) {
				if (arr[x][y]<arr[A][B]) dp[x][y] = Math.max(dp[x][y],dfs(A,B)+1);
			} 
		}
		
		if (dp[x][y]==0) dp[x][y] = 1;
		
		return dp[x][y];
		
	}
}
