import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, answer;
	static int[] numbers, setNumbers;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		numbers = new int[N];
		setNumbers = new int[N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		findMax(0);
		System.out.println(answer);
	}
	
	static void findMax(int count) {
		if (count == N) {
			answer = Math.max(answer, equation());
			return ;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i] == true) {
				continue;
			}
			setNumbers[count] = numbers[i];
			visited[i] = true;
			findMax(count + 1);
			setNumbers[count] = 0;
			visited[i] = false;
		}
	}

	static int equation() {
		int result = 0;
		for (int i = 0; i < N - 1; i++) {
			result += Math.abs(setNumbers[i] - setNumbers[i + 1]);
		}
		return result;
	}
}
