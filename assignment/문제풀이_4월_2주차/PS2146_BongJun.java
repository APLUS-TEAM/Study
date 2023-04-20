import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] originalMap;
	static int[][][] map;
	static int minDistance, islandIndex = 1;
	static Queue<int[]> bridgeNodes = new LinkedList<>();
	static Queue<int[]> islandNodes = new LinkedList<>();
	static ArrayList<int[]> nodesForLengthCompare = new ArrayList<>();
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, 1, 0, -1};
	static int result = 5000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2][2];
		originalMap = new int[N + 2][N + 2];
		for (int i = 0; i < N + 2; i++) {
			map[0][i][0] = -2;
			map[N + 1][i][0] = -2;
			map[i][0][0] = -2;
			map[i][N + 1][0] = -2;
			map[0][i][1] = -2;
			map[N + 1][i][1] = -2;
			map[i][0][1] = -2;
			map[i][N + 1][1] = -2;
			originalMap[0][i] = -2;
			originalMap[N + 1][i] = -2;
			originalMap[i][0] = -2;
			originalMap[i][N + 1] = -2;
		}
		for (int y = 1; y < N + 1; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 1 ; x < N + 1; x++) {
				int mapData = Integer.parseInt(st.nextToken());
				map[y][x][0] = -mapData;
				originalMap[y][x] = mapData;
			}
		}
		for (int y = 1; y < N + 1; y++) {
			for (int x = 1 ; x < N + 1; x++) {
				if (originalMap[y][x] == 0) {
					continue;
				}
				findEndOfIsland(y, x);
			}
		}
//		while (!bridgeNodes.isEmpty()) {
//			System.out.println(Arrays.toString(bridgeNodes.poll()));
//		}
		findShortestBridge();
		System.out.println(result);
	}
	// originalMap은 bridgeNode 선정을 위해 초기 BFS 돌리는거(섬깎아내기)
	// map은 최소다리 찾기 위해 원형을 남겨놔야할 것, bridegeNode용
	static void findEndOfIsland(int yPosition, int xPosition) {
		islandNodes.add(new int[] {yPosition, xPosition, islandIndex++});
		originalMap[yPosition][xPosition] = 0;
		map[yPosition][xPosition][0] = islandIndex - 1;
		while (!islandNodes.isEmpty()) {
			int[] target = islandNodes.poll();
			int y = target[0];
			int x = target[1];
			if (isItEndOfIsland(y, x)) {
				bridgeNodes.add(new int[] {y, x, target[2], 0});
			}
			for (int i = 0; i < 4; i++) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				if (originalMap[nextY][nextX] == 1) {
					islandNodes.add(new int[] {nextY, nextX, target[2]});
					originalMap[nextY][nextX] = 0;
					map[nextY][nextX][0] = islandIndex - 1;
				}
			}
		}
	}
	
	static boolean isItEndOfIsland(int originY, int originX) {
		for (int direction = 0; direction < 4; direction++) {
			int y = originY + dy[direction];
			int x = originX + dx[direction];
			if (map[y][x][0] == 0) {
				return true;
			}
		}
		return false;
	}
	
	static void findShortestBridge() {
//		System.out.println(bridgeNodes.size());
		int depth = 0, previousDepth = 0;
		while (!bridgeNodes.isEmpty()) {
			
			while (!bridgeNodes.isEmpty() && depth - previousDepth == 0) {
				previousDepth = depth;
				int[] target= bridgeNodes.poll();
				int y = target[0], x = target[1], islandIndex = target[2];
				int bridgeLength = target[3];
				depth = bridgeLength;
				
				// 주변 다리 건설
				for (int direction = 0; direction < 4; direction++) {
					int nextY = y + dy[direction];
					int nextX = x + dx[direction];
					if (map[nextY][nextX][0] == 0) {
						map[nextY][nextX][0] = islandIndex;
						bridgeNodes.add(new int[] {nextY, nextX, islandIndex, bridgeLength + 1});
						map[nextY][nextX][1] = bridgeLength + 1;
						int tempResult;
						if ((tempResult = isItReached(nextY, nextX, islandIndex, bridgeLength + 1)) > 0) {
							result = Math.min(result, tempResult);
						}
					}
				}
			}
			previousDepth = depth;
			if (result != 5000) {
				return ;
			}
		}
		// 큐에서 하나 꺼내서
		// 이친구가 어디 출신인지
		// 겹치는 칸 처리를 어떻ㄱ게 할 것인가?
		// 다음칸이 어디인지
		// 주변에 섬이나 다른 다리를 만나는지
		// 만나면 그 다리(섬)까지의 depth + 지금 depth -> 그게 답
	}
	
	static int isItReached(int y, int x, int islandIndex, int bridgeLength) {
		// 주변에 다른 다리나 섬이 있는가?
		int result = 5000;
		for (int direction = 0; direction < 4; direction++) {
			int nextY = y + dy[direction];
			int nextX = x + dx[direction];
			if (map[nextY][nextX][0] != -2 && map[nextY][nextX][0] != islandIndex && map[nextY][nextX][0] != 0) {
				result = Math.min(result, bridgeLength + map[nextY][nextX][1]);
//				System.out.println("check " + bridgeLength + "  " + map[nextY][nextX][1]);
//				System.out.printf("node y : %d x : %d idx : %d\n",y, x, islandIndex);
//				System.out.printf("next y : %d x : %d %s\n", nextY, nextX, Arrays.toString(map[nextY][nextX]));
//				for (int yy = 0; yy < map.length; yy++) {
//					for (int xx = 0; xx < map.length; xx++) {
//						System.out.printf("%d ", map[yy][xx][0]);
//					}
//					System.out.println();
//				}
//				System.out.println();
//				for (int yy = 0; yy < map.length; yy++) {
//					for (int xx = 0; xx < map.length; xx++) {
//						System.out.printf("%d ", map[yy][xx][1]);
//					}
//					System.out.println();
//				}
//				System.out.println("result : " + result);
			}
		}
		if (result == 5000) {
			return 0;
		}
		return result;
	}
	// node 정보엔 뭐가 들어가야 하는가
	// bridge 노드 : 좌표, 길이, 어디 출신인지
	// island 노드 : 좌표, 어디 출신(섬인지)
}
