import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PS1992_BongJun {
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				board[y][x] = br.read() - '0';
			}
			br.readLine();
		}
		System.out.println(quardTree(0, 0, N));
	}
	
	static String quardTree(int y, int x, int size) {
		if (size == 1) {
			return Integer.toString(board[y][x]);
		}
		size /= 2;
		String[] quardValue = new String[4];
		quardValue[0] = quardTree(y, x, size);
		quardValue[1] = quardTree(y, x + size, size);
		quardValue[2] = quardTree(y + size, x, size);
		quardValue[3] = quardTree(y + size, x + size, size);
		
		StringBuilder subSB = new StringBuilder();
		
		if (isitBroken(quardValue)) {
			subSB.append('(');
			for(int i = 0; i < 4; i++) {
				subSB.append(quardValue[i]);
			}
			subSB.append(')');
			return subSB.toString();
		} else {
			if (areTheySame(quardValue)) {
				return quardValue[0];
			} 
			subSB.append('(');
			for (int i = 0; i < 4; i++) {
				subSB.append(quardValue[i]);
			}
			subSB.append(')');
			return subSB.toString();
		}
	}
	
	static boolean isitBroken(String[] values) {
		for (int i = 0; i < 4; i++) {
			if (values[i].charAt(0) == '(') {
				return true;
			}
		}
		return false;
	}
	
	static boolean areTheySame(String[] values) {
		for (int i = 1; i < 4; i++ ) {
			if (!values[i].equals(values[i - 1])) {
				return false;
			}
		}
		return true;
	}
}
