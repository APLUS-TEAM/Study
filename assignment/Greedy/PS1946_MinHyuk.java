package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon1946 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-->0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N+1];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				// �����ɻ� �������� ����
				arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
			}
			int cnt = 1; // ���� 1���� ������ ���߰��� �ο�
			int max = arr[1]; // ���� ������� ���� ���� ���
			for (int i = 2; i <= N; i++) 
				// i��° ����� ���� ����� ���� ������� ���� ���� ������� ������
				if (max > arr[i]) {
					// ���߰����ο� + 1
					cnt++;
					// ���� ������� ���� ���� ��� ����
					max = arr[i];
				}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}