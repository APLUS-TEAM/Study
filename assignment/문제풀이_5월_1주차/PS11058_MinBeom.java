import java.io.*;
public class PS11058_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		long[] dp = new long[N+1];
		for(int i=1; i<=N; i++) {
			if(i<=6) dp[i] = i;
			else {
				for(int j=2; j<=5; j++) dp[i] = Math.max(dp[i-(j+1)]*j, dp[i]);
			}
		}
		System.out.println(dp[N]);
	}
}
