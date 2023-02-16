package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PS1874_MinBeom {
	private static Stack<Integer> stk = new Stack<>();
	private static int testNum;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb2 = new StringBuilder();
	private static int i = 1;

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		testNum = Integer.parseInt(bf.readLine());
		int targetNum = 0;
		for (int k = 0; k < testNum; k++) {
			if (sb2.toString().equals("NO")) {
				System.out.println(sb2.toString());
				break;
			}
			targetNum = Integer.parseInt(bf.readLine());
			stackNum(targetNum);
		}
		if (!sb2.toString().equals("NO")) {
			System.out.println(sb.toString());
		}
	}

	private static void stackNum(int targetNum) throws IOException {
		while (true) {
			if (stk.isEmpty()) {
				stk.push(i);
				i++;
				sb.append("+").append("\n");
			}

			else if (stk.peek() < targetNum) {
				stk.push(i);
				i++;
				sb.append("+").append("\n");
			}

			else if (stk.peek() == targetNum) {
				stk.pop();
				sb.append("-").append("\n");
				break;
			}

			else {
				sb2.append("NO");
				break;
			}
		}
	}
}