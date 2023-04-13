package algoStudy;
import java.util.Scanner;

public class PS1937_JiHyeon {
	static int N;
	static int[][] arr;
	static int[][] delta = {{-1,0}, {1,0},{0,1},{0,-1}}; //상하우좌
	static boolean[][] visit;
	static int[][] dp;
	static int max;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		arr = new int[N][N];
		max = Integer.MIN_VALUE;
		dp = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dfs(i,j);
				}
			}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(dp[i][j], max);
			}
		}
		
		System.out.println(max);
	}

	private static int dfs(int r, int c) {
		if(dp[r][c]!=0) return dp[r][c];
		
		dp[r][c] = 1;
		
		for(int idx=0; idx<4; idx++) {
		 int nr = r + delta[idx][0];
		 int nc = c + delta[idx][1];
		 
		 if(nr>=0 && nc>=0 && nr<N && nc<N && arr[r][c] < arr[nr][nc]) {
			 dp[r][c] = Math.max(dp[r][c], dfs(nr,nc)+1);
		 }
		}//델타 포문
		
		return dp[r][c];
	}
}