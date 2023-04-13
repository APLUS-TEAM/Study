import java.util.*;
import java.io.*;
public class PS1937_MinBeom {
	static int[] dr = {-1, 1, 0, 0};				// 사방 탐색
	static int[] dc = {0, 0, -1, 1};
	static int[][] arr;								// 값을 담을 배열
	static int[][] count;							// 거리를 담을 배열
	static int N;
	static int max = 0;								// 최대값 변수
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));		// 입력부
		N = Integer.parseInt(bf.readLine());
		arr = new int[N][N];
		count = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				max = Math.max(max, dfs(i,j));	// 최대 값인지 비교해서 저장 max 변수에 저장
			}
		}
		System.out.println(max);
	}
	static int dfs(int row, int col) {
		if(count[row][col] != 0) return count[row][col];	// 거리가 0이 아니면 이미 지나쳐 온곳이므로 여기서 들어가서 시작해도 최대값이 될수 없기 때문에 리턴
		count[row][col] = 1;								// 거리를 1로 초기화
		int dRow, dCol;									
		for(int i=0; i<4; i++) {
			dRow = row + dr[i];								// 사방탐색 진행
			dCol = col + dc[i];
			if(dRow >= N || dCol >= N || dRow <0 || dCol <0) continue;
			if(arr[row][col] < arr[dRow][dCol]) {			// 만약 현재값보다 탐색한 대나무가 더 많은 경우
				count[row][col] = Math.max(count[row][col], dfs(dRow,dCol)+1);	// 거리를 저장되어있던 값과 dfs로 다시 들어가서 탐색한 값과 비교해서 저장한다
			}
		}
		return count[row][col];						// 카운트 리턴
	}
}