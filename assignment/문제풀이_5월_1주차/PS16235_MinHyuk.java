import java.io.*;
import java.util.*;
public class PS16235_MinHyuk {
	static class Tree {
		int r, c, age;
		Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}
	}
	static int N, M, K;
	static Deque<Integer>[][] trees;
	static int[][] curNutrient, plusNutrient; // ���� �����, �ܿ￡ �߰����� �����
	static Queue<Tree> newOrDeadTrees = new LinkedList<>(); // ���� �¾ ���� or ���� ���� ť
	static int[][] delta = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		trees = new Deque[N][N];
		curNutrient = new int[N][N];
		plusNutrient = new int[N][N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				trees[i][j] = new LinkedList<>();
				curNutrient[i][j] = 5;
				plusNutrient[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (M-->0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			trees[r][c].offer(age);
		}
		while (K-->0) {
			springAndSummer();
			autumnAndWinter();
		}
		System.out.println(count());
	}
	static void springAndSummer() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				int size = trees[i][j].size();
				while (size-->0) {
					int treeAge = trees[i][j].poll();
					if (treeAge <= curNutrient[i][j]) { // ���� ����к��� ���̰� ������
						curNutrient[i][j] -= treeAge++; // ���� ����� ���̰�, ���� �ѻ� ����
						trees[i][j].offer(treeAge);
					}
					else newOrDeadTrees.offer(new Tree(i, j, treeAge)); // �׾����� ���� ���� ť�� �־���
				}
				while (!newOrDeadTrees.isEmpty()) curNutrient[i][j] += newOrDeadTrees.poll().age/2; // ���� ���⼭ ó��
			}
		}
	}
	static void autumnAndWinter() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				curNutrient[i][j] += plusNutrient[i][j]; // �ܿﵵ ���⼭ ó��
				int size = trees[i][j].size();
				while (size-->0) {
					int treeAge = trees[i][j].poll();
					trees[i][j].offer(treeAge);
					if (treeAge % 5 != 0) continue; 
					for (int k = 0; k < 8; ++k) {
						int nr = i + delta[k][0];
						int nc = j + delta[k][1];
						if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
						newOrDeadTrees.offer(new Tree(nr, nc, 1));
					}
				}
			}
		}
		while (!newOrDeadTrees.isEmpty()) {
			Tree cur = newOrDeadTrees.poll();
			trees[cur.r][cur.c].offerFirst(cur.age); // ���� ���� ������ ���� ��Ƿ� offerFirst
		}
	}
	static int count() {
		int cnt = 0;
		for (int i = 0; i < N; ++i)
			for (int j = 0; j < N; ++j)
				cnt += trees[i][j].size();
		return cnt;
	}
}