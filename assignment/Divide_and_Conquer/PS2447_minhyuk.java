package baekjoon;

import java.io.*;
public class Baekjoon2447 {
	static char[][] arr;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        star(0, 0, N);
        for (char[] i : arr) sb.append(i).append('\n');
        System.out.println(sb);
    }
	static void star(int x, int y, int size) {
		if (size == 3) {
			for (int i = x; i < x+3; i++) 
				for (int j = y; j < y+3; j++) 
					if (i == x+1 && j == y+1) arr[i][j] = ' ';
					else arr[i][j] = '*';
		} else {
			int cnt = 0;
			for (int i = x; i < x+size; i += size/3) {
				for (int j = y; j < y+size; j += size/3) {
					if (cnt == 4) blank(i, j, size/3);
					else star(i, j, size/3);
					cnt++;
				}
			}
		}
	}
	static void blank(int x, int y, int size) {
		for (int i = x; i < x+size; i++)
			for (int j = y; j < y+size; j++)
				arr[i][j] = ' ';
	}
}