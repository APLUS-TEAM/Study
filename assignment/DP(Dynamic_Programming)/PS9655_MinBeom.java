package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PS9655_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int rockSize = Integer.parseInt(bf.readLine());
		if((rockSize&1) == 1) {
			System.out.println("SK");
		}
		else {
			System.out.println("CY");
		}
	}
}
