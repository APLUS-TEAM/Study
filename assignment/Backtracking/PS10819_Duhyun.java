package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10819 {
	
	static int N;
	static int max;
	static int[] arr;
	static boolean[] visited;
	static int[] newarr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		newarr = new int[N];
		visited = new boolean[N];
		
		max = 0;
		
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		def(0);
		
		System.out.println(max);
		
	}
	
	static void def(int a) {
		
		if (a==N) {
			sum(newarr);
			return;
		}
		
		for (int i=0;i<N;i++) {
			
			if (!visited[i]) {
				visited[i] = true;
				newarr[a] = arr[i];
				def(a+1);
				visited[i] = false;
			}
			
		}
		
		
	}
	
	static void sum(int[] arr) {
		
		int sum = 0;
		
		for (int i=0;i<N-1;i++) {
			sum+=Math.abs(newarr[i]-newarr[i+1]);
		}
		
		max = Math.max(max, sum);
		
	}

}