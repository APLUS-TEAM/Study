import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class PS24479_MinBeom {
	static ArrayList<Integer>[] graph;
	static boolean[] visit;
	static int[] arr;
	static int count = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int node = Integer.parseInt(st.nextToken());
		int line = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		graph = new ArrayList[node+1];
		visit = new boolean[node+1];
		arr = new int[node+1];
		for(int i=1; i<=node; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i=0; i<line; i++) {
			st = new StringTokenizer(bf.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			graph[parent].add(child);
			graph[child].add(parent);
		}
		for(int i=1; i<=node; i++) {
			Collections.sort(graph[i]);
		}
		visit[start] = true;
		dfs(start);
		for(int i=1; i<=node; i++) {
			sb.append(arr[i]).append('\n');
		}
		System.out.println(sb);
	}
	static void dfs(int a) {
		arr[a] = ++count;
		for(int child : graph[a]) {
			if(!visit[child]) {
				visit[child] = true;
				dfs(child);
			}
		}
	}
}