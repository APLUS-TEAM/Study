package DFSBFS;

import java.io.*;
import java.util.*;

public class PS1260_BongJun {
	static int N, M, V;
	static ArrayList<Integer>[] tree;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			tree[node1].add(node2);
			tree[node2].add(node1);
		}
		
		for (int i = 1; i <= N; i++) {
			tree[i].sort(Comparator.naturalOrder());
		}
		visited = new boolean[N + 1];
		DFS(V);
		visited = new boolean[N + 1];
		sb.append('\n');
		BFS(V);
		System.out.println(sb);
	}
	
	static void DFS(int node) {
		sb.append(node).append(' ');
		
		visited[node] = true;
		
		for (Integer n : tree[node]) {
			if (visited[n] == true) {
				continue;
			}
			visited[n] = true;
			DFS(n);
		}
	}
	
	static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			sb.append(node).append(' ');
			
			for (Integer next : tree[node]) {
				if (visited[next] == true) {
					continue;
				}
				queue.add(next);
				visited[next] = true;
			}
		}
	}
}
