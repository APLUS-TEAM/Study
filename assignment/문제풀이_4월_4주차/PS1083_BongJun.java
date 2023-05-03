import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, S;
	static ArrayList<Integer> nums = new ArrayList<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
		}
		S = Integer.parseInt(br.readLine());
		
		for (int target = 0; target < N && S > 0; target++) {
			int max = nums.get(target);
			int maxIdx = target;
			for (int next = target + 1; next <= target + S && next < N; next++) {
				if (max < nums.get(next)) {
					max = nums.get(next);
					maxIdx = next;
				}
			}
			
			nums.remove(maxIdx);
			nums.add(target, max);
			S -= maxIdx - target;
		}
		
		for (int a : nums) {
			System.out.print(a + " ");
		}
	}
}