import java.util.*;
import java.io.*;
public class PS27498_MinBeom {
	static int[] p;
	static int[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		p = new int[N+1];
		graph = new int[M][4];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
			graph[i][3] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(graph, new Comparator<int []>() {
			@Override
			public int compare(int [] o1, int [] o2) {
				if(o1[3] != o2[3]) return Integer.compare(o2[3], o1[3]);
				else return Integer.compare(o2[2], o1[2]);
			}
		});
		for(int i=1; i<=N; i++) p[i] = i;
		int ans = 0;
		for (int i = 0; i < M; i++) {
			int px = findset(graph[i][0]);
			int py = findset(graph[i][1]);
			if(px!=py) union(px,py);
			else ans += graph[i][2];
		}
		System.out.println(ans);
	}
	
	static int findset(int x) {
		if(x != p[x]) p[x] = findset(p[x]);
		return p[x];
	}
	static void union(int x, int y) {
		p[y] = x;
	}
}
