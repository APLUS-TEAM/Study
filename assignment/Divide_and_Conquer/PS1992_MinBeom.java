package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PS1992_MinBeom {
	static StringBuilder sb = new StringBuilder();
	static boolean[][] con; 
	static boolean sw;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());
		con = new boolean[testCase][testCase];
		for(int i=0; i<testCase; i++) {
			char[] arr = bf.readLine().toCharArray();
			for(int k=0; k<testCase; k++) {
				con[i][k] = (arr[k]=='1') ? true : false;
			}
		}
		quad(testCase, 0, 0);
		System.out.println(sb);
	}
	
	private static void quad(int num, int row, int col) {
		sb.append('(');
		if(num == 2) {
			for(int i=0; i<2; i++) {
				for(int k=0; k<2; k++) {
					
				}
			}
		}
	}
}
