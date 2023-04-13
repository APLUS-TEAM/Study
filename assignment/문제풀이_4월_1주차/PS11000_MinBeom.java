import java.util.*;
import java.io.*;
public class PS11000_MinBeom {
	static class Node implements Comparable<Node>{
		int start, end;
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public int compareTo(Node o) {
			if(this.start != o.start) return Integer.compare(this.start, o.start);
			else return Integer.compare(this.end, o.end);
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N =Integer.parseInt(bf.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Node[] arr = new Node[N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			Node now = new Node(start, end);
			arr[i] = now;
		}
		Arrays.sort(arr);
		for(int i=0; i<N; i++) {
			if(pq.isEmpty()) pq.offer(arr[i].end);
			else if(pq.peek() <= arr[i].start) {
				pq.poll();
				pq.offer(arr[i].end);
			}
			else pq.offer(arr[i].end);
		}
		System.out.println(pq.size());
	}
}