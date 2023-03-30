package baekjoon;
import java.util.*;

import java.io.*;
public class PS1753_MinBeom {
    static StringBuilder sb = new StringBuilder();
    static final int INF = Integer.MAX_VALUE;
    static class Node implements Comparable<Node>{
        int idx, cost;
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static ArrayList<Node>[] graph;
    static int[] arr;
    static boolean[] visit;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int startNum = Integer.parseInt(bf.readLine());
        graph = new ArrayList[V+1];
        for(int i=0; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v,w));
        }
        dijkstra(V, startNum);
        System.out.println(sb);
    }
    
    static void dijkstra(int num, int start) {
        visit = new boolean[num+1];
        arr = new int[num+1];
        Arrays.fill(arr, INF);
        arr[start] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            int nowNode = pq.poll().idx;
            if(visit[nowNode]) continue;
            visit[nowNode] = true;
            for(Node next : graph[nowNode]) {
                if(arr[next.idx] > arr[nowNode]+next.cost) {
                    arr[next.idx] = arr[nowNode]+next.cost;
                    pq.add(new Node(next.idx, arr[next.idx]));
                }
            }
        }
        for(int i=1; i<arr.length; i++) {
            if(arr[i] == INF) sb.append("INF").append('\n');
            else sb.append(arr[i]).append('\n');
        }
    }
}