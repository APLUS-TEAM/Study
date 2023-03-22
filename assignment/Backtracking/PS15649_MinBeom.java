package baekjoon;

import java.io.*;
import java.util.*;

public class PS15649_MinBeom {
	static int[] arr;
	static boolean[] visit;
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		visit = new boolean[10];
		dfs(0);
		System.out.println(sb);
	}
	static void dfs(int a) {
		if(a == M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		else {
			for(int i=1; i<=N; i++) {
				if(!visit[i]) {
					arr[a] = i;
					visit[i] = true;
					dfs(a+1);
					arr[a] = 0;
					visit[i] = false;
				}
			}
		}
	}
}