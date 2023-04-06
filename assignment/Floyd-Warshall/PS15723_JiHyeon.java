package algoStudy;
import java.util.Scanner;

public class PS15723_JiHyeon {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		boolean[][] arr = new boolean[26][26];
		
		for(int i = 0; i<N; i++) {
			String word = sc.next();
			int from = word.charAt(0)-'a';
			word = sc.next();
			word = sc.next();
			int to = word.charAt(0)-'a';
			
			arr[from][to] = true;
		}
		
		
		for(int i=0; i<26; i++) {
			arr[i][i] = true;
		}
		
		
		for(int k=0; k<26; k++) {
			for(int i=0; i<26; i++) {
				for(int j=0; j<26; j++) {
					if(arr[i][k] && arr[k][j]) {
						arr[i][j] = true;
					}
				}
			}
		}
		
		int M = sc.nextInt();
		for(int i = 0; i<M; i++) {
			String word = sc.next();
			int from = word.charAt(0)-'a';
			word = sc.next();
			word = sc.next();
			int to = word.charAt(0)-'a';
			
			if(arr[from][to]) System.out.println('T');
			else System.out.println('F');
		}

		
	}

}