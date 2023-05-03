import java.util.*;
import java.io.*;
public class PS11559_MinBeom {
    static char[][] arr = new char[12][6];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Deque<int []> graph = new LinkedList<>();
    static int count = 0;
    static boolean isCount = true;
    static boolean[][] visit;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<12; i++) arr[i] = bf.readLine().toCharArray();
        while(isCount) {
            isCount = false;
            visit = new boolean[12][6];
            for(int i=0; i<12; i++) { 
                for(int j=0; j<6; j++) {
                    if(visit[i][j] || arr[i][j] == '.') continue;
                    bfs(i,j,arr[i][j]);
                }
            }
            if(isCount) {
            	while(!graph.isEmpty()) {
            		int[] num = graph.pop();
            		arr[num[0]][num[1]] = '.';
            	}
                count++;
                for(int i=0; i<6; i++) gravity(i);
            }
        }
        System.out.println(count);
    }
    static void bfs(int startRow, int startCol, char alpha) {
        int num = 1;
        graph.add(new int[] {startRow, startCol});
        Queue<Integer> q = new LinkedList<>();
        q.offer(startRow);
        q.offer(startCol);
        visit[startRow][startCol] = true;
        while(!q.isEmpty()) {
            int row = q.poll();
            int col = q.poll();
            for(int i=0; i<4; i++) {
                int dRow = row+dr[i];
                int dCol = col+dc[i];
                if(dRow<0 || dCol<0 || dRow>=12 || dCol>=6 || visit[dRow][dCol]) continue;
                if(arr[dRow][dCol] != alpha) continue;
                num++;
                q.add(dRow);
                q.add(dCol);
                visit[dRow][dCol] = true;
                graph.add(new int[] {dRow, dCol});
            }
        }
        if(num < 4) while(num-->0) graph.removeLast();
        else isCount = true;
    }
    static void gravity(int col) {
    	for(int i=11; i>=0; i--) {
    		if(arr[i][col] != '.') continue;
    		for(int j=i-1; j>=0; j--) {
    			if(arr[j][col] == '.') continue;
    			arr[i][col] = arr[j][col];
    			arr[j][col] = '.';
    			break;
    		}
    	}
    }
}