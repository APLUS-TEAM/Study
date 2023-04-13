import java.util.*;
import java.io.*;
public class PS16236_MinBeom {
	static class Node implements Comparable<Node>{
		int row, col, dist;
		public Node(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			if(this.dist != o.dist) return Integer.compare(this.dist, o.dist);
			else if(this.row != o.row) return Integer.compare(this.row, o.row);
			else return Integer.compare(this.col, o.col);
		}
	}
    static int N;
    static int[][] arr;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static Queue<Node> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9) {
                	q.offer(new Node(i, j, 0));
                    arr[i][j] = 0;
                }
            }
        }
        bfs();
    }
    static void bfs() {
    	int time = 0, stack = 0, cost = 2;
        int dRow, dCol;
        while(true) {
        	int[][] dist = new int[N][N];
        	PriorityQueue<Node> pq = new PriorityQueue<>();
        	while(!q.isEmpty()) {
        		Node next = q.poll();
        		for(int i=0; i<4; i++) {
        			dRow = next.row + dr[i];
        			dCol = next.col + dc[i];
        			if(dRow >= N || dCol >= N || dRow < 0 || dCol < 0 || arr[dRow][dCol] > cost || dist[dRow][dCol] != 0) continue;
        			dist[dRow][dCol] = dist[next.row][next.col]+1;
        			q.offer(new Node(dRow, dCol, dist[dRow][dCol]));
        			if(arr[dRow][dCol]<cost && arr[dRow][dCol] >= 1) pq.offer(new Node(dRow, dCol, dist[dRow][dCol]));
        		}
        	}
        	if(pq.size() == 0) {
        		System.out.println(time);
        		return;
        	}
        	Node target = pq.poll();
        	time += target.dist;
        	stack++;
        	arr[target.row][target.col] = 0;
        	if(cost == stack) {
        		cost++;
        		stack = 0;
        	}
        	q.offer(new Node(target.row, target.col, 0));
        }
    }
}