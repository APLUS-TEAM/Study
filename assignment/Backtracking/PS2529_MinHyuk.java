import java.io.*;
import java.util.*;
public class Baekjoon2529 {
	static int N;
	static boolean[] arr;
	static int[] per;
	static boolean[] visit = new boolean[10];
	static ArrayList<String> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new boolean[N];
		per = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) arr[i] = st.nextToken().charAt(0) == '<';
		find(0);
		Collections.sort(list);
		System.out.println(list.get(list.size()-1) + "\n" + list.get(0));
	}
	static void find(int depth) {
		if (depth == N+1) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < N+1; ++i) sb.append(per[i]);
			list.add(sb.toString());
			return;
		}
		for (int i = 0; i < 10; ++i) {
			if (visit[i] || depth > 0 && per[depth-1] < i != arr[depth-1]) continue;
			per[depth] = i;
			visit[i] = true;
			find(depth+1);
			visit[i] = false;
		}
	}
}