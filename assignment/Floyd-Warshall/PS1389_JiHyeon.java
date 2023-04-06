package algoStudy;
import java.util.Scanner;

public class PS1389_JiHyeon {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); //유저의 수 N (2 ≤ N ≤ 100)
		int M = sc.nextInt(); //친구 관계의 수 M (1 ≤ M ≤ 5,000)
		int[][] arr = new int[N+1][N+1]; //1부터 시작
		
		int INF = 5001; //대충 큰수 친구관계의 수보다 큰 수로.... 인티저맥스밸류 하지 않기..
		
		for(int i=1; i<N+1; i++) { // 1부터 N까지 배열 INF로 초기화
			for(int j=1; j<N+1; j++) {
				arr[i][j] = INF;
			}
		}
		
		for(int i=1; i<N+1; i++) { // 나 자신으로부터 나 자신은 거리 0
			arr[i][i] = 0;
		}
		
		for(int m=0; m<M; m++) { 
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			arr[a][b] = 1; // A 와  B 가 친구면 B 와  A도 친구이다
			arr[b][a] = 1;
		}
		
		
		for(int k=1; k<N+1; k++) { //중간
			for(int i=1; i<N+1; i++) { //시작
				for(int j=1; j<N+1; j++) { //끝
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j] ); //한번에 가는 것과 <->
																	//중간 지점을 기점으로 양쪽 더한 값이 더 작은지 비교해서 갱신 
				}
				
			}
		}
		int min = Integer.MAX_VALUE;
		
		int idx = 1;
		
		for(int i=1; i<N+1;  i++) {
			int sum = 0;
			for(int j=1; j<N+1; j++) {
				sum+=arr[i][j];
			}
			
			if(sum<min) {
				min = sum;
				idx = i;
			}
			
		}
		
		System.out.println(idx); //가작 작은 수인 사람의 번호 출력
	}

}