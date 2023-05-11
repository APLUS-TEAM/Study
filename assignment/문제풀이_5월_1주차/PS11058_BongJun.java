import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[101];
		for (int i = 1; i <= 6; i++) {
			dp[i] = i;
		}
		for (int i = 7; i <= N; i++) {
			dp[i] = selectMax(dp[i - 3] * 2, 
					dp[i - 4] * 3, 
					dp[i - 5] * 4);
		}
		
		System.out.println(dp[N]);
	}

	private static long selectMax(long i, long j, long k) {
		return Math.max(i, Math.max(j, k));
	}
}