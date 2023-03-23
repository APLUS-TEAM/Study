package 스터디;

import java.util.Scanner;

public class PS15649_Jihyeon {
		public static boolean[] visit;
		public static int[] arr;
		public static StringBuilder sb = new StringBuilder();

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);

			int N = sc.nextInt();
			int M = sc.nextInt();

			visit = new boolean[N];
			arr = new int[M];
			dfs(N, M, 0);

			System.out.print(sb);

		}

		public static void dfs(int N, int M, int depth) {
			if (depth == M) {//깊이랑 M으로 들어온 값(arr에 값이 담기면)이 같아지면 출력 
				for (int x : arr) {
					sb.append(x).append(' ');
				}
				sb.append('\n');
				return;
			}

			for (int i = 0; i < N; i++) {
				if (!visit[i]) {
					visit[i] = true;
					arr[depth] = i + 1;
					dfs(N, M, depth + 1);
					visit[i] = false; //출력 후 리턴되고 펄스로 바뀜 다시 시작
				}
			}
		}
	}