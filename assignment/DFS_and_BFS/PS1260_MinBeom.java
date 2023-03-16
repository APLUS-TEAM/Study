import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PS1260_MinBeom {
	static boolean[] visit;
	static int[][] graph;
	static int node, size, startNum;
	static Queue<Integer> queue = new LinkedList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		node = Integer.parseInt(st.nextToken());
		size = Integer.parseInt(st.nextToken());
		startNum = Integer.parseInt(st.nextToken());
		visit = new boolean[node+1];
		graph = new int[node+1][node+1];
		for(int i=0; i<size; i++) {
			st = new StringTokenizer(bf.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			graph[parent][child] = 1;
			graph[child][parent] = 1;
		}
		dfs(startNum);
		sb.append('\n');
		visit = new boolean[node+1];
		bfs(startNum);
		System.out.println(sb);
	}
	
	static void dfs(int a) {
		visit[a] = true;
		sb.append(a).append(' ');
		for(int i=0; i<=node; i++) {
			if(graph[a][i] == 1 && !visit[i]) dfs(i);
		}
	}
	static void bfs(int a) {
		queue.add(a);
		visit[a] = true;
		
		while(!queue.isEmpty()) {
			a= queue.poll();
			sb.append(a).append(' ');
			for(int i=1; i<=node; i++) {
				if(graph[a][i] == 1 && !visit[i]) {
					queue.add(i);
					visit[i] = true;
				}
			}
		}
	}
}