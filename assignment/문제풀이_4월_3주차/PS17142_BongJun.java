import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static List<int[]> viruses = new ArrayList<>();
    static int result, virusIdx, emptyCount;
    static int[] select;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int t = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        virusIdx = 0;
        select = new int[M];
        result = 987654321;
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 2) {
                    viruses.add(new int[] {y, x});
                    virusIdx++;
                } else if (value == 0) {
                	emptyCount++;
                }
                map[y][x] = value;
            }
        }
        
        if (emptyCount == 0) {
        	result = 0;
        } else {
        	selectVirus(0, 0);
        }
        
        if (result == 3000) {
        	result = -1;
        }
        System.out.println(result);
    }
    private static void selectVirus(int index, int depth) {
        if (depth == M) {
            result = Math.min(result, simulation(dimension2Clone(map)));
            t++;
            return ;
        }
        
        for (int i = index; i < virusIdx; i++) {
            select[depth] = i;
            selectVirus(i + 1, depth + 1);
        }
    }
    
    private static int[][] dimension2Clone(int[][] map) {
    	int[][] clonedArr = new int[N][N];
    	for (int i = 0; i < N; i++) {
    		clonedArr[i] = map[i].clone();
    	}
		return clonedArr;
	}
	private static int simulation(int[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        for (Integer i : select) {
            map[viruses.get(i)[0]][viruses.get(i)[1]] = 3;
        	queue.add(viruses.get(i));
        }
        int spreadCount = 0;
        int depth = 0;
        int answer = 0;
        while (!queue.isEmpty() && spreadCount != emptyCount) {
        	int size = queue.size();
        	depth--;
        	while (size-- > 0) {
        		int[] current = queue.poll();
        		for (int d = 0; d < 4; d++) {
        			int y = current[0] + dy[d];
        			int x = current[1] + dx[d];
        			
        			if (!isInArray(y, x) || (map[y][x] != 0 && map[y][x] != 2)) continue;
        			queue.add(new int[] {y, x});
        			if (map[y][x] == 0) {
        				spreadCount++;
        			}
        			map[y][x] = depth;
        			answer = depth;
        		}
        	}
        }
        if (spreadCount != emptyCount) {
        	return 3000;
        }
//        for (int[]a : map) {
//        	System.out.println(Arrays.toString(a));
//        }
//        System.out.println(-answer);
        return -answer;
    }
	private static boolean isInArray(int y, int x) {
		if (0 <= x && 0 <= y && x < N && y < N) {
			return true;
		}
		return false;
	}
}