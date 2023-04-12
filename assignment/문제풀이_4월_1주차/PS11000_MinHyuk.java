import java.io.*;
import java.util.*;
public class Baekjoon11000 {
	static class Class implements Comparable<Class> {
		int start, end;
		Class(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Class o) {
			if (this.start == o.start) return Integer.compare(this.end, o.end);
			return Integer.compare(this.start, o.start);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Class[] arr = new Class[N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(arr);
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(arr[0].end);
		for (int i = 1; i < N; ++i) {
			if (pq.peek() <= arr[i].start) pq.poll();
			pq.offer(arr[i].end);
		}
		System.out.println(pq.size());
	}
}