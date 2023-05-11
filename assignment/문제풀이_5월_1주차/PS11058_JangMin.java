package codeTest.baekjoon;

import java.util.Scanner;

public class Q11058 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 버튼 누르는 횟수
		
		long[] dp  = new long[N+1];
		
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i-1] + 1;
			
			for(int j = 1; j <= i-3; j++) {
				long temp = dp[i-(j+2)]*(j+1);
				dp[i] = Math.max(dp[i],  temp);
				
			}
		}
		
		System.out.println(dp[N]);
		
	}

}
