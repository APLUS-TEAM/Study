package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon2346 {
	static class LinkedList {
		// ù��° ��带 ����Ű�� �ʵ�
		private Node head;
		private Node tail;
		private int size = 0;
		private class Node {
			private Object data; // �����Ͱ� ����� �ʵ�
			private Node next; // ���� ��带 ����Ű�� �ʵ�
			public Node(Object input) {
				this.data = input;
				this.next = null;
			}
		}
		public void add(Object input) {
			Node newNode = new Node(input); // ��� ����
			// ������Ʈ�� 0�̶�� ù ��带 �߰���
			if (size == 0) {
				newNode.next = head; // ���ο� ����� ���� ���� ��带 ����
				head = newNode; // ���� ���ο� ��� ����
				size++; // ������Ʈ ���� 1 ����
				if (head.next == null) tail = head;
			} else {
				tail.next = newNode; // ������ ����� ���� ���� ������ ��� ����
				tail = newNode; // ������ ��� ����
				size++; // ������Ʈ ���� 1 ����
			}
		}
		Node node(int index) {
			Node x = head;
			while (index-->0) x = x.next;
			return x;
		}
		public Object remove(int k) {
			// ù ��带 ������ ��
			if (k == 0) {
				Node temp = head; // ù ��带 temp�� �����ϰ� head ���� �ι�° ���� ����
				head = temp.next;
				Object returnData = temp.data; // �����͸� �����ϱ� �� ������ ���� �ӽ� ������ ����
				temp = null;
				size--;
				return returnData;
			}
			Node temp = node(k-1); // k-1��° ��带 temp�� ������ ����
			Node todoDeleted = temp.next; // ���� ��带 todoDeleted�� ���
			temp.next = temp.next.next; // ���� �� ����� ���� ���� ���� ���� ��带 ����
			Object returnData = todoDeleted.data; // �����͸� �����ϱ� �� ������ ���� �ӽ� ������ ����
			if (todoDeleted == tail) tail = temp;
			todoDeleted = null; // cur.next ����
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
