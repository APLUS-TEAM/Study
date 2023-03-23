package algo;

import java.util.*;
import java.io.*;

public class baek1916 {
	static int N, M; // 노드와 간선 변수
	static final int INF = Integer.MAX_VALUE; // 무한대 값 지정 -> int형 최대값
	static int target; // 확인할 숫자

	static class Node implements Comparable<Node> { // 인덱스와 가중치(거리)를 갖고있는 노드 클래스 생성
		int idx, cost;

		public Node(int idx, int cost) { // 노드가 생성될 때 저장
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) { // comparable를 통해서 가중치들의 값을 비교
			return Integer.compare(this.cost, o.cost); // 왼쪽이 크면 1, 같으면 0, 작으면 -1리턴
		}
	}

	static int[] arr; // 배열 생성
	static boolean[] visit; // 방문 배열 생성
	static ArrayList<Node>[] graph; // 노드클래스를 인자로 받는 2차원 ArrayList생성

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine()); // 노드 개수
		M = Integer.parseInt(bf.readLine()); // 간선 개수
		graph = new ArrayList[N + 1]; // 배열을 N+1크기 만큼 생성
										// 0번째 안씁니다

		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>(); // ArrayList를 배열안에 생성
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int node = Integer.parseInt(st.nextToken()); // 시작노드
			int idx = Integer.parseInt(st.nextToken()); // 도착노드
			int cost = Integer.parseInt(st.nextToken()); // 가중치 입력
			graph[node].add(new Node(idx, cost)); // 그래프에 노드클래스로 저장
		}
		st = new StringTokenizer(bf.readLine());
		int start = Integer.parseInt(st.nextToken()); // 시작노드
		target = Integer.parseInt(st.nextToken()); // 도착노드
		dijkstra(start); // 다익스트라 시작
		System.out.println(arr[target]); // 출력

	}

	static void dijkstra(int idx) {
		visit = new boolean[N + 1]; // 방문 배열생성
		arr = new int[N + 1]; // 거리를 담을 배열 생성
		Arrays.fill(arr, INF); // 배열을 INF로 전부 초기화
		arr[idx] = 0; // 시작노드 0으로 거리 초기화
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 노드 클래스를 인자로 받는 우선순위 큐를 생성
														// 여기서 우선순위는 가중치로 결정이 된다
		pq.add(new Node(idx, 0)); // 시작노드를 우선순위 큐에 집어넣는다

		while (!pq.isEmpty()) { // 우선순위 큐가 빌때까지 진행
			int nowIdx = pq.poll().idx; // 우선순위 큐에서 하나의 인자를 뽑아서 진행
			if (nowIdx == target)
				break; // 우리가 원하는 노드가 큐에서 나올시 종료
			if (visit[nowIdx])
				continue; // 방문했을시 해당 반복문 건너뛰기
			visit[nowIdx] = true; // 안했을시 방문처리 후 for문 진행
//			Node curNode = pq.poll();		// 노드를 받고
//			int nowIdx = curNode.idx;		
//            if(nowIdx == target) break;		// 우리가 원하는 노드가 큐에서 나올시 종료
//            if(arr[nowIdx] < curNode.cost) continue;	// 최소 비용이 아닐시 continue;
			
			
			for (Node next : graph[nowIdx]) { // 현재 노드와 다른 노드와의 거리를 갱신하기 위한 for문
				if (arr[next.idx] > arr[nowIdx] + next.cost) { // 만약 다음 노드까지의 저장되어있던 길이 > 현재 노드부터 다음 노드의 길이 + 현재노드와 시작 노드와의 길이
					arr[next.idx] = arr[nowIdx] + next.cost; // 거리 최소값 갱신
					pq.add(new Node(next.idx, arr[next.idx])); // 우선순위 큐에 담는다
				}
			}
		}
	}
}