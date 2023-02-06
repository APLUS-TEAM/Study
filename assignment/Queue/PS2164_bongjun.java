package ps2164;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<Integer> queue = new LinkedList<>();
		for (int index = 1; index <= N; index++) {
			queue.add(index);
		}
		while (queue.size() != 1) {
			queue.poll();
			queue.add(queue.poll());
		}
		System.out.println(queue.poll());
	} 
}
