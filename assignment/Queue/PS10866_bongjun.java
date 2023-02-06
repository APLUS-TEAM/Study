package ps10866;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStreamReader a = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(a);
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder str = new StringBuilder();
		Deque dq = new Deque();

		int N = Integer.parseInt(st.nextToken());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case ("push_back"): {
				int num = Integer.parseInt(st.nextToken());
				dq.push_back(num);
				break;
			}
			case ("push_front"): {
				int num = Integer.parseInt(st.nextToken());
				dq.push_front(num);
				break;
			}
			case ("pop_front"): {
				str.append(dq.pop_front()).append("\n");
				break;
			}
			case ("pop_back"): {
				str.append(dq.pop_back()).append("\n");
				break;
			}
			case ("size"): {
				str.append(dq.size()).append("\n");
				break;
			}
			case ("empty"): {
				str.append(dq.empty()).append("\n");
				break;
			}
			case ("front"): {
				str.append(dq.front()).append("\n");
				break;
			}
			case ("back"): {
				str.append(dq.back()).append("\n");
				break;
			}
			}
		}
		System.out.println(str);
	}

	static class Deque {
		LinkedList<Integer> box = new LinkedList<>();

		public void push_front(int num) {
			box.add(0, num);
		}

		public void push_back(int num) {
			box.add(num);
		}

		public void print() {
			for (Integer a : box) {
				System.out.println(a);
			}
		}

		public int pop_front() {
			if (box.isEmpty()) {
				return -1;
			}
			return box.removeFirst();
		}

		public int pop_back() {
			if (box.isEmpty()) {
				return -1;
			}
			return box.removeLast();
		}

		public int size() {
			return box.size();
		}

		public int empty() {
			if (box.isEmpty()) {
				return 1;
			}
			return 0;
		}

		public int front() {
			if (box.isEmpty()) {
				return -1;
			}
			return box.getFirst();
		}

		public int back() {
			if (box.isEmpty()) {
				return -1;
			}
			return box.getLast();
		}
	}
}
