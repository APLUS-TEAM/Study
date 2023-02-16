package baekjoon;

import java.io.*;
import java.util.*;
public class Baekjoon11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }
        for (int i = 0; i < N; i++)
        	for (int j = 0; j < i; j++)
        		if (arr[i] > arr[j] && dp[i] < dp[j] + arr[i])
        			dp[i] = dp[j] + arr[i];
        int ans = dp[0];
        for (int i = 1; i < N; i++)
        	ans = Math.max(ans, dp[i]);
        System.out.println(ans);
    }
}
