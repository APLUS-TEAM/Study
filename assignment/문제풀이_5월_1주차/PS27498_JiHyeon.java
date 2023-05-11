package swea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class PS27498_JiHyeon {
	static int N, M;
	static int[] P;
	static ArrayList<Node> list = new ArrayList<>();
	static class Node{
		int one;
		int two;
		int affection;
		int couple;
		
		public Node(int one, int two, int affection, int couple) {
			this.one = one;
			this.two = two;
			this.affection = affection;
			this.couple = couple;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		for (int i = 0; i < M; i++) {
			int q = sc.nextInt();
			int w = sc.nextInt();
			int e = sc.nextInt();
			int r = sc.nextInt();
			
			list.add(new Node(q, w, e, r));
		}

		Collections.sort(list, new Comparator<Node>() {


			@Override
			public int compare(Node o1, Node o2) {
				if(o2.couple==o1.couple) return o2.affection-o1.affection;
				return o2.couple-o1.couple;
			}
		});

		P = new int[N + 1]; //정점 1부터 시작
		
		for (int i = 1; i <= N; i++) {//대표 설정
			P[i] = i;
		}

		int ans = 0; //포기한 애정도

		for (int i = 0; i < M; i++) {
			Node now = list.get(i);
			int x = now.one;
			int y = now.two;

			if (findset(x) != findset(y)) {//대표 원소가 같지 않으면
				union(x, y); //합해줌
			} else
				ans += now.affection; //선택못한 간선을 더함
		}
		
		System.out.println(ans);
		
	}

	private static void union(int x, int y) {
		P[findset(y)] = findset(x);
	}

	private static int findset(int x) {
		if (P[x] != x)
			return P[x] = findset(P[x]);
		else
			return x;
	}
}