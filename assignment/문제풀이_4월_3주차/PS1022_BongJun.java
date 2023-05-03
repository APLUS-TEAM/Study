import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static int r1, r2, c1, c2, correctionY, correctionX, ySize, xSize;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        
        ySize = r2 - r1 + 1;
        xSize = c2 - c1 + 1; 
        correctionY = 5000 + r1;
        correctionX = 5000 + c1;
        int[][] map = new int[ySize][xSize];
        int count = 0;
        int countEnd = (ySize) * (xSize);
        int max = 0;
        if (isInArray(5000, 5000)) {
        	count++;
        	map[5000 - correctionY][5000 - correctionX] = 1;
        	max = 1;

        }
        if (isInArray(5000, 5001)) {
        	count++;
        	map[5000 - correctionY][5001 - correctionX] = 2;
        	max = Math.max(max, 2);
        }
        int number = 3;
        int y = 4999, x = 5001;
        int size = 2;
        int d = 2;
        while (count < countEnd) {
            for (int dCount = 0; dCount < 2; dCount++) {
                for (int sCount = 1; sCount <= size; sCount++) {
                	if (isInArray(y, x)) {
                		count++;
//                        System.out.println(number + " " + count + " " + (y - correctionY) + " " + (x - correctionX));
                		map[y - correctionY][x - correctionX] = number;
                		max = Math.max(number, max);
                	}
                	
                    y += dy[d];
                    x += dx[d];
                    
                    number++;
                    // 위치확인
                }
                d = (d + 1) % 4;
            }
            size++;
        }
        int maxLength = sizeOfNumber(max);
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < ySize; r++) {
            for (int c = 0; c < xSize; c++) {
                int spaceCount = maxLength - sizeOfNumber(map[r][c]);
                while (spaceCount-- > 0) {
                    sb.append(' ');
                }
                if (c != 0) {
                	sb.append(' ');
                }
                sb.append(map[r][c]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    
    
    private static boolean isInArray(int y, int x) {
        int fixedY = y - correctionY;
        int fixedX = x - correctionX;
        return 0 <= fixedY && 0 <= fixedX && fixedY < ySize && fixedX < xSize;
    }


    private static int sizeOfNumber(int number) {
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}