import java.util.*;
import java.io.*;
public class PS1083_MinBeom {
	public static void main(String[] args) throws IOException{
		ArrayList<Integer> arr = new ArrayList<>();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i=0; i<N; i++) arr.add(Integer.parseInt(st.nextToken()));
		int S = Integer.parseInt(bf.readLine());
		for(int i=0; i<N ;i++) {
			if(S==0) break;
			int num = 0, idx = 0;
			for(int j=i; j<N; j++) {
				if(S+i<j) break;
				if(num < arr.get(j)) {
					num = arr.get(j);
					idx = j;
				}
			}
			arr.remove(idx);
			arr.add(i, num);
			S -= (idx - i);
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) sb.append(arr.get(i)).append(' ');
		System.out.println(sb);
	}
}