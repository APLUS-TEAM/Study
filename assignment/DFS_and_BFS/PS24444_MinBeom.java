import java.util.*;
import java.io.*;
public class PS24444_MinBeom {
	static boolean visit[];
	static ArrayList<Integer>[] graph;
	static int[] arr;
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
		bfs(start);
		for(int i=1; i<=node; i++) {
			if(visit[i]) sb.append(arr[i]).append('\n');
			else sb.append(0).append('\n');
		}
		System.out.println(sb);
		
	}
	static void bfs(int a) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(a);
		int count = 0;
		while(!queue.isEmpty()) {
			a = queue.poll();
			arr[a] = ++count;
			for(int num : graph[a]) {
				if(!visit[num]) {
					visit[num] = true;
					queue.add(num);
				}
			}
		}
	}
}