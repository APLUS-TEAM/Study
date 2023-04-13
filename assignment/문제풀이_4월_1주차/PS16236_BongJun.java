import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PS16236_bongjun {

	    static int[] dy = { -1, 0, 1, 0 };
	    static int[] dx = { 0, 1, 0, -1 };
	    static int N;
	    static int[][] map;
	    static int[] shark = new int[4];
	    static final int Y = 0, X = 1, SIZE = 2, EAT = 3;
	    static boolean[][] visited;
	    static Queue<Node> queue = new LinkedList<>();
	    static ArrayList<Node> fishList = new ArrayList<>();

	    public static void main(String[] args) throws NumberFormatException, IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        N = Integer.parseInt(br.readLine());
	        StringTokenizer st;
	        map = new int[N][N];
	        visited = new boolean [N][N];
	        for (int y = 0; y < N; y++) {
	            st = new StringTokenizer(br.readLine());
	            for (int x = 0; x < N; x++) {
	                map[y][x] = Integer.parseInt(st.nextToken());
	                if (map[y][x] == 9) {
	                    shark[Y] = y;
	                    shark[X] = x;
	                    shark[SIZE] = 2;
	                    map[y][x] = 0;
	                }
	            }
	        }

	        System.out.println(getEatTime());
	    }

	    private static int getEatTime() {
	        int netTime = 0;
	        int time = hasNextFish(cloneArr(visited));

	        while (time != -1) {
	            netTime += time;
	            time = hasNextFish(cloneArr(visited));
	            sharkLevelUp();
	        }
	        
	        return netTime;
	    }

	    private static void sharkLevelUp() {
	        if (shark[EAT] == shark[SIZE]) {
	            shark[SIZE]++;
	            shark[EAT] = 0;
	        }
	    }

	    private static int hasNextFish(boolean[][] visited) {
	        queue.add(new Node(shark[Y], shark[X]));
	        visited[shark[Y]][shark[X]] = true;
	        int time = 0;
	        
	        while (!queue.isEmpty()) {
	            int size = queue.size();
	            time++;
	            
	            while (size-- > 0) {
	                Node current = queue.poll();

	                for (int d = 0; d < 4; d++) {
	                    int y = current.y + dy[d];
	                    int x = current.x + dx[d];

	                    if (!isInArray(y, x) || map[y][x] > shark[SIZE] || visited[y][x]) continue;
	                    if (map[y][x] < shark[SIZE] && map[y][x] != 0) {
	                        fishList.add(new Node(y, x));
	                    }
	                    visited[y][x] = true;
	                    queue.add(new Node(y, x));
	                }
	                
	            }
	            
	            if (fishList.size() > 0) {
	            	Node target = getTarget();
	            	map[target.y][target.x] = 0;
	            	shark[Y] = target.y;
	            	shark[X] = target.x;
	            	shark[EAT]++;
	            	queue.clear();
	            	fishList.clear();
	            	return time;
	            }
	        }
	        
	        return -1;
	    }
	    
	    public static void print() {
	    	
	    	for (int[] a: map) {
	    		System.out.println(Arrays.toString(a));
	    	}
	    	System.out.println();
	    }
	    
	    private static Node getTarget() {
	        Node target = fishList.get(0);
	        
	        for (int i = 1; i < fishList.size(); i++) {
	            Node next = fishList.get(i);
	            if (target.y == next.y) {
	                if (target.x > next.x) {
	                    target = next;
	                }
	            } else if (target.y > next.y){
	                target = next;
	            }
	        }
	        return target;
	    }

	    private static boolean isInArray(int y, int x) {
	        if (0 <= y && 0 <= x && y < N && x < N) {
	            return true;
	        }
	        return false;
	    }

	    public static boolean[][] cloneArr(boolean[][] arr) {
	        boolean[][] newArr = new boolean[N][N];
	        
	        for (int y = 0; y < N; y++) {
	            newArr[y] = arr[y].clone();
	        }
	        return newArr;
	    }

	    static class Node {
	        int y, x;

	        public Node(int y, int x) {
	            this.y = y;
	            this.x = x;
	        }
	    }
	}
