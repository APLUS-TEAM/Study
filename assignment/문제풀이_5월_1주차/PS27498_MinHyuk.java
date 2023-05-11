import java.io.*;
import java.util.*;
public class PS27498_MinHyuk {
	static int N;
	static int[] p;
	static class Node implements Comparable<Node> {
		int x, y, love;
		Node(int x, int y, int love) {
			this.x = x;
			this.y = y;
			this.love = love;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.love, this.love);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		makeset();
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int total = 0;
		while (M-->0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int love = Integer.parseInt(st.nextToken());
			boolean isComplete = st.nextToken().equals("1");
			if (isComplete) union(findset(x), findset(y));
			else {
				total += love;
				pq.offer(new Node(x, y, love));
			}
		}
		int sum = 0;
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int x = findset(cur.x);
			int y = findset(cur.y);
			if (x != y) {
				sum += cur.love;
				union(x, y);
			}
		}
		System.out.println(total-sum);
	}
	static void makeset() {
		p = new int[N+1];
		for (int i = 1; i <= N; ++i) p[i] = i;
	}
	static void union(int x, int y) {
		p[y] = x;
	}
	static int findset(int x) {
		if (x != p[x]) p[x] = findset(p[x]);
		return p[x];
	}
}