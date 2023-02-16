package baekjoon;

import java.io.*;
public class Baekjoon9655 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(N%2==1?"SK":"CY");
	}
}