import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] company;
	static ArrayList<Node>[] stations;
	static final int COMPANY = 0, TIME = 1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		company = new int[N];
		stations = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			company[i] = Integer.parseInt(br.readLine());
			stations[i] = new ArrayList<>();
		}
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				int value = Integer.parseInt(st.nextToken());
				if (value == 0) {
					continue;
				}
				stations[y].add(new Node(x, company[x], value));
				stations[x].add(new Node(y, company[y], value));
			}
		}
		
		Node result = dijkstra(0, M);
		System.out.printf("%d %d", result.transferCount, result.time);
	}
	
	private static Node dijkstra(int start, int goal) {
		int[][] nodeWeight = new int[N][2];
		for (int i = 0; i < N; i++) {
			Arrays.fill(nodeWeight[i], Integer.MAX_VALUE);
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0, 0));
		
		while (!pq.isEmpty()) {
			Node current = pq.poll();
			if (current.index == goal) {
				return current;
			}
			
			int index = current.index;
			int transferCount = current.transferCount;
			int time = current.time;

			if (transferCount > nodeWeight[index][COMPANY]) {
				continue;
			}
			
			for (Node next : stations[index]) {
				int countSum = transferCount + (company[index] ^ company[next.index]);
				int timeSum = time + next.time;
				if (countSum > nodeWeight[next.index][COMPANY]) {
					continue;
				}
				if (timeSum < nodeWeight[next.index][TIME]) {
					pq.add(new Node(next.index, countSum, timeSum));
					nodeWeight[next.index][COMPANY] = countSum;
					nodeWeight[next.index][TIME] = timeSum;
				}
			}
		}
		return null;
	}

	static class Node implements Comparable<Node> {
		int transferCount, index, time;
		
		Node(int index, int transferCount, int time) {
			this.transferCount = transferCount;
			this.index = index;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			if (this.transferCount == o.transferCount) {
				return this.time - o.time;
			} else {
				return this.transferCount - o.transferCount;
			}
		}
	}
}
