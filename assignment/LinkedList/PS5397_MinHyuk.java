package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon5397 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		while (N-->0) {
			char[] str = br.readLine().toCharArray();
			LinkedList<Character> list = new LinkedList<>();
			int idx = 0;
			for (int i = 0; i < str.length; i++) {
				if (str[i] == '<') {
					if (!list.isEmpty() && idx > 0) idx--;
				} else if (str[i] == '>') {
					if (!list.isEmpty() && idx < list.size()) idx++;
				} else if (str[i] == '-') {
					if (!list.isEmpty() && idx > 0) list.remove(--idx);
				} else {
					list.add(idx++, str[i]);
				}
			}
			for (char i : list) sb.append(i);
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
