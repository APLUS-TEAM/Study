package codeTest.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Q1260 {
	
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	static Integer[] answer;
//	static Integer[] answer2;
	static int cnt;
	static Queue<Integer> queue = new LinkedList<Integer>();
	static Queue<Integer> q = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
//		int N = sc.nextInt();
//		int M = sc.nextInt();
//		int R = sc.nextInt();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N+1];
		visited = new boolean[N+1];
		answer = new Integer[N+1];
//		answer2 = new Integer[N+1];
		
		for(int i = 1; i < N+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			
//			int temp1 = sc.nextInt();
//			int temp2 = sc.nextInt();
//			
//			// 연결된 점을 나타내기 위해 add해준다
//			arr[temp1].add(temp2);
//			arr[temp2].add(temp1);
			
			st = new StringTokenizer(br.readLine());
			int temp1 = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			arr[temp1].add(temp2);
			arr[temp2].add(temp1);
			
		}
		
		// 오름차순 정렬
		for(int i = 1; i < N+1; i++) {
			Collections.sort(arr[i]);
		}
		
		cnt = 1;
		
		dfs(R);
		
		for(int i = 1; i < N+1; i++) {
			
//			if(answer[i] != null) {
//				System.out.println(answer[i]);
//			
//			}else {
//				System.out.println(0);
//			}
			
			if (answer[i] != null) {
				sb.append(answer[i]+" ");
			} else {
				break;
			}
			
		}
		
		sb.append("\n");
		
		cnt = 1;
		visited = new boolean[N+1];
		answer = new Integer[N+1];
		
		bfs(R);
		
//		for(int i = 1; i < N+1; i++) {
//			
////			if(answer[i] != null) {
////				System.out.println(answer[i]);
////			
////			}else {
////				System.out.println(0);
////			}
//			
//			if (answer[i] != null) {
//				sb.append(answer[i]+" ");
//			} else {
//				sb.append(0);
//			}
//			
//		}
		
		while(!q.isEmpty()) {
			
			sb.append(q.poll()+" ");
			
		}
		
		System.out.print(sb);
		
	}
	
	static void dfs(int start) {
		
		
		// 방문했다
		visited[start] = true;
		
		// 방문 순서에 따라 
		answer[cnt] = start;
		cnt++;
			
		for(int i : arr[start]) {
			
			if(!visited[i]) {
				
				dfs(i);
				
			}
			
		}
		
	}
	
	static void bfs(int start) {
		
		queue.offer(start);
		q.offer(start);
		
		// 방문했다
		visited[start] = true;
		
		// 방문 순서에 따라 
		answer[cnt] = start;
		cnt++;
		
		while(!queue.isEmpty()) {
			
			int temp = queue.poll();
			
			for(int i : arr[temp]) {
				
				if(!visited[i]) {
					q.offer(i);
					queue.offer(i);
					visited[i] = true;
					answer[i] = i;
					cnt++;
					
				}
				
			}
			
		}
		
	}

}
