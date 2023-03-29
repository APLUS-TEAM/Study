import java.util.*;
import java.io.*;

public class Baekjoon1753 {
	static int V, E, target;
	static final int INF = Integer.MAX_VALUE;
	static class Node implements Comparable<Node> {
		int idx, cost;
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	static int[] arr;
	static boolean[] visit;
	static ArrayList<Node>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		graph = new ArrayList[V + 1];
		for (int i = 0; i <= V; ++i) graph[i] = new ArrayList<>();
		for (int i = 0; i < E; ++i) {
			st = new StringTokenizer(br.readLine());
			graph[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		dijkstra(start);
		for (int i = 1; i <= V; ++i) sb.append(arr[i]==INF?"INF":arr[i]).append('\n');
		System.out.println(sb);
	}
	static void dijkstra(int idx) {
		visit = new boolean[V + 1];
		arr = new int[V + 1];
		Arrays.fill(arr, INF);
		arr[idx] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(idx, 0));
		while (!pq.isEmpty()) {
			int nowIdx = pq.poll().idx;
			if (visit[nowIdx]) continue;
			visit[nowIdx] = true;
			for (Node next : graph[nowIdx]) {
				if (arr[next.idx] > arr[nowIdx] + next.cost) {
					arr[next.idx] = arr[nowIdx] + next.cost;
					pq.add(new Node(next.idx, arr[next.idx]));
				}
			}
		}
	}
}
