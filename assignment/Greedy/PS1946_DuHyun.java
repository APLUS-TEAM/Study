package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS1946_DuHyun {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc=0;tc<T;tc++) {
			
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			
			for (int i=0;i<N;i++) {
				StringTokenizer st= new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				arr[x] = y;
				
			}
			
			int check = arr[1];
			int cnt = 1;
			
			for (int i =2;i<N+1;i++) {
				
				if (arr[i]<check) {
					check = arr[i];
					cnt++;
				}
			}
			sb.append(cnt+"\n");
		}
		System.out.println(sb);
	}

}