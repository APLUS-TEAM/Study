package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PS5397_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		DoublyLinkedList list = new DoublyLinkedList();
		int testCase =  Integer.parseInt(bf.readLine());
		char[][] arr = new char[testCase][];
		for(int i=0; i<testCase; i++) {
			arr[i] = bf.readLine().toCharArray();
		}
		for(int i=0; i<testCase; i++) {
			int rear = 0;
			for(int k=0; k<arr[i].length; k++) {
				if(list.size() == 0) {
					if(arr[i][k] == '<' || arr[i][k] == '>' || arr[i][k] == '-') continue;
					else {
						list.add(rear, arr[i][k]);
//						list.add(arr[i][k]);
						rear++;
					}
				}
				else if(arr[i][k] == '<') {
					if(rear == 0) continue;
					else rear--;
				}
				else if(arr[i][k] == '>') {
					if(rear == list.size()) continue;
					else rear++;
				}
				else if(arr[i][k] == '-') {
					if(rear == 0) continue;
					else {
						rear--;
						list.remove(rear);
					}
				}
				else {
					list.add(rear, arr[i][k]);
					rear++;
				}
			}
			while(list.size() != 0){
				sb.append(list.get(0));
				list.removeFirst();
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}

class DoublyLinkedList {
	private Node head;
	private Node tail;
	private int size = 0;

	class Node {
		private Object data;
		private Node next;
		private Node prev;

		Node(Object input) {
			this.data = input;
			this.next = null;
			this.prev = null;
		}
	}

	void addFirst(Object input) {
		Node newNode = new Node(input);
		newNode.next = head;
		if (head != null) {
			head.prev = newNode;
		}

		head = newNode;
		size++;
		if (head.next == null) {
			tail = head;
		}
	}

	void addLast(Object input) {
		Node newNode = new Node(input);
		if (size == 0) {
			addFirst(input);
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			size++;
		}
	}

	Node node(int index) {
		if (index < size / 2) {
			Node x = head;
			for (int i = 0; i < index; i++) {
				x = x.next;
			}
			return x;
		} else {
			Node x = tail;
			for (int i = size - 1; i > index; i--) {
				x = x.prev;
			}
			return x;
		}
	}

	void add(int k, Object input) {
		if (k == 0) {
			addFirst(input);
		} else {
			Node temp1 = node(k - 1);
			Node temp2 = temp1.next;
			Node newNode = new Node(input);
			temp1.next = newNode;
			newNode.next = temp2;
			if (temp2 != null) {
				temp2.prev = newNode;
			}
			newNode.prev = temp1;
			size++;
			if (newNode.next == null) {
				tail = newNode;
			}
		}
	}

	public Object removeFirst() {
		// 첫번째 노드를 temp로 지정하고 head의 값을 두번째 노드로 변경합니다.
		Node temp = head;
		head = temp.next;
		// 데이터를 삭제하기 전에 리턴할 값을 임시 변수에 담습니다.
		Object returnData = temp.data;
		temp = null;
		// 리스트 내에 노드가 있다면 head의 이전 노드를 null로 지정합니다.
		if (head != null)
			head.prev = null;
		size--;
		return returnData;
	}

	public Object remove(int k) {
		if (k == 0)
			return removeFirst();
		// k-1번째 노드를 temp로 지정합니다.
		Node temp = node(k - 1);
		// temp.next를 삭제하기 전에 todoDeleted 변수에 보관합니다.
		Node todoDeleted = temp.next;
		// 삭제 대상 노드를 연결에서 분리합니다.
		temp.next = temp.next.next;
		if (temp.next != null) {
			// 삭제할 노드의 전후 노드를 연결합니다.
			temp.next.prev = temp;
		}
		// 삭제된 노드의 데이터를 리턴하기 위해서 returnData에 데이터를 저장합니다.
		Object returnData = todoDeleted.data;
		// 삭제된 노드가 tail이었다면 tail을 이전 노드를 tail로 지정합니다.
		if (todoDeleted == tail) {
			tail = temp;
		}
		// cur.next를 삭제 합니다.
		todoDeleted = null;
		size--;
		return returnData;
	}

	public Object removeLast() {
		return remove(size - 1);
	}

	public int size() {
		return size;
	}

	public Object get(int k) {
		Node temp = node(k);
		return temp.data;
	}

	public int indexOf(Object data) {
		// 탐색 대상이 되는 노드를 temp로 지정합니다.
		Node temp = head;
		// 탐색 대상이 몇번째 엘리먼트에 있는지를 의미하는 변수로 index를 사용합니다.
		int index = 0;
		// 탐색 값과 탐색 대상의 값을 비교합니다.
		while (temp.data != data) {
			temp = temp.next;
			index++;
			// temp의 값이 null이라는 것은 더 이상 탐색 대상이 없다는 것을 의미합니다.이 때 -1을 리턴합니다.
			if (temp == null)
				return -1;
		}
		// 탐색 대상을 찾았다면 대상의 인덱스 값을 리턴합니다.
		return index;
	}

}
