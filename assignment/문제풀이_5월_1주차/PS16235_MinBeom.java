import java.util.*;
import java.io.*;
public class PS16235_MinBeom {
    static int[][] A;
    static int[][] graph;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static class Node implements Comparable<Node> {
        int row, col, age;
        public Node(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.age, o.age);
        }
    }
    static Deque<Node> dq = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        graph = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=1; j<=N; j++) {
            	A[i][j] = Integer.parseInt(st.nextToken());
            	graph[i][j] = 5;
            }
        }
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            dq.add(new Node(row, col, age));
        }
        while(K-->0) {
            spring();
            fall(N);
            winter(N);
        }
        System.out.println(dq.size());
    }
    static void spring() {
    	Queue<Node> dead = new LinkedList<>();
    	Deque<Node> live= new LinkedList<>();
    	while(!dq.isEmpty()) {
    		Node now = dq.poll();
    		if(graph[now.row][now.col] < now.age) dead.offer(now);
    		else {
    			graph[now.row][now.col] -= now.age;
    			now.age++;
    			live.offer(now);
    		}
    	}
    	dq = live;
    	for(Node now : dead) graph[now.row][now.col] += now.age/2;
    }
    static void fall(int N) {
    	Deque<Node> live = new LinkedList<>();
    	while(!dq.isEmpty()) {
    		Node now = dq.poll();
    		if(now.age % 5 == 0) {
    			for(int i=0; i<8; i++) {
    				int dRow = now.row + dr[i];
    				int dCol = now.col + dc[i];
    				if(dRow < 1 || dRow > N || dCol < 1 || dCol > N) continue;
    				live.offerFirst(new Node(dRow, dCol, 1));
    			}
    		}
    		live.offerLast(now);
    	}
    	dq = live;
    }
    static void winter(int N) {
        for(int i=1; i<=N; i++) {
        	for(int j=1; j<=N; j++) graph[i][j] += A[i][j];
        }
    }
}