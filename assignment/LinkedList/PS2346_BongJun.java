package LinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS2346_BongJun {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		LinkedList linkedList = new LinkedList();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			linkedList.append(Integer.parseInt(st.nextToken()), i + 1);
		}
		int next = 1;
		int nextMove = 0;
		
		while (!linkedList.isEmpty()) {
			next = (next + nextMove) % linkedList.size;
			if (next == 0) { // 위치가 0 start가 아니라서 보정
				next = linkedList.size;
			} else if (next < 0) { // -방향 움직임 보정
				next += linkedList.size;
			}
			nextMove = linkedList.remove(next);
			if (nextMove > 0) {
				nextMove--;
			}
		}
		System.out.println(linkedList.sb);
	}
}

class LinkedList {
	Node head;
	Node tail;
	int size = 0;
	StringBuilder sb = new StringBuilder();
	
	void append(int input, int index) {
		Node newNode = new Node(input, index);
		size++;
		if (head == null) {
			head = newNode;
			tail = head;
			return ;
		}
		tail.next = newNode; // head.next = newNode인 상태
		newNode.next = head; // tail(head)-newNode -> tail(head)-newNode-head(tail)
		tail = newNode;	// head-tail-head		
	}
	
	void scan(int index) { // 디버깅용
		Node node = head;
		while (index-- > 0) {
			sb.append(node.data + " ");
			node = node.next;
		}
		System.out.println(sb);
	}
	
	int remove(int index) { // 메인문 구조상 size 이상의 remove할 경우가 없으므로 고려 x
		size--;
		Node temp = head;
		Node previous = null;
		if (size == 0) { // 1개 남았을때
			head = null;
			tail = null;
			sb.append(temp.originIndex).append(' ');
			return temp.data;
		}
		if (index == 1) { // index 1 제거 = 헤드 제거이므로 따로 처리
			head = temp.next;
			tail.next = head;
			sb.append(temp.originIndex).append(' ');
			return temp.data;
		}
		while (--index > 0) { // index 위치까지 밀어주기
			previous = temp;
			temp = temp.next;
		}
		if (temp == tail) { // 지울 위치가 tail일 경우 tail 설정
			tail = previous;
		}
		previous.next = temp.next;
		sb.append(temp.originIndex).append(' ');
		return temp.data;
	}
	
	boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
}

class Node {
	int data;
	int originIndex;
	Node next;
	
	Node(int input, int index) {
		data = input;
		originIndex = index;
	}
}
