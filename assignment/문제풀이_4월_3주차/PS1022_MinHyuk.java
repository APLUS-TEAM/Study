import java.io.*;
import java.util.*;

public class Baekjoon1022 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		int r2 = Integer.parseInt(st.nextToken());
		int c2 = Integer.parseInt(st.nextToken());
		int r = 0, c = 0;
		int num = 1;
		int dir = 0;
		int cnt = 0;
		int allCnt = 0;
		int widthCnt = 1;
		int max = Integer.MIN_VALUE;
		int[][] map = new int[r2-r1+1][c2-c1+1];
		int[][] delta = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
		while (allCnt != (r2-r1+1)*(c2-c1+1)) {
			if (r >= r1 && c >= c1 && r <= r2 && c <= c2) {
				++allCnt;
				map[r-r1][c-c1] = num;
				max = Math.max(max, num);
			}
			++cnt;
			r += delta[dir][0];
			c += delta[dir][1];
			if (cnt == widthCnt) {
				if (dir == 1 || dir == 3) ++widthCnt;
				cnt = 0;
				dir = (dir+1)%4;
			}
			++num;
		}
		int len = 0;
		while (max > 0) {
			++len;
			max /= 10;
		}
		String format = "%"+len+"d ";
		for (int[] i : map) {
			for (int n : i) sb.append(String.format(format, n));
			sb.append('\n');
		}
		System.out.println(sb);
	}
}