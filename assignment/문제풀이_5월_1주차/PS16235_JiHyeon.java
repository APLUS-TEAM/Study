package swea;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class PS16235_JiHyeon {

	static int[][] energyM;
	static int[][] energyW;
	static Queue<Tree> treeM;
	static int N, M, K;
	static int[][] delta = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };

	static Queue<Tree> getAge = new LinkedList<>();
	static Queue<Tree> getEnergy = new LinkedList<>();
	static Queue<Tree> temp = new LinkedList<>();

	static class Tree implements Comparable<Tree> {
		int row;
		int col;
		int age;

		public Tree(int row, int col, int age) {
			this.row = row;
			this.col = col;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		energyM = new int[N][N];
		energyW = new int[N][N];

		treeM = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				energyM[i][j] = 5;
				energyW[i][j] = sc.nextInt();
			}
		}

		for (int m = 0; m < M; m++) {
			int row = sc.nextInt() - 1;
			int col = sc.nextInt() - 1;
			int age = sc.nextInt();
			treeM.add(new Tree(row, col, age));
		}

		Collections.sort((List<Tree>) treeM);

		while (K > 0) {

			// 봄 + 여름
			while (!treeM.isEmpty()) {
				Tree now = treeM.poll();

				if (now.age <= energyM[now.row][now.col]) {
					energyM[now.row][now.col] -= now.age;
					++now.age;
					getAge.add(now);
				} else
					getEnergy.add(now);
			}

			while (!getAge.isEmpty()) {
				treeM.add(getAge.poll());
			}

			while (!getEnergy.isEmpty()) {
				Tree now = getEnergy.poll();
				energyM[now.row][now.col] += now.age / 2;
			}

			// 가을

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					energyM[i][j] += energyW[i][j];
				}
			}

			// 겨울
			int size = treeM.size();
			while (size > 0) {
				Tree now = treeM.poll();
				int age = now.age;
				if (age % 5 == 0) {
					for (int d = 0; d < 8; d++) {
						int nr = now.row + delta[d][0];
						int nc = now.col + delta[d][1];
						if (nr < 0 || nc < 0 || nr >= N || nc >= N)
							continue;
						treeM.add(new Tree(nr, nc, 1));
					}
				}
				temp.add(now);
				--size;
			}

			while (!temp.isEmpty()) {
				treeM.add(temp.poll());
			}

			--K;
		}

		System.out.println(treeM.size());
	}

}
