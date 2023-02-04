package ps1874;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		Scanner sc = new Scanner(System.in);
		StringBuilder result = new StringBuilder();
		int n = sc.nextInt();
		stack.push(0);
		int used = 0;
		int top = 0;
		
		
		while (n-- > 0) {
			int next = sc.nextInt();
			
			if (next >= top) {
				for (int index = used + 1; index <= next; index++) {
					result.append("+\n");
					stack.push(index);
					used = index;
				}
				stack.pop();
				result.append("-\n");
			} else {
				System.out.println("NO");
				System.exit(0);
			}
			top = stack.peek();
		}
		System.out.println(result);
	}
}