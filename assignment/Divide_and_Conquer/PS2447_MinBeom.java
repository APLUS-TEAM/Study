package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PS2447_MinBeom {
	static StringBuilder sb = new StringBuilder();
	static int size;
	static char[][] star;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		size = Integer.parseInt(bf.readLine());
		star = new char[size][size];
		for(int i=0; i<size; i++) {
			for(int k=0; k<size; k++) {
				star[i][k] = '*';
			}
		}
		space(size, 0, 0);
		for(int i=0; i<size; i++) {
			for(int k=0; k<size; k++) {
				sb.append(star[i][k]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static void space(int num, int y, int x) {
		int divNum = num/3;
		for(int i=0; i<num; i++) {
			for(int k=0; k<num; k++) {
				if(i/divNum == 1 && k/divNum == 1) {
					star[i+y][k+x] = ' ';
				}
			}
		}
		if(divNum == 1) return;
		for(int i=0; i<3; i++) {
			for(int k=0; k<3; k++) {
				space(divNum, i*divNum+y, k*divNum+x);
			}
		}
	}
}
