import java.util.*;
import java.io.*;
public class PS7562_MinBeom {
	static int size;
	static int[][] chess;
	static boolean[][] visit;
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
	static int targetRow;
	static int targetCol;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int testCase = Integer.parseInt(bf.readLine());
		for(int tc=1; tc<=testCase; tc++) {
			size = Integer.parseInt(bf.readLine());
			chess = new int[size][size];
			visit = new boolean[size][size];
			st = new StringTokenizer(bf.readLine());
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine());
			targetRow = Integer.parseInt(st.nextToken());
			targetCol = Integer.parseInt(st.nextToken());
			visit[startRow][startCol] = true;
			bfs(startRow, startCol);
			sb.append(chess[targetRow][targetCol]).append('\n');
		}
		System.out.println(sb);
	}
	
	static void bfs(int a, int b) {
		Queue<Integer> qRow = new LinkedList<>();
		Queue<Integer> qCol = new LinkedList<>();
		qRow.add(a);
		qCol.add(b);
		while(!qRow.isEmpty()) {
			int row = qRow.poll();
			int col = qCol.poll();
			if(row == targetRow && col == targetCol) break;
			for(int i=0; i<8; i++) {
				int num1 = row+dr[i];
				int num2 = col+dc[i];
				if(num1<size && num2<size && num1>=0 && num2>=0) {
					if(!visit[num1][num2]) {
						visit[num1][num2] = true;
						chess[num1][num2] = chess[row][col]+1;
						qRow.add(num1);
						qCol.add(num2);
					}
				}
			}
		}
	}
}