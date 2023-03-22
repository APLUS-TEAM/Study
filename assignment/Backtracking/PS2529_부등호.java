import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int k;
	static char[] signs;
	static long max, min = Long.MAX_VALUE;
	static boolean[] visited = new boolean[10];
	static int[] setNumbers;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		signs = new char[k];
		for (int i = 0 ; i < k; i++) {
			signs[i] = st.nextToken().charAt(0);
		}
		setNumbers = new int[k + 1];
		
		findAnswer(0, 0, 9);
		
		StringBuilder sb = new StringBuilder();
		if (String.valueOf(max).length() != k + 1) {
			sb.append(0);
		}
		sb.append(max).append('\n');
		if (String.valueOf(min).length() != k + 1) {
			sb.append(0);
		}
		sb.append(min);
		
		System.out.println(sb);
	}
	
	static void findAnswer(int index, int from, int to) {
		if (index == k) {
			for (int n = from; n <= to; n++) {
				if (visited[n]) {
					continue;
				}
				setNumbers[index] = n;
				long number = arrToNumber();
				max = Math.max(max, number);
				min = Math.min(min, number);
			}
			return ;
		}
		
		for (int n = from; n <= to; n++) {
			if (visited[n]) {
				continue;
			}
			
			visited[n] = true;
			setNumbers[index] = n;
			
			selectNextNumber(index, n, signs[index]);
			
			visited[n] = false;
		}
	}
	
	private static void selectNextNumber(int index, int n, char sign) {
		if (sign == '<') {
			findAnswer(index + 1, n + 1, 9);
		} else {
			findAnswer(index + 1, 0, n - 1);
		}
	}

	private static long arrToNumber() {
		long number = 0;
		for (int i = 0 ; i < k + 1; i++) {
			number *= 10;
			number += setNumbers[i];
		}
		return number;
	}
}
