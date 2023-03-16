package algo;

import java.util.*;
import java.io.*;
public class PS2178_MinBeom {
	static boolean[][] visit;	// 방문했는지 판별하는 2차원 배열
	static int[][] arr;			// 숫자 카운트 배열
	static int[][] graph;		// 그래프 배열
	static int N;				// 노드의 세로
	static int M;				// 노드의 가로
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][M];
		arr = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String str = bf.readLine();
			for(int k=0; k<M; k++) {
				graph[i][k] = str.charAt(k) - '0';		// 여기까지 배열에 값넣고 배열들 초기화 하는 과정
			}
		}
		visit[0][0] = true;				// 너비우선 탐색 이전 스타트 지점을 방문한걸로 지정하고 너비우선 탐색 시작
		bfs(0,0);						// 시작 지점 좌표값 입력
		System.out.println(arr[N-1][M-1]);
	}
	
	
	/*
	 * 		너비 우선 탐색시 원하는 결과물
	 * 		graph의 배열							arr 배열
	 * 
	 * 											카운트를 길을 지나간 자리마다 +1씩해서
	 * 											최종적으로 목표한 타켓 좌표의 값을 출력
	 * 
	 * 		1 1 0								1 2 0
	 * 		1 0 1				-> 				2 0 0
	 * 		1 1 1								3 4 5
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	static void bfs(int a, int b) {
		Queue<Integer> graphRow = new LinkedList<>();	// 그래프의 세로 좌표값을 저장하는 queue
		Queue<Integer> graphCol = new LinkedList<>();	// 그래프의 가로 좌표값을 저장하는 queue
		graphRow.add(a);								// 그래프에 시작 좌표값 입력
		graphCol.add(b);								// 그래프에 시작 좌표값 입력
		arr[a][b] = 1;									// 시작 좌표값은 1로 시작;
		while(!graphRow.isEmpty()) {					// queue값이 빌때까지 while문
			int row = graphRow.poll();					// queue세로 좌표값 갖고 나오기
			int col = graphCol.poll();					// queue가로 좌표값 갖고 나오기
			if(row == N-1 && col == M-1) return;		// 세로+1 탐색
			if(row+1<N && graph[row+1][col] != 0) {
				if(!visit[row+1][col]) {
					visit[row+1][col] = true;
					graphRow.add(row+1);
					graphCol.add(col);
					arr[row+1][col] += arr[row][col]+1;
				}
			}
			if(col+1<M && graph[row][col+1] != 0) {			// 가로+1 탐색
				if(!visit[row][col+1]) {
					visit[row][col+1] = true;
					graphRow.add(row);
					graphCol.add(col+1);
					arr[row][col+1] += arr[row][col]+1;
				}
			}
			if(row-1>=0 && graph[row-1][col] != 0) {		// 세로-1 탐색
				if(!visit[row-1][col]) {
					visit[row-1][col] = true;
					graphRow.add(row-1);
					graphCol.add(col);
					arr[row-1][col] += arr[row][col]+1;
				}
			}
			if(col-1>=0 && graph[row][col-1] != 0) {		// 가로-1 탐색
				if(!visit[row][col-1]) {
					visit[row][col-1] = true;
					graphRow.add(row);
					graphCol.add(col-1);
					arr[row][col-1] += arr[row][col]+1;
				}
			}
		}
	}
}