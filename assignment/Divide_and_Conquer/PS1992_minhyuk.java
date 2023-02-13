package baekjoon;

import java.io.*;
public class Baekjoon1992 {
	static char[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		for (int i = 0; i < N; i++)
			arr[i] = br.readLine().toCharArray();
		qt(0, 0, N);
		System.out.println(sb);
	}
	static void qt(int r, int c, int N) {
		char temp = arr[r][c];
		boolean check = true;
		outer: for (int i = r; i < r + N; i++) 
			for (int j = c; j < c + N; j++) 
				if (temp != arr[i][j]) {
					check = false;
					break outer;
				}
		if (check) {
			sb.append(temp);
			return;
		}
		sb.append('(');
		qt(r, c, N/2);
		qt(r, c + N/2, N/2);
		qt(r + N/2, c, N/2);
		qt(r + N/2, c + N/2, N/2);
		sb.append(')');
	}
}
