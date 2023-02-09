package baekjoon;

import java.io.*;

public class Baekjoon17478 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sb.append("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.\n");
		recursion(0, "");
		System.out.print(sb);
	}
	private static void recursion(int cnt, String str) {
		String temp = str;
		if (cnt == N) {
			sb.append(str + "\"����Լ��� ������?\"\n");
			sb.append(str + "\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"\n");
			sb.append(str + "��� �亯�Ͽ���.\n");
			return;
		} else {
			sb.append(str + "\"����Լ��� ������?\"\n");
			sb.append(str + "\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.\n");
			sb.append(str + "���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.\n");
			sb.append(str + "���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"\n");
			str += "____";
			recursion(cnt + 1, str);
			sb.append(temp + "��� �亯�Ͽ���.\n");
		}
	}
}