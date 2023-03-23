package codeTest.baekjoon;

import java.util.Scanner;

public class Q15649 {
	
	static int N;
	static int M;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 1; i <= N; i++) {
			arr[i-1] = i;
		}
		int output[] = new int[arr.length];
		boolean visited[] = new boolean[arr.length];
		
		// N까지의 수 중에서 M개를 중복있게 뽑는다
		permutation(arr, output, visited, 0, M);
		
		
		
	}

	private static void permutation(int[] arr, int[] output, boolean[] visited, int depth, int M) {

		if(depth == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(output[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				output[depth] = arr[i];
				permutation(arr, output, visited, depth+1, M);
				visited[i] = false;
			}
			
			
		}
		
	}

}
