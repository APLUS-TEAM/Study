package algoStudy;
import java.util.Scanner;

public class PS11404_JiHyeon {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt(); //도시의 개수
		int M = sc.nextInt(); //버스의 개수
		
		int[][] arr = new int[N+1][N+1];
		
		int INF = 100000000;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				else arr[i][j] = INF;
			}
		}
		
		for(int m = 0 ; m<M; m++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int before = arr[a][b];
			int cost = sc.nextInt();
			
			arr[a][b] = Math.min(cost, before); 
		}
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
				arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(arr[i][j]==INF) sb.append(0).append(' ');
				else sb.append(arr[i][j]).append(' ');
			} sb.append('\n');
		}
		
		System.out.println(sb);
	}

}