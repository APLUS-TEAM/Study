package day010;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PS16235_LeeDuHyun {
	
	static int N;
	static int[][] arr;
	static int[][] energy;
	static PriorityQueue<Integer>[][] PQ;
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	static int[] dy = {1,1,0,-1,-1,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N]; // 현재 있는 양분 
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				arr[i][j] = 5;
			}
		}
		energy = new int[N][N]; //매년 공급하는 양분
		
		for (int i=0;i<N;i++) {
			st = new  StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				energy[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		PQ = new PriorityQueue[N][N];
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				PQ[i][j] = new PriorityQueue<Integer>();
			}
		}
		
		for (int i=0;i<M;i++) {
			st = new  StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			PQ[A-1][B-1].offer(C);
		}
		
		for (int i=0;i<K;i++) {
			
			//봄, 여름 합침
			spring();
			//가을, 겨울 합침
			fall();
		}
		
		int cnt = 0;
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				
				if (!PQ[i][j].isEmpty()) {
					cnt+=PQ[i][j].size();
				}
				
			}
		}
		
		System.out.println(cnt);
		
		
	}



	private static void fall() {
		
		int[][] temparr = new int[N][N];
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (!PQ[i][j].isEmpty()) {
					
					for (int c:PQ[i][j]) {
						if (c%5==0) {
							
							for (int k=0;k<8;k++) {
								
								int x = i+dx[k];
								int y = j+dy[k];
								
								if (x<0||N<=x||y<0||N<=y) continue;
								else temparr[x][y]++;
							}
							
							
						}
					}
				}
			}
		}
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				for (int k=0;k<temparr[i][j];k++) PQ[i][j].offer(1);
				arr[i][j]+=energy[i][j];
			}
		}
		
		
	}


	private static void spring() {
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (!PQ[i][j].isEmpty()) {
				
					List<Integer> list1 = new ArrayList<>();
					List<Integer> list2 = new ArrayList<>();
					while (!PQ[i][j].isEmpty()) {
						int temp = PQ[i][j].poll();
						
						if (arr[i][j]>=temp) {
							arr[i][j]-=temp;
							list1.add(temp+1);
						} else list2.add(temp);
						
					}
					
					for (int c: list1) {
						PQ[i][j].offer(c);
					}
					
					for (int c:list2) {
						arr[i][j]+=c/2;
					}
					
				}
			}
		}
		
		
	}

}