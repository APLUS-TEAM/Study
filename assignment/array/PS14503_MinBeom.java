package algo;

import java.util.*;
import java.io.*;

public class PS14503_MinBeom {
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(bf.readLine());
		int row = Integer.parseInt(st.nextToken());		// 시작 세로 좌표
		int col = Integer.parseInt(st.nextToken());		// 시작 가로 좌표
		int d = Integer.parseInt(st.nextToken());		// 시작 방향
		boolean swi = true;								// 사방 중 청소가 안되어있는 방이있는지 표시해줄 변수
		int count = 0;									// 방청소 횟수
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 위까지는 입력부분
		
		outer: while (true) {
			swi = true;								// 매 while문 마다 true로 초기화
			if (arr[row][col] == 0) {				// 현재 칸이 청소가 안되어 있으면
				count++;							// 횟수+1증가
				arr[row][col] = 2;					// 현재 배열을 2로 증가시킴 -> 후에 0이 아니기 때문에 재탐색 안함
			}
			if (arr[row][col] == 2) {				// 현재 칸이 청소가 되어있는 경우
				for (int i = 0; i < 4; i++) {
					if (arr[row + dr[i]][col + dc[i]] == 0) {
						swi = false;			// 사방탐색해서 하나라도 청소가 안되어있으면 false처리
					}
				}
				if (!swi) {					// false일때 실행되는 변수 -> 이때 false메소드 부분이 먼저 나와야 순서가 맞음
					d = (d + 3) % 4;		// 반시계 방향으로 꺽기
					switch (d) {			// 청소기 방향에 따른 case문 앞에 청소가 안되어있을 경우 전진
					case 0:					
						if (arr[row - 1][col] == 0) row--;		
						break;
					case 1:
						if (arr[row][col + 1] == 0) col++;
						break;
					case 2:
						if (arr[row + 1][col] == 0) row++;
						break;
					case 3:
						if (arr[row][col - 1] == 0) col--;
						break;
					}
				} 
				else {					// 전부 청소가 되어있는 경우
					switch (d) {		// 0인경우
					case 0:				
						if (arr[row + 1][col] == 1) break outer; // 만약 뒤가 벽이면 while문 종료
						else row++;								// 아닐 경우 바라보는 방향기준 청소기를 뒤로 1칸 옮김
						break;
					case 1:
						if (arr[row][col - 1] == 1) break outer;
						else col--;
						break;
					case 2:
						if (arr[row - 1][col] == 1) break outer;
						else row--;
						break;
					case 3:
						if (arr[row][col + 1] == 1) break outer;
						else col++;
						break;
					}
				}
			}
		}
		System.out.println(count);				// 횟수 출력
	}
}
