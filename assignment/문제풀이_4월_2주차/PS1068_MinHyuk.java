import java.io.*;
import java.util.*;
public class Baekjoon1068 {
	static int N, ans;
	static boolean[] visit;
	static ArrayList<Integer>[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int root = -1;
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N];
		for (int i = 0; i < N; ++i) tree[i] = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int curNode = 0; curNode < N; ++curNode) {
			int parentNode = Integer.parseInt(st.nextToken());
			if (parentNode == -1) root = curNode;
			else tree[parentNode].add(curNode);
		}
		ans = 0;
		visit = new boolean[N];
		int remove = Integer.parseInt(br.readLine());
		visit[remove] = true;
		if (root != remove) dfs(root);
		System.out.println(ans);
	}
	static void dfs(int curNode) {
		visit[curNode] = true;
		int cnt = 0;
		for (int nextNode : tree[curNode]) {
			if (!visit[nextNode]) {
				++cnt;
				visit[nextNode] = true;
				dfs(nextNode);
			}
		}
		if (cnt == 0) ++ans;
	}
}