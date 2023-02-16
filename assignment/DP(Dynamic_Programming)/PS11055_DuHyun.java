package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS11055_DuHyun {
	
	static int N;
	static int[] max;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		max = new int[N];
		for (int i=0; i<N;i++) {
			max[i]=0;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int result =0;
		
		for (int i=0; i<N;i++) {
			result = Math.max(result, sum(i,arr));
		}
		
		System.out.println(result);

	}

	static int sum(int a, int[] arr) {

		if (a == N - 1) {
			return arr[N - 1];
		}
		
		if (max[a]!=0) return max[a];

		else {
			// N-1까지 가거나 더큰거 발견
			max[a] = arr[a];
			for (int i = a + 1; i < N; i++) {
				
				if (arr[a]<arr[i]) {					
					max[a] = Math.max(max[a], arr[a]+sum(i, arr));
				} 
			}
			
			return max[a];
		}

	}


}
