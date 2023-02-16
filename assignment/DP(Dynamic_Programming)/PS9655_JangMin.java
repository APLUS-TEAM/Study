package codeTest.baekjoon;

import java.util.Scanner;

public class Q9655 {
	
	static int[] dy;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int num = sc.nextInt();
		dy = new int[1000];
		
		int ran = stone(num);
		
		if(ran % 2 == 1) {
			System.out.println("CY");
		} else {
			System.out.println("SK");
		}
		
	}
	
	public static int stone(int num) {
		
		int count = 0;
		
		if(num < 4) {
			
			return num+1;
			
		} else {
			
			dy[0] = 1;
			dy[1] = 3;
			
			for(int n = 2; n < num; n++) {
				dy[n] = dy[n-1] + dy[n%2];
				
				if(dy[n] >= num) {
					count = n;
				}
			}
			
		}
		
		return count;
		
	}

}