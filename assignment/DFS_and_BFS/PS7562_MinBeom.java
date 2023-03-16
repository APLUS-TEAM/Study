package algo;

import java.util.*;
import java.io.*;
public class PS7562_MinBeom {
	static int size;										// 사이즈 
	static int[][] chess;									// 체스판 배열
	static boolean[][] visit;								// 방문 2차원으로 배열
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};			// 8방탐색할 dr, dc 만들기
	static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};			
	static int targetRow;									// 나이트가 위치해야할 세로좌표
	static int targetCol;									// 나이트가 위치해야할 가로좌표
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int testCase = Integer.parseInt(bf.readLine());		
		for(int tc=1; tc<=testCase; tc++) {					// 이 부분까지 테스트 케이스받아서 돌리는 부분
			size = Integer.parseInt(bf.readLine());			// 체스판의 사이즈 받기
			chess = new int[size][size];					// 체스판의 크기 정해주기
			visit = new boolean[size][size];				// 방문했는지 표시할 배열에 크기 정해주기
			st = new StringTokenizer(bf.readLine());		
			int startRow = Integer.parseInt(st.nextToken());	// 시작지점 세로좌표 입력받기
			int startCol = Integer.parseInt(st.nextToken());	// 시작지점 가로좌표 입력받기
			st = new StringTokenizer(bf.readLine());
			targetRow = Integer.parseInt(st.nextToken());		// 나이트가 위치해야할 세로좌표 입력받기
			targetCol = Integer.parseInt(st.nextToken());		// 나이트가 위치해야할 가로좌표 입력받기
			visit[startRow][startCol] = true;					// bfs 시작 전 시작점 방문했다고 바꿔주기
			bfs(startRow, startCol);							// 시작좌표부터 너비우선탐색 시작
			sb.append(chess[targetRow][targetCol]).append('\n');		// 얼마나 움직였는지 나타내기
		}
		System.out.println(sb);								// 값 출력
	}
	
	/*
	 * 		너비 우선 탐색시 원하는 결과물
	 * 		목표좌표와 시작 좌표들을 보기 쉽게 나타냄		chess 배열
	 * 
	 * 		시작지점: -								카운트를 길을 지나간 자리마다 +1씩해서
	 * 		도착지점: +								최종적으로 목표한 타켓 좌표의 값을 출력
	 * 
	 * 													- 시작좌표
	 * 													숫자들은 이전 좌표까지의 방문 횟수+1 해서 현재 좌표에 대입
	 * 													이런식으로 쌓아가면서 찾아나가기
	 * 
	 * 
	 * 		0 + 0 0 0 0 0 0								0 + 0 0 0 0 0 0					
	 * 		0 0 0 0 0 0 0 0								0 0 0 1 0 1 0 0
	 * 		0 0 0 0 0 0 0 0								0 0 1 0 0 0 1 0				시작 지점으로부터 나이트가 방문이
	 * 		0 0 0 0 - 0 0 0				->				0 0 0 0 - 0 0 0				가능한 좌표들에 +1하고
	 * 		0 0 0 0 0 0 0 0								0 0 1 0 0 0 1 0				visit[][] 배열에 각각의 좌표들을 
	 * 		0 0 0 0 0 0 0 0								0 0 0 1 0 1 0 0				true로 방문처리
	 * 		0 0 0 0 0 0 0 0								0 0 0 0 0 0 0 0				후에 true로 되어있는 부분을 방문하려 하는 경우
	 * 		0 0 0 0 0 0 0 0								0 0 0 0 0 0 0 0				재방문이 되므로 방문하지 않게 if문으로 해결한다
	 * 
	 * 
	 * 							0 2 0 0 0 0 0 0				최종적으로 +지점까지 2번만에 이동함							
	 * 							0 0 0 1 0 1 0 0								
	 * 							0 0 1 0 0 0 1 0								
	 * 			->				0 0 0 0 - 0 0 0							
	 * 							0 0 1 0 0 0 1 0								
	 * 							0 0 0 1 0 1 0 0								
	 * 							0 0 0 0 0 0 0 0								
	 * 							0 0 0 0 0 0 0 0								
	 * 
	 * 
	 */
	
	
	static void bfs(int a, int b) {
		Queue<Integer> qRow = new LinkedList<>();			// 세로 좌표를 담을 queue
		Queue<Integer> qCol = new LinkedList<>();			// 가로 좌표를 담을 queue
		qRow.add(a);										// 시작 세로 좌표를 queue에 입력
		qCol.add(b);										// 시작 가로 좌표를 queue에 입력
		while(!qRow.isEmpty()) {
			int row = qRow.poll();							// queue에서 세로 값 받아오기
			int col = qCol.poll();							// queue에서 가로	값 받아오기
			if(row == targetRow && col == targetCol) break;			// 가로 세로 좌표가 목표값이면 bfs종료
			for(int i=0; i<8; i++) {
				int num1 = row+dr[i];								// 아까 만들어둔 dr와 dc를 사용해서 
				int num2 = col+dc[i];								//	나이트가 이동이 가능한 8곳을 탐색
				if(num1<size && num2<size && num1>=0 && num2>=0) {		// 체스판 범위를 벗어나지 않는 한에서 탐색
					if(!visit[num1][num2]) {							// 방문하지 않은 곳만 방문하도록 조건문
						visit[num1][num2] = true;						// 방문했다고 true로 바꿔줌
						chess[num1][num2] = chess[row][col]+1;			// 체스판에 지나온 길의 횟수+1을 해줘서 대입
						qRow.add(num1);									// 방문한 좌표의 세로값을 queue에 입력
						qCol.add(num2);									// 방문한 좌표의 가로값을 queue에 입력
					}
				}
			}
		}
	}
}