package 스터디;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
// i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
// 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

public class PS1753JH {
	static int V, E, K;
	static boolean[] visit;
	static int[] dist;
	static ArrayList<Node>[] graph;
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

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt(); // 정점 수
		E = sc.nextInt(); // 간선 수
		K = sc.nextInt(); // 시작 정점

		graph = new ArrayList[V + 1];

		for (int i = 0; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			int node = sc.nextInt();
			int idx = sc.nextInt();
			int cost = sc.nextInt();

			graph[node].add(new Node(idx, cost));
		}
		
		dik(K);
		
		for(int i = 1; i<=V; i++ ) {
			if(dist[i]==INF) System.out.println("INF");
			else System.out.println(dist[i]);
		}
		
}

	static void dik(int idx) {
		visit = new boolean[V+1];
		dist = new int[V+1];
		Arrays.fill(dist, INF);
		dist[idx] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(idx,0));
		
		while(!pq.isEmpty()) {
			int nowIdx = pq.poll().idx;
			
			if(visit[nowIdx]) continue;
			
			visit[nowIdx] = true;
			
			for(Node next : graph[nowIdx] ) {
				if(dist[next.idx]>dist[nowIdx]+next.cost) {
					dist[next.idx] = dist[nowIdx]+next.cost;
					pq.add(new Node(next.idx,dist[next.idx]));
				}
			}
		}
		
	}

}
