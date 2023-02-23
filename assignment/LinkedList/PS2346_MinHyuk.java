package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon2346 {
	static class LinkedList {
		// 첫번째 노드를 가리키는 필드
		private Node head;
		private Node tail;
		private int size = 0;
		private class Node {
			private Object data; // 데이터가 저장될 필드
			private Node next; // 다음 노드를 가리키는 필드
			public Node(Object input) {
				this.data = input;
				this.next = null;
			}
		}
		public void add(Object input) {
			Node newNode = new Node(input); // 노드 생성
			// 엘리먼트가 0이라면 첫 노드를 추가함
			if (size == 0) {
				newNode.next = head; // 새로운 노드의 다음 노드로 헤드를 지정
				head = newNode; // 헤드로 새로운 노드 지정
				size++; // 엘리먼트 개수 1 증가
				if (head.next == null) tail = head;
			} else {
				tail.next = newNode; // 마지막 노드의 다음 노드로 생성한 노드 지정
				tail = newNode; // 마지막 노드 갱신
				size++; // 엘리먼트 개수 1 증가
			}
		}
		Node node(int index) {
			Node x = head;
			while (index-->0) x = x.next;
			return x;
		}
		public Object remove(int k) {
			// 첫 노드를 삭제할 때
			if (k == 0) {
				Node temp = head; // 첫 노드를 temp로 지정하고 head 값을 두번째 노드로 변경
				head = temp.next;
				Object returnData = temp.data; // 데이터를 삭제하기 전 리턴할 값을 임시 변수에 담음
				temp = null;
				size--;
				return returnData;
			}
			Node temp = node(k-1); // k-1번째 노드를 temp의 값으로 지정
			Node todoDeleted = temp.next; // 삭제 노드를 todoDeleted에 기록
			temp.next = temp.next.next; // 삭제 앞 노드의 다음 노드로 삭제 뒤의 노드를 지정
			Object returnData = todoDeleted.data; // 데이터를 삭제하기 전 리턴할 값을 임시 변수에 담음
			if (todoDeleted == tail) tail = temp;
			todoDeleted = null; // cur.next 삭제
			size--;
			return returnData;
		}
		public int size() {
			return size;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		LinkedList ll = new LinkedList();
		for (int i = 1; i <= N; i++) ll.add(i);
		int idx = 0;
		while (ll.size() > 1) {
			int temp = (int) ll.remove(idx);
			sb.append(temp).append(' ');
			int move = arr[temp-1];
			if (move > 0) {
				idx = (--idx + move) % ll.size();
			} else {
				idx = (idx + move) % ll.size();
				if (idx < 0)
					idx += ll.size();
			}
		}
		sb.append(ll.remove(idx)).append(' ');
		System.out.println(sb);
	}
}
