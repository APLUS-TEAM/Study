import java.io.*;
public class PS11058_MinHyuk {
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		long[] dp = new long[N+1];
		for (int i = 1; i <= 6 && i <= N; ++i) dp[i] = i;
		for (int i = 7; i <= N; ++i)
			for (int j = 3; j < 6; ++j)
				dp[i] = Math.max(dp[i], dp[i-j]*(j-1));
		System.out.println(dp[N]);
	}
}