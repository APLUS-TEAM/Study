package day0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main17940 {

	static class train implements Comparable<train> {

		int index; // 지하철역 나타냄
		int transfer; // 환승 
		int cost; // 비용 // PQ에서는 최대 비용도 나타냄

		public train(int index,int transfer, int cost) {
			this.index = index;
			this.transfer = transfer;
			this.cost = cost;
		}

		@Override
		public int compareTo(train o) {
			if (this.transfer == o.transfer) {
				return this.cost - o.cost; // 환승횟수 같으면 비용이 더 적은걸로 ㄱㄱ
			}
			return this.transfer - o.transfer;
		}
	}
	
	static class traincost{
		
		int transfer; // 환승 
		int cost; // 비용 // PQ에서는 최대 비용도 나타냄
		
		public traincost(int transfer, int cost) {
			this.transfer = transfer;
			this.cost = cost;
		}

	}

	static final int inf = 100000000; // 넉넉히 1억;
	static int N;
	static int M;
	static List<train>[] list;
	static traincost[] result;
	static boolean[] visited;
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		boolean[] company = new boolean[N];

		for (int i = 0; i < N; i++) {
			if (Integer.parseInt(br.readLine()) == 1)
				company[i] = true;
		}

		list = new List[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {

				int A = Integer.parseInt(st.nextToken());
				if (A == 0) continue;
				else {
					if (company[i]==company[j]) list[i].add(new train(j,0,A)); // i-j까지 지하철 이용시 A만큼 비용듬.
					else list[i].add(new train(j,1,A));
				}
			}
		}
		
		result = new traincost[N];
		visited = new boolean[N];

		for (int i=0;i<N;i++) {
			result[i] = new traincost(inf,inf);
		}
		traincost answer = dijkstra(new train(0,0,0), M);
		
		System.out.println(answer.transfer+" "+answer.cost);
		
	}

	static traincost dijkstra(train start, int end) {
		
		PriorityQueue<train> pq = new PriorityQueue<>();
		pq.add(start);
		result[0] = new traincost(0,0);
				
		while (!pq.isEmpty()) {
			
			train temp = pq.poll();
			
			if (temp.index==end) break;
			if (!visited[temp.index]) {
				visited[temp.index] = true;
				
				for (train c: list[temp.index]) {
					
					if (result[c.index].transfer>result[temp.index].transfer+c.transfer) {
						result[c.index].transfer = result[temp.index].transfer+c.transfer;
						result[c.index].cost = result[temp.index].cost+c.cost;
						pq.offer(new train(c.index, result[c.index].transfer, result[c.index].cost));
						
					} else if (result[c.index].transfer==result[temp.index].transfer+c.transfer) {
						
						if (result[c.index].cost > result[temp.index].cost+c.cost) {
							result[c.index].cost = result[temp.index].cost+c.cost;
							pq.offer(new train(c.index, result[c.index].transfer, result[c.index].cost));
						}
					}
				}
			}
			
		}
		
		return result[end];
	}

}