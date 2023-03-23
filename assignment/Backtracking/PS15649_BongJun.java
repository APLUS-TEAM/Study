import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS15649_BongJun {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder resultSb = new StringBuilder();
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		permutation(0);
		System.out.println(resultSb);
	}
	
	static void permutation(int depth) {
		if (depth == M) {
			resultSb.append(sb).append('\n');
			return ;
		}
		
		for (int i = 1; i <= N; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			sb.append(i).append(' ');
			permutation(depth + 1);
			sb.delete(depth * 2, sb.length());
			visited[i] = false;
		}
	}
}
