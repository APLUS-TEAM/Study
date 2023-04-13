package algoStudy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class PS11000_JiHyeon {
	static class Node {
		int start;
		int end;
		
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "Node [start=" + start + ", end=" + end + "]";
		} //확인용
		
	}
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Node[] arr = new Node[N];
		
		for(int i=0; i<N; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			arr[i] = new Node(start, end); //배열에 노드 하나씩
		}
		
		Arrays.sort(arr, new Comparator<Node>() { //컴패래터 활용해서 시작시간 빠른 순서대로 정렬

			public int compare(Node o1, Node o2) {
				return o1.start - o2.start;
			}
			
		});
		
		PriorityQueue<Integer> q = new PriorityQueue<>(); //우선순위큐 숫자 작은것부터 위로
		q.add(arr[0].end); //첫번째 강의(시작시간이 가장 빠른) 것의 끝시간을 큐에 넣고
		
		for(int i = 1; i<N; i++) {
			if(arr[i].start<q.peek()) {//그시간이 다음의 시작보다 빠르면 
				q.add(arr[i].end); //다음 시간의 끝나는 시간을 큐에 넣고
			}else {q.poll(); q.add(arr[i].end);}
		}
		
		System.out.println(q.size());
		
	}
	
}