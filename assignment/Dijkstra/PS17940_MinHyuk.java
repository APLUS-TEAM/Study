import java.io.*;
import java.util.*;

public class Baekjoon17940_new {
	static int N, M, minTransfer, minCost;
	static int[] dist;
	static int[][] costs;
	static boolean[] company;
	static class Node implements Comparable<Node> {
		int idx, cost, transfer;
		public Node(int idx, int cost, int transfer) {
			this.idx = idx;
			this.cost = cost;
			this.transfer = transfer;
		}
		@Override
		public int compareTo(Node o) {
			return this.transfer==o.transfer?this.cost-o.cost:this.transfer-o.transfer;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		company = new boolean[N];
		for (int i = 0; i < N; ++i) company[i] = br.readLine().equals("0");
		costs = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) costs[i][j] = Integer.parseInt(st.nextToken());
		}
		dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		minTransfer = Integer.MAX_VALUE;
		minCost = Integer.MAX_VALUE;
		dijkstra();
		System.out.printf("%d %d", minTransfer, minCost);
	}
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));
		dist[0] = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.idx == M) {
				minTransfer = cur.transfer;
				minCost = cur.cost;
				return;
			}
			for (int i = 0; i < N; ++i) {
				if (costs[cur.idx][i] != 0 && dist[i] > cur.cost + costs[cur.idx][i]) {
					dist[i] = cur.cost + costs[cur.idx][i];
					if (company[i] == company[cur.idx]) pq.offer(new Node(i, dist[i], cur.transfer));
					else pq.offer(new Node(i, dist[i], cur.transfer+1));
				}
			}
		}
	}
}
