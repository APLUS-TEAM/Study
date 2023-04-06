package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1389 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 유저 수 
		int M = Integer.parseInt(st.nextToken()); // 친구 관계 수
		
		int[][] arr = new int[N+1][N+1];
		final int inf = 100000;
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				if (i!=j) arr[i][j] = inf;
			}
		}
		
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			arr[A][B] = 1;
			arr[B][A] = 1;
		}
		
		for (int i=1;i<=N;i++) {
			for (int j=1;j<=N;j++) {
				for (int k=1;k<=N;k++) {
					arr[j][k] = Math.min(arr[j][k], arr[j][i]+arr[i][k]);
				}
			}
		}
		
		int min = 10000000;
		int result = 0;
		
		for (int i=N;i>0;i--) {
			int sum = 0;
			for (int j=1;j<=N;j++) {
				sum+=arr[i][j];
			}
			min = Math.min(min, sum);
			if (min==sum) {
				result = i;
			}
		}
		
		System.out.println(result);
		
	}

}