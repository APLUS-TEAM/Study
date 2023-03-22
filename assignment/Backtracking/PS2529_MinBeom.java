package baekjoon;

import java.util.*;
import java.io.*;
public class PS2529_MinBeom {
	static int testCase;
	static long max = 0;
	static long min = Long.MAX_VALUE;
	static char[] arr;
	static int[] graph;
	static boolean[] visit;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		arr = new char[testCase];
		graph = new int[testCase+1];
		visit = new boolean[10];
		for(int i=0; i<testCase; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		dfs(0);
		sb.append(max).append('\n');
		if(min < Math.pow(10, testCase)) {
			sb.append(0);
		}
		sb.append(min);
		System.out.println(sb);
	}
	
	static void dfs(int a) {
		if(a == testCase+1) {
			long num = 0l;
			for(int i=0; i<=testCase; i++) {
				num += (long)(graph[i]*Math.pow(10, testCase-i));
			}
			max = max<num ? num : max;
			min = min>num ? num : min;
			return;
		}
		for(int i=0; i<=9; i++) {
			if(visit[i]) continue;
			if(a==0 || isTruth(a, i)){
				graph[a] = i;
				visit[i] = true;
				dfs(a+1);
				visit[i] = false;
			}
		}
	}
	static boolean isTruth(int depth, int num) {
		if(arr[depth-1] == '<') {
			if(graph[depth-1] < num) return true;
		}
		else if(arr[depth-1] == '>') {
			if(graph[depth-1] > num) return true;
		}
		return false;
	}
}