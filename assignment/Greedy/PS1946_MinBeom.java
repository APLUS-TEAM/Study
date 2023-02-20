package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS1946_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int count = 0;
		for(int a=1; a<=testCase; a++) {
			int person = Integer.parseInt(bf.readLine());
			count = 1;
			int doc = 0;
			int[] arr = new int[person+1];
			for(int i=1; i<=person; i++) {
				st = new StringTokenizer(bf.readLine());
				doc = Integer.parseInt(st.nextToken());
				arr[doc] = Integer.parseInt(st.nextToken());
			}
			int min = arr[1];
			
			for(int i=2; i<=person; i++) {
				if(min > arr[i]) {
					min = arr[i];
					count++;
				}
			}
			sb.append(count+"\n");
		}
		System.out.println(sb);
	}
}