package 스터디;

import java.util.Scanner;


public class PS10819_Jihyeon {
	static int N;
	static int[] arr;
	static boolean[] visit;
	static int[] vArr;
	static int result;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N];
		visit = new boolean[N];
		vArr = new int [N];
		result = 0;
		
		for (int n = 0; n < N; n++) {
			arr[n] = sc.nextInt();
		}

		permutation(0); //순열이라는 뜻
		System.out.println(result);

	}

	private static void permutation(int count) {
		if (count == N) { //내가 고른 수 ==순열 고를 값(N)
			int sum = 0;
			for (int n = 0; n < N - 1; n++) {
				sum += Math.abs(vArr[n] - vArr[n + 1]); //주어진 조건 실행
			}
			if (sum > result) {
				result = sum;
			} return; //갱신후 리턴!!!!!!
		}

		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true; //방문처리
				vArr[count] = arr[i]; //방문값 밸류어레이에 넣어주기
				permutation(count + 1); // 쭉쭉쭉 실행 후
				visit[i] = false; //펄스 처리되고 다시 시작
			} else continue;
		}
	}

}