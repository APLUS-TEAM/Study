package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baek11729 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(bf.readLine());
		int a= num;
		sb.append((1<<a)-1).append("\n");
		hanoi(num,1,2,3);
		System.out.print(sb);
	}
	
	static void hanoi(int count, int place1, int place2, int place3) {
		if(count >20) {
			return;
		}
		if(count == 1) {
			sb.append(place1 + " " + place3 + "\n");	// 다이렉트로 꽂을때
			return;
		}
		hanoi(count-1, place1, place3, place2);
		sb.append(place1 + " " + place3+"\n");
		hanoi(count-1, place2, place1, place3);
	}
}
