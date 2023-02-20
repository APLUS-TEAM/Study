package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PS14916_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int change = Integer.parseInt(bf.readLine());
		int count = 0;
		if (change == 1 || change == 3) {
			count = -1;
		}
		else if((change%5 != 0) && ((change%5)&1)==1) {
			count = (change/5)-1;
			change = change%5 +5;
		}
		else {
			count = change/5;
			change = change%5;
		}
		if(count != -1) {
			count += change/2;
		}
		System.out.println(count);
	}
}