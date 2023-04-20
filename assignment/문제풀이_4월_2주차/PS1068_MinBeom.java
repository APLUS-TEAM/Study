import java.util.*;
import java.io.*;
public class PS1068_MinBeom {
	static ArrayList<Integer>[] arr;
	static int deleteNum;
	static int count = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		arr = new ArrayList[N];
		for(int i=0; i<N; i++) arr[i] = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int startNum = 0;
		for(int i=0; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num == -1) startNum = i;
			else arr[num].add(i);
		}
		deleteNum = Integer.parseInt(bf.readLine());
		dfs(startNum);
		System.out.println(count);
	}
	static void dfs(int startNum) {
		if(startNum == deleteNum) return;
		else if(arr[startNum].size() == 0) {
			count++;
			return;
		}
		for(int i=0; i<arr[startNum].size(); i++) {
			if(arr[startNum].get(i) == deleteNum) {
				if(arr[startNum].size()-1 == 0) count++;
				continue;
			}
			else dfs(arr[startNum].get(i));
		}
	}
}
