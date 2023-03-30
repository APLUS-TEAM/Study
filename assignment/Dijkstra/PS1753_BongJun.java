import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
		static int V, E, INF = Integer.MAX_VALUE;
		static boolean[] visited;
		static int[] minDistance;
		static ArrayList<Node>[] nodeList;
		static PriorityQueue<Node> queue = new PriorityQueue<>();

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(br.readLine());

			nodeList = new ArrayList[V + 1];

			for (int i = 0; i <= V; i++) {
				nodeList[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());
				nodeList[from].add(new Node(to, distance));
			}

			visited = new boolean[V + 1];
			minDistance = new int[V + 1];

			for (int i = 1; i <= V; i++) {
				minDistance[i] = INF;
			}

			dijkstra(start);

			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= V; i++) {
				if (minDistance[i] == INF) {
					sb.append("INF");
				} else {
					sb.append(minDistance[i]);
				}
				sb.append('\n');
			}
			System.out.println(sb);
		}

		static void dijkstra(int start) {
			// 가장 짧은 노드 찾기
			for (Node nextNode : nodeList[start]) {
				if (minDistance[nextNode.index] > nextNode.distance) {
					minDistance[nextNode.index] = nextNode.distance;
				}
				queue.add(nextNode);
			}
			visited[start] = true;
			minDistance[start] = 0;
			while (!queue.isEmpty()) {
				start = queue.poll().index;
				if (visited[start]) {
					continue;
				}
				visited[start] = true;

				for (Node nextNode : nodeList[start]) {
					if (!visited[nextNode.index] && minDistance[nextNode.index] > nextNode.distance + minDistance[start]) {
						minDistance[nextNode.index] = nextNode.distance + minDistance[start];
						queue.add(new Node(nextNode.index, minDistance[nextNode.index]));
					}
				}
			}
		}

		static int shortestNode() {
			int start = 0;
			int min = INF;
			for (int i = 1; i <= V; i++) {
				if (!visited[i] && min > minDistance[i]) {
					start = i;
					min = minDistance[i];
				}
			}
			return start;
		}

		static class Node implements Comparable<Node>{
			int index, distance;

			Node(int index, int distance) {
				this.index = index;
				this.distance = distance;
			}

			@Override
			public int compareTo(Node o) {
				return this.distance - o.distance;
			}
		}
	}
