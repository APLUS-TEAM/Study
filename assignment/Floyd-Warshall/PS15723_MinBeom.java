import java.io.*;
import java.util.*;

public class PS15723_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		final int INF = 1000000000;
		int[][] arr = new int[26][26];
		for(int i=0; i<26; i++) {
			for(int j=0; j<26; j++) {
				if(i != j) arr[i][j] = INF;
			}
		}
		for(int i=0; i<N; i++) {
			String str = bf.readLine();
			int start = str.charAt(0)-'a';
			int end = str.charAt(5)-'a';
			arr[start][end] = 1;
		}
		for(int i=0; i<26; i++) {
			for(int j=0; j<26; j++) {
				for(int k=0; k<26; k++) {
					arr[j][k] = Math.min(arr[j][k], arr[j][i]+arr[i][k]);
				}	
			}
		}
		N = Integer.parseInt(bf.readLine());
		for(int i=0; i<N; i++) {
			String str = bf.readLine();
			int start = str.charAt(0)-'a';
			int end = str.charAt(5)-'a';
			if(arr[start][end] == INF) sb.append('F').append('\n');
			else sb.append('T').append('\n');
		}
		System.out.println(sb);
	}
}