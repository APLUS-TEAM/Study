import java.io.*;
import java.util.*;
public class Baekjoon15723 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		boolean[][] arr = new boolean[26][26];
		for (int i = 0; i < 26; ++i)
			for (int j = 0; j < 26; ++j)
				if (i == j) arr[i][j] = true;
		while (N-->0) {
			char[] temp = br.readLine().toCharArray();
			arr[temp[0]-'a'][temp[5]-'a'] = true;
		}
		for (int m = 0; m < 26; ++m)
			for (int s = 0; s < 26; ++s)
				for (int e = 0; e < 26; ++e)
					if (arr[s][m] && arr[m][e]) arr[s][e] = true;
		int M = Integer.parseInt(br.readLine());
		while (M-->0) {
			char[] temp = br.readLine().toCharArray();
			sb.append(arr[temp[0]-'a'][temp[5]-'a']?'T':'F').append('\n');
		}
		System.out.println(sb);
	}
}