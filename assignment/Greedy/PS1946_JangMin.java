package codeTest.baekjoon;

import java.util.Scanner;

public class Q1946 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		
		for(int tc = 0; tc < test_case; tc++) {
			
			int num = sc.nextInt();
			int[][] slave = new int[num][2];
			
			int min = Integer.MAX_VALUE;
			int minR = 0;
			int count = 0;
			
			for(int r = 0; r < num; r++) {
				int sum = 0;
				for(int c = 0; c < 2; c++) {
					slave[r][c] = sc.nextInt();
					sum += slave[r][c];
				}
				if(sum < min) {
					min = sum;
					minR = r;
				}
			}
			
			loop : 
			for(int r = 0; r < num; r++) {
				
				if(r == minR) {
					count++;
					continue;
				}
				
				int check = 0;
				
				for(int c = 0; c < 2; c++) {
					if(slave[r][c] == 1) {
						count++;
						continue loop;
					}
					
					if(slave[r][c] > slave[minR][c]) {
						check++;
					}
					
					if(check == 2) continue loop;
				}
				
				count++;
				
			}
			
			System.out.println(count);
			
		}
		
	}

}
