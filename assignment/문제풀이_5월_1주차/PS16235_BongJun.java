import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static PriorityQueue<Integer>[][][] trees;
	static Set<Integer> treePosition = new HashSet<>();
	static Set<Integer> deadTrees = new HashSet<>();
	static int[][] map;
	static int[][] energy;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		energy = new int[N][N];
		trees = new PriorityQueue[N][N][2];
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < N; x++) {
				trees[y][x][0] = new PriorityQueue<>();
				trees[y][x][1] = new PriorityQueue<>();
				map[y][x] = 5;
				energy[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			trees[y][x][0].add(age);
			treePosition.add(100 * y + x);
		}
		
		int i = 1;
		while (K-- > 0) {
//			System.out.println(i++ +"년시작");
			spring();
			summer();
			autumn();
			winter();
		}
		
		int count = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				count += trees[y][x][0].size();
			}
		}
		System.out.println(count);
	}
	
	private static void winter() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				map[y][x] += energy[y][x];
			}
		}
	}

	private static void autumn() {
		Set<Integer> children = new HashSet<>();
		for (Integer current : treePosition) {
			int y = current / 100;
			int x = current % 100;
			
			while (!trees[y][x][1].isEmpty()) {
				int tree = trees[y][x][1].poll();
				
				trees[y][x][0].add(tree);
				if (tree % 5 != 0) continue;
				for (int d = 0; d < 8; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];
					
					if (!isInArray(ny, nx)) continue;
					trees[ny][nx][0].add(1);
					children.add(ny * 100 + nx);
				}
			}
		}
		
		treePosition.addAll(children);
	}

	private static boolean isInArray(int y, int x) {
		return 0 <= y && 0 <= x && y < N && x < N;
	}

	private static void summer() {
		for (Integer current : deadTrees) {
			int y = current / 100;
			int x = current % 100;
			
			while (!trees[y][x][0].isEmpty()) {
				map[y][x] += trees[y][x][0].poll() / 2;
			}
		}
		
		deadTrees.clear();
	}

	private static void spring() {
		Set<Integer> delTarget = new HashSet<>();
		for (Integer current : treePosition) {
			int y = current / 100;
			int x = current % 100;
			
			while (!trees[y][x][0].isEmpty()) {
				int tree = trees[y][x][0].poll();
				if (map[y][x] < tree) {
					deadTrees.add(current);
					trees[y][x][0].add(tree);
					break;
				}
				map[y][x] -= tree;
				trees[y][x][1].add(tree + 1);
			}
			
			if (trees[y][x][1].size() == 0) {
				delTarget.add(current);
			}
		}
		
		for (Integer next : delTarget) {
			treePosition.remove(next);
		}
	}

	private static void print(PriorityQueue<TreeNode> a) {
		PriorityQueue<TreeNode> temp = new PriorityQueue<>();
		while (!a.isEmpty()) {
			TreeNode current = a.poll();
			System.out.println(current.toString());
			temp.add(current);
		}
		while (!temp.isEmpty()) {
			a.add(temp.poll());
		}
	}

	static class TreeNode implements Comparable<TreeNode>{
		int y, x, age;
		boolean death;

		public TreeNode(int y, int x, int age) {
			this.y = y;
			this.x = x;
			this.age = age;
		}

		@Override
		public int compareTo(TreeNode o) {
			if (this.death == o.death) {
				return Integer.compare(this.age, o.age);
			}
			return Boolean.compare(this.death, o.death);
		}

		@Override
		public String toString() {
			return age + " " + death + " " + map[y][x];
		}
	}
}