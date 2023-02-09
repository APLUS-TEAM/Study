package baekjoon;

import java.io.*;
public class Baekjoon11729 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sb.append((1<<N) - 1).append("\n");
        hanoi(N, 1, 2, 3);
        System.out.println(sb);
    }
    static void hanoi(int N, int start, int enRoute, int end) {
    	if (N == 0) return;
    	hanoi(N - 1, start, end, enRoute);
    	sb.append(start + " " + end + "\n");
    	hanoi(N - 1, enRoute, start, end);
    }
}
