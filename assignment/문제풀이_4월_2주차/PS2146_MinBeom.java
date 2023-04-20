import java.util.*;
import java.io.*;
public class PS2146_MinBeom {
    static class Node implements Comparable<Node>{
        int row, col, cost;
        public Node(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static int N;
    static int[][] arr;
    static int[][] costArr;
    static int[][] team;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N][N];
        costArr = new int[N][N];
        team = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(costArr[i], 1000);
        }
        StringTokenizer st;
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(bf.readLine());
        	for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int num = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] == 1 && team[i][j] == 0) {
                    land(i,j,num);
                    num++;
                }
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(arr[i][j] == 1) bfs(i,j);
            }
        }
        System.out.println(min);
    }
    static void land(int startRow, int startCol, int land) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(startRow);
        q.offer(startCol);
        boolean[][] visit = new boolean[N][N];
        visit[startRow][startCol] = true;
        team[startRow][startCol] = land;
        while(!q.isEmpty()) {
            int row = q.poll();
            int col = q.poll();
            for(int i=0; i<4; i++) {
                int dRow = row + dr[i];
                int dCol = col + dc[i];
                if(dRow >= N || dCol >= N || dCol <0 || dRow <0 || visit[dRow][dCol]) continue;
                if(arr[dRow][dCol] == 1) {
                    team[dRow][dCol] = land;
                    visit[dRow][dCol] = true;
                    q.offer(dRow);
                    q.offer(dCol);
                }
            }
        }
    }
    
    static void bfs(int startRow, int startCol) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(startRow, startCol, 0));
        while(!q.isEmpty()) {
            Node now = q.poll();
            for(int i=0; i<4; i++) {
                int dRow = now.row + dr[i];
                int dCol = now.col + dc[i];
                if(dRow >= N || dCol>=N || dCol <0 || dRow <0 || costArr[dRow][dCol] <= now.cost+1) continue;
                if(arr[dRow][dCol] == 1) {
                    if(team[dRow][dCol] == team[startRow][startCol]) continue;
                    min = Math.min(now.cost, min);
                }
                else {
                    q.offer(new Node(dRow, dCol, now.cost+1));
                    costArr[dRow][dCol] = now.cost+1;
                }
            }
        }
    }
}