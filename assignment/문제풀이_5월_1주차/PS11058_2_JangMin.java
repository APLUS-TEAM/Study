package codeTest.baekjoon;

import java.util.Scanner;

public class Q11058_1 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 버튼 누르는 횟수
		
		long[] dp = new long[N+1];
		
		for(int i = 1; i <= Math.min(N, 6); i++) {
			dp[i] = i;
		}
		
		for(int i = 7; i <= N; i++) {
			for(int j = 2; j <= 5; j++) {
				dp[i] = Math.max(dp[i], dp[i-(j+1)]*j);
			}
		}
		
		System.out.println(dp[N]);
		
	}

}
