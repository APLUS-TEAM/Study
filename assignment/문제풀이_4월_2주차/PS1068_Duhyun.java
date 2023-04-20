package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main1068 {
	
	static int N;
	static List<Integer>[] list;
	static int cnt = 0;
	static boolean[] visited;
	static int delete;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = 0;
		list = new List[N];
		visited = new boolean[N];
		
		for (int i=0;i<N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i=0;i<N;i++) {
			int A = Integer.parseInt(st.nextToken());
			if (A==-1) {
				start = i;
			}  else {
				list[A].add(i);
			}
		}
		
		delete = Integer.parseInt(br.readLine());
		
		dfs(start);
		
		System.out.println(cnt);
		
	}

	private static void dfs(int A) {
		
		visited[A] = true;
		
		if (A==delete) {
			return;
		}
		
		if (list[A].size()==0) {
			cnt++;
			return;
		}
		
		if (list[A].size()==1&&list[A].get(0)==delete) {
			cnt++;
			return;
		}
		
		for (int c: list[A]) {
			if (!visited[c]) {
				dfs(c);
			}
		}
	}

}
