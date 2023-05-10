package day010;

import java.io.*;
import java.util.*;

public class Main27498 {

	static int N;
	static int[] p;
	static int inf = 100000;

	static class nojam implements Comparable<nojam> {
		int start, end, weight;

		@Override
		public int compareTo(nojam o) {
			return o.weight - this.weight;
		}

		public nojam(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		nojam[] arr = new nojam[M];
		p = new int[N + 1];

		makeset();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());

			if (D == 0) {
				arr[i] = new nojam(A, B, C);
			} else {
				arr[i] = new nojam(A, B, inf);
			}
		}

		Arrays.sort(arr);
		int sum = 0;

		for (int i = 0; i < M; i++) {

			if (union(arr[i].start, arr[i].end))
				;
			else {
				sum += arr[i].weight;
			}

		}

		System.out.println(sum);

	}

	static void makeset() {
		for (int i = 1; i <= N; i++)
			p[i] = i;
	}

	static boolean union(int a, int b) {

		int A = find(a);
		int B = find(b);

		if (A != B) {
			p[A] = B;
			return true;
		} else {
			return false;
		}

	}

	static int find(int a) {
		if (p[a] != a)
			return  p[a] = find(p[a]);
		else
			return a;
	}

}
