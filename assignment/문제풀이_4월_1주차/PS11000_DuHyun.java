package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PS11000_DuHyun {
	
	static class Node implements Comparable<Node> {
		int start, end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Node o) {
			if (this.start==o.start) {
				return this.end-o.end;
			}
			return this.start-o.start;
		}

	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Node> Q = new PriorityQueue<>(); 
		PriorityQueue<Integer> lecture = new PriorityQueue<>(); 
		lecture.offer(0);
		int min = 0;
		
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			Q.offer(new Node(A,B));
		}
		
		
		while(!Q.isEmpty()) {
			
			Node temp = Q.poll();
			
			if (min<=temp.start) {
				lecture.poll();
				lecture.offer(temp.end);
				min = lecture.peek();
				
			} else {
				lecture.offer(temp.end);
				min = lecture.peek();
			}
			
		}
		
		System.out.println(lecture.size());
		
	}

}