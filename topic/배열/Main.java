package ps3085;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		char[][] board = new char[N][N];
		for (int index = 0; index < N; index++) {
			board[index] = sc.next().toCharArray();
		}
		int max = 0;
		int temp  = 0;

		for (int yIndex = 0; yIndex < N; yIndex++) {
			for (int xIndex = 0; xIndex < N; xIndex++) {
				temp = countX(board, yIndex, xIndex, temp);
				temp = countY(board, yIndex, xIndex, temp);
				max = Math.max(max, temp);
				if (max == N) {
					System.out.println(N);
					System.exit(0);
				}
			}
		}
		
		for (int yIndex = 0; yIndex < N; yIndex++) {
			for (int xIndex = 0; xIndex < N - 1; xIndex++) {
				switchElement(board, yIndex, xIndex, true);
				temp = countX(board, yIndex, xIndex, temp);
				temp = countX(board, yIndex, xIndex + 1, temp);
				temp = countY(board, yIndex, xIndex, temp);
				temp = countY(board, yIndex, xIndex + 1, temp);
				switchElement(board, yIndex, xIndex, true);
				max = Math.max(max, temp);
				if (max == N) {
					System.out.println(N);
					System.exit(0);
				}
			}
		}
		for (int yIndex = 0; yIndex < N - 1; yIndex++) {
			for (int xIndex = 0; xIndex < N; xIndex++) {
				switchElement(board, yIndex, xIndex, false);
				temp = countX(board, yIndex, xIndex, temp);
				temp = countX(board, yIndex + 1, xIndex, temp);
				temp = countY(board, yIndex, xIndex, temp);
				temp = countY(board, yIndex + 1, xIndex, temp);
				max = Math.max(max, temp);
				switchElement(board, yIndex, xIndex, false);
				if (max == N) {
					System.out.println(N);
					System.exit(0);
				}
			}
		}
		System.out.println(max);
	}
	
	static void switchElement(char[][] board, int y, int x, boolean horizontal) {
		char temp = board[y][x];
	
		if (horizontal) {
			board[y][x] = board[y][x + 1];
			board[y][x + 1] = temp;
		} else {
			board[y][x] = board[y + 1][x];
			board[y + 1][x] = temp;
		}
	}
	
	static int countX(char[][] board, int y , int x, int previous) {
		int size = board.length;
		char target = board[y][x];
		int result = -1;
		for (int index = x; index < size; index++) {
			System.out.println(y + " " + x + "cX" + index);
			if (board[y][index] != target) {
				break;
			}
			result++;
		}
		for (int index = x; index >= 0; index--) {
			System.out.println(y + " " + x + "cX" + index);
			if (board[y][index] != target) {
				break;
			}
			result++;
		}
		return Math.max(previous, result);
	}
	
	static int countY(char[][] board, int y , int x, int previous) {
		int size = board.length;
		char target = board[y][x];
		int result = -1;
		for (int index = y; index < size; index++) {
			if (board[index][x] != target) {
				break;
			}
			result++;
		}
		for (int index = y; index >= 0; index--) {
			if (board[index][x] != target) {
				break;
			}
			result++;
		}
		return Math.max(previous, result);
	}
}
