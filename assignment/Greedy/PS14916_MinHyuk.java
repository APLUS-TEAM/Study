package baekjoon;

import java.io.*;
public class Baekjoon14916 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if (n == 1 || n == 3) {
			System.out.println(-1);
			System.exit(0);
		}
		if (n % 5 == 0)
			System.out.println(n/5);
		else if (n % 5 == 1)
			System.out.println((n/5-1) + 3);
		else if (n % 5 == 2)
			System.out.println(n/5 + 1);
		else if (n % 5 == 3)
			System.out.println((n/5-1) + 4);
		else
			System.out.println(n/5 + 2);
	}
}