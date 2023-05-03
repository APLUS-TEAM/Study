package codeTest.baekjoon;

import java.util.Scanner;

public class Q1082 {
	
	// 가장 긴 자릿수
	// 앞자리부터 큰 수가 들어간다
	
	static int N;
	static int[] arr;
	static int M;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		M = sc.nextInt();
		
		int ans[] = new int[100];
		int cost = 0;
		int idx = 0;
		
		// 가장 싼걸로 가장 길게 만들기
		int minIdx = findMin(0);
		if(minIdx == 0) { // 근데 0이 젤 싼거면
			minIdx = findMin(1);
			if(arr[minIdx] <= M) { // 두번째로 싼거를 살수있으면 넣는다
				ans[idx++] = minIdx;
				cost += arr[minIdx];
				minIdx = 0;
			}else { // 두번째로 싼거 못사면 패스
				System.out.println(0);
				return;
			}
		}
		
		// 자릿수채우기
		while(cost+arr[minIdx] <= M) {
			ans[idx++] = minIdx;
			cost += arr[minIdx];
		}
		
		// 앞에서부터 큰숫자로 교체시도
		for(int i = 0; i < idx; i++) {
			for(int j = N - 1; j >= 0; j--) {
				if(cost - arr[ans[i]] + arr[j] <= M) {
					cost = cost - arr[ans[i]] + arr[j];
					ans[i] = j;
					break;
				}
			}
		}
		
		for(int i = 0; i < idx; i++) {
			System.out.print(ans[i]);
		}
		
	}

	// 싼거찾기
	private static int findMin(int start) {
		int retIdx = 0;
		int min = 100;
		for(int i = start; i < N; i++) {
			if(min > arr[i]) {
				min = arr[i];
				retIdx = i;
			}
		}
		return retIdx;
	}

}
