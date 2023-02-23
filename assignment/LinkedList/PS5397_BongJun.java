package LinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class PS5397_BongJun_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			char[] input = br.readLine().toCharArray();
			int length = input.length;
			LinkedList<Character> front = new LinkedList<>();
			Stack<Character> back = new Stack<Character>();
			for (int i = 0; i < length; i++) {
				char target = input[i];
				switch (target) {
				case '<': {
					if (!front.isEmpty()) {
						back.add(front.removeLast());
					}
					break;
				}
				case '>': {
					if (!back.isEmpty()) {
						front.add(back.pop());
					}
					break;
				}
				case '-': {
					if (!front.isEmpty()) {
						front.removeLast();
					}
					break;
				}
				default: {
					front.add(target);
				}
				}
			}
			while (!front.isEmpty()) {
				sb.append(front.removeFirst());
			}
			while (!back.isEmpty()) {
				sb.append(back.pop());
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}