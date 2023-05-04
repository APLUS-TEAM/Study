package day010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1082 {

	static class node implements Comparable<node> {
		int index;
		int cost;

		public node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(node o) {
			if (this.cost == o.cost) {
				return o.index - this.index;
			}
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		node[] arr1 = new node[N]; // 원본 그대로 순서대로 둘것;
		node[] arr2 = new node[N]; // 가격 순서대로

		if (N==1) {
			System.out.println(0);
			System.exit(0);
		}
		
		for (int i = 0; i < N; i++) {
			int A = Integer.parseInt(st.nextToken());
			arr1[i] = new node(i, A);
			arr2[i] = new node(i, A);
		}
		int M = Integer.parseInt(br.readLine());
		// 입력 끝 arr2는 가격순, 가격이 같으면 값이 높은 순서대로;
		Arrays.sort(arr2);

		int A = M % arr2[0].cost;
		int B = M / arr2[0].cost;

		if (A == 0 && arr2[0].index != 0) { // 0이 아닌 경우에만 이렇게 해결!
			for (int i = 0; i < B; i++)
				sb.append(arr2[0].index);
		} else if (A != 0 && arr2[0].index != 0) {

			int index = N - 1;
			int cnt = 0;
			while (A >= arr2[1].cost - arr2[0].cost) {
				if (arr1[index].cost==arr2[0].cost) break;
				while (A >= (arr1[index].cost - arr2[0].cost)) {
					A -= (arr1[index].cost - arr2[0].cost); // 할 수 있을 때까지 뺀다.
					sb.append(index);
					cnt++;
					if (cnt>=B) {
						System.out.println(sb);
						System.exit(0);
					}
				}
				index--;
				if (index<0) break;
			}
			for (int i = 0; i < B - cnt; i++)
				sb.append(arr2[0].index);

		} else { // arr2[0].index==0이면 계산법이 좀 달라짐		

			while (A < arr2[1].cost - arr2[0].cost) {
				A += arr2[0].cost;
				B--;
			}
			
			if (B<=0) {
				System.out.println(0);
				System.exit(0);
			}
			
			
			int index = N - 1;
			int cnt = 0;
			while (A >= arr2[1].cost - arr2[0].cost) {
				
				while (A >= (arr1[index].cost - arr2[0].cost)) {
					A -= (arr1[index].cost - arr2[0].cost); // 할 수 있을 때까지 뺀다.
					sb.append(index);
					cnt++;
				}
				index--;
				if (index<0) break;
			}
			for (int i = 0; i < B - cnt; i++)
				sb.append(arr2[0].index);

		}

		System.out.println(sb);

	}

}