import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static final int TYPE = 0, COST = 1;
	static int[][] confirmedNumber;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> numberType = new TreeMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[10];
        for (int i = 0; i < N; i++) {
        	int value = Integer.parseInt(st.nextToken());
            numberType.put(value, i);
            arr[i] = value;
        }
        
        int M = Integer.parseInt(br.readLine());
        if (numberType.size() == 1) {
        	confirmedNumber = new int[numberType.size() + 1][2];
        	confirmedNumber[1][COST] = 99999;
        } else {
        	confirmedNumber = new int[numberType.size()][2];
        }
        int idx = 0;
        for (Integer cost : numberType.keySet()) {
        	confirmedNumber[idx][COST] = cost;
        	confirmedNumber[idx++][TYPE] = numberType.get(cost);
        }
        
        int[] result = new int[100];
        int money = 0;
        int length = 0;
        for (; money <= M; length++) {
        	if (length == 0 && confirmedNumber[0][TYPE] == 0) {
        		if (M < confirmedNumber[1][COST]) {
        			length = 2;
        			money += 5000;
        			break;
        		}
        		result[length] = confirmedNumber[1][TYPE];
        		money += confirmedNumber[1][COST];
        		continue;
        	}
        	result[length] = confirmedNumber[0][TYPE];
        	money += confirmedNumber[0][COST];
        }
        money -= confirmedNumber[0][COST];
        length--;
        
        money = M - money;
        for (int i = 0; money >= 0 && i < length; i++) {
        	for (int j = numberType.size() - 1; j > 0; j--) {
        		if (result[i] > confirmedNumber[j][TYPE]) continue;
        		if (money + arr[result[i]] >= confirmedNumber[j][COST]) {
        			money = money + arr[result[i]] - confirmedNumber[j][COST];
        			result[i] = confirmedNumber[j][TYPE];
        			break;
        		}
        	}
        }
        for (int i = 0; i < length; i++) {
        	System.out.print(result[i]);
        }
    }
}