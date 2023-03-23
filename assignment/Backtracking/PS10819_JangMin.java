package codeTest.baekjoon;

import java.util.Scanner;

public class Q10819 {
	
	static int N;  // 배열의 크기
	static boolean[] visited; // 방문을 나타내는 배열
	static int max; // 최대값
	static int[] A; // 입력받을 배열
	static int count; // 내부함수에서 최대값이랑 비교할 변수
	static int idx; // 내부함수를 돌리는 횟수
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력
		N = sc.nextInt();
		A = new int[N];
		
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		// 내부함수 실행
		for(int i = 0; i < N; i++) {
			// i : 처음 비교를 시작할 인덱스
			visited = new boolean[N]; // 매번 초기화해줘도됨
			idx = 0; // 내부함수를 돌리는 횟수 초기화
			count = 0; // 전역 최대값이랑 비교해줄 이번 회차의 최대값
			function(i); // 실행해염
		}
		
		System.out.println(max);
		
	}
	
	// i : 처음 비교를 시작할 인덱스
	public static void function(int i) {
		
		// idx가 N과 같으면 다돌았다
		// 이번 회차의 식의 값과 기존 최대값을 비교
		if(idx == N) {
//			System.out.println(count);
//			System.out.println(max);
			max = Math.max(max, count);
			return;
		}
		
		// temp : 한 번의 뺄셈 값을 비교할 변수
		// j : 함수를 실행할 때 보내줄 다음 인덱스
		int temp = 0;
		int j = 0;
		
		// N만큼 반복문을 도는데
		// 입력받은 인덱스와 같거나
		// 이미 들렀던 값은 제외하고
		// 나머지 값 중에서
		// 차이를 구해서 가장 큰 값을 구한다
		// 그리고 최대일 때 인덱스를 다음 내부함수로 보낸다
		for(int k = 0; k < N; k++) {
			if(i == k || visited[k] == true) continue;
			
			if(Math.abs(A[i] - A[k]) >= temp) {
				temp = Math.abs(A[i] - A[k]);
				j = k;
			}
			
		}
		
		// 방문 기록
		// 식의 값을 더하고
		// 실행횟수 +1하고
		// 다음 함수 실행
		visited[i] = true;
		count += temp;
		idx++;
		function(j);
		
	}

}
