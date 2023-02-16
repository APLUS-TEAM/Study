package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon12865 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] WV = new int[N+1][N+1];
        int[] dp = new int[K+1];
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	WV[i][0] = Integer.parseInt(st.nextToken());
        	WV[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++)
        	for (int j = K; j >= WV[i][0] ; j--)
        		dp[j] = Math.max(dp[j], dp[j - WV[i][0]] + WV[i][1]);
        System.out.println(dp[K]);
	}
}