import java.util.*;
import java.io.*;
public class PS1022_MinBeom {
	static int r1,r2,c1,c2,max=0;
	static int[][] arr;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		int dRow = 0, dCol = 0, dir = 0;
		int d = 1, num = 1, dCount = 0;
		arr = new int[r2-r1+1][c2-c1+1];
		while(!isFull()) {
			if(dRow>= r1 && dRow<=r2 && dCol>= c1 && dCol<=c2) {
				arr[dRow-r1][dCol-c1] = num;
			}
			num++;
			dCount++;
			dRow += dr[dir];
			dCol += dc[dir];
			if(d == dCount) {
				dCount = 0;
				if(dir%2 == 1) d++;
				dir = (dir+1)%4;
			}
		}
		max = num-1;
		print();
		System.out.println(sb);
	}
	static void print() {
		int maxLen = (int)Math.log10(max), len;
		for (int i = 0; i <= r2 - r1; i++) {
            for (int j = 0; j <= c2 - c1; j++) {
                len = maxLen - (int) Math.log10(arr[i][j]);
                for (int k = 0; k < len; k++) {
                	sb.append(' ');
                }
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }
	}
	static boolean isFull() {
		return arr[0][0] != 0 && arr[r2-r1][0] != 0 && arr[0][c2-c1] != 0 && arr[r2-r1][c2-c1] != 0;
	}
}