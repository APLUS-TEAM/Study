package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main2346 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		LinkedList<int[]> list = new LinkedList<>();
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {

			int[] arr= new int[2];
			arr[0] = i+1;
			arr[1] = Integer.parseInt(st.nextToken());
			list.add(arr);

		}

		int index = 0;
		
		while (list.size()!=1) {
			
			sb.append(list.get(index)[0]+" ");
			int B = list.get(index)[1];
			list.remove(index);
			if (B>=0) index += B-1;
			else index += B;
			
			while (index<0) {
				index+=list.size();
			} 
			
			index = index%list.size();
			
		}
		
		sb.append(list.get(0)[0]);
		

		System.out.println(sb);

	}

}