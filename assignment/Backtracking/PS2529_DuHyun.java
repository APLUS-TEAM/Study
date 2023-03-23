package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2529 {

	static int N;
	static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	static boolean[] visited;
	static int[] maxarr;
	static int[] minarr;
	static String[] arr;
	static long max;
	static long min;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = br.readLine().split(" "); // N개 나온다.
		StringBuilder sb = new StringBuilder();
		maxarr = new int[N + 1];
		minarr = new int[N + 1];
		max = 0;
		min = 9999999999l;
		
		visited = new boolean[10];
		defmax(0);
		visited = new boolean[10];
		defmin(0);
		
		sb.append(max).append('\n');

		if (min/(int)Math.pow(10, N)==0) {
			sb.append("0");
		}
		
		sb.append(min);
		
		System.out.println(sb);

	}

	static void defmax(int a) {
		
		for (int i=9-N;i<=9;i++) {
			if (!visited[i]) {
				visited[i] = true;
				maxarr[a] = num[i];
				defmax(a+1);
				visited[i] = false;
			}
			
		}
		
		if (a == N + 1) {

			if (possibility(maxarr)) {
				long temp =0l; 
				for (int i=0;i<=N;i++) {
					temp+=maxarr[i]*Math.pow(10, N-i);
				}
				
				max = Math.max(max, temp);
				
			}
			return;
		}
		
	}

	static void defmin(int a) {
		
		for (int i=0;i<=N;i++) {
			if (!visited[i]) {
				visited[i] = true;
				minarr[a] = num[i];
				defmin(a+1);
				visited[i] = false;
			}
			
		}
		
		if (a == N + 1) {

			if (possibility(minarr)) {
				long temp =0; 
				for (int i=0;i<=N;i++) {
					temp+=minarr[i]*Math.pow(10, N-i);
				}
				
				min = Math.min(min, temp);
				
			}
			return;
		}
		
		

	}

	static boolean possibility(int[] result) {

		boolean check = true;

		for (int i = 0; i < N; i++) {

			if (arr[i].equals(">")) {
				if (result[i] > result[i + 1])
					continue;
				else {
					check = false;
					break;
				}
			} else {
				if (result[i] < result[i + 1])
					continue;
				else {
					check = false;
					break;
				}
			}

		}
		
		return check;

	}

}