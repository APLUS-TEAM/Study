package baekjoon;

import java.util.*;
import java.io.*;
public class PS10819_MinBeom {
	static int[] graph;
	static int[] arr;
	static boolean[] visit;
	static int max = 0;
	static int sum = 0;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		graph = new int[N+1];
		visit = new boolean[N+1];
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(max);
	}
	static void dfs(int a, int num) {
		if(a == N) {
			max = sum>max ? sum : max;
			return;
		}
		for(int i=0; i<N; i++) {
			if(a == 0) {
				visit[i] = true;
				dfs(a+1,i);
				visit[i] = false;
			}
			else if(!visit[i]) {
				sum += (int)Math.abs(arr[num]-arr[i]);
				visit[i] = true;
				dfs(a+1, i);
				sum -= (int)Math.abs(arr[num]-arr[i]);
				visit[i] = false;
			}
		}
	}
}