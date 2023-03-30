package baekjoon;
import java.util.*;
import java.io.*;
public class PS17940_MinBeom {
    static final int INF = Integer.MAX_VALUE;
    static int[] company;
    static ArrayList<Node>[] arr;
    static int[] dist;
    static int[] cost;
    static int N;
    static int M;
    static int companyCost = 0;
    static class Node implements Comparable<Node> {
        int idx, dist, cost;
        Node(int idx, int dist, int cost) {
            this.idx= idx;
            this.dist = dist;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            if(this.cost > o.cost) return 1;
            else if(this.cost < o.cost) return -1;
            else {
                return Integer.compare(this.dist, o.dist);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        company = new int[N];
        cost = new int[N];
        dist = new int[N];
        arr = new ArrayList[N];
        for(int i=0; i<N; i++) {
            company[i] = Integer.parseInt(bf.readLine());
            arr[i] = new ArrayList<>();
        }
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 0) continue;
                else arr[i].add(new Node(j, num, 0));
            }
        }
        dijk(0);
        sb.append(cost[M]).append(' ').append(dist[M]);
        System.out.println(sb);
    }
    
    static void dijk(int startNum) {
        Arrays.fill(dist, INF);
        Arrays.fill(cost, INF);
        dist[startNum] = 0;
        cost[startNum] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNum, 0, 0));
        while(!pq.isEmpty()) {
            Node nowNode = pq.poll();
            if(nowNode.idx == M) break;
            if(nowNode.cost > cost[nowNode.idx]) continue;
            for(Node next : arr[nowNode.idx]) {
                int num = 0;
                if(company[next.idx] != company[nowNode.idx]) num++;
                if(cost[next.idx] > cost[nowNode.idx] + num) {
                	cost[next.idx] = cost[nowNode.idx] + num;
                	if(dist[next.idx] > dist[nowNode.idx] + next.dist) {
                		dist[next.idx] = dist[nowNode.idx] + next.dist;
                		pq.add(new Node(next.idx, dist[next.idx], cost[next.idx]));
                	}
                }
                else if(cost[next.idx] == cost[nowNode.idx]+num) {
                	if(dist[next.idx] > dist[nowNode.idx] + next.dist) {
                        dist[next.idx] = dist[nowNode.idx] + next.dist;
                        pq.add(new Node(next.idx, dist[next.idx], cost[next.idx]));
                    }
                }
                else continue;
            }
        }
    }
}