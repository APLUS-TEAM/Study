package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15723 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int inf = 1000000;
		int[][] arr = new int[26][26];
		
		for (int i=0;i<26;i++) {
			for (int j=0;j<26;j++) {
				if (i!=j) arr[i][j] = inf;
			}
		}
		
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = st.nextToken().charAt(0) -'a';
			String s = st.nextToken();
			int B = st.nextToken().charAt(0) -'a';
			arr[A][B] = 0;
		}
		
		for (int i=0;i<26;i++) {
			for (int j=0;j<26;j++) {
				for (int k=0;k<26;k++) {
					arr[j][k] = Math.min(arr[j][k], arr[j][i]+arr[i][k]);
				}
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		for (int i=0;i<M;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = st.nextToken().charAt(0) -'a';
			String s = st.nextToken();
			int B = st.nextToken().charAt(0) -'a';
			
			if (arr[A][B]==0) {
				sb.append("T").append("\n");
			} else {
				sb.append("F").append("\n");
			}
			
		}
		
		System.out.println(sb);
		
	}

}
