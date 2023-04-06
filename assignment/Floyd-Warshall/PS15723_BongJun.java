import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.sound.midi.Soundbank;

public class Main {
	static int n, m;
	static int[][] edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		edges = new int[26][26];
		for (int i = 0; i < 26; i++) {
			Arrays.fill(edges[i], 500);
			edges[i][i] = 0;
		}
		for (int i = 0 ; i < n; i++) {
			String temp = br.readLine();
			char a = temp.charAt(0);
			char b = temp.charAt(5);
			edges[a - 'a'][b - 'a'] = 1;
		}
		m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < 26; i++) {
			for (int y = 0; y < 26; y++) {
				for (int x = 0; x < 26; x++) {
					edges[y][x] = Math.min(edges[y][x], edges[y][i] + edges[i][x]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			String temp = br.readLine();
			char a = temp.charAt(0);
			char b = temp.charAt(5);
			if (edges[a - 'a'][b - 'a'] == 500) {
				sb.append('F').append('\n');
			} else {
				sb.append('T').append('\n');
			}
		}
		System.out.println(sb);
	}
}
