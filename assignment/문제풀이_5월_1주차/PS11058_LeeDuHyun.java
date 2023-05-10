package day010;

import java.io.*;
import java.util.StringTokenizer;

public class Main11058 {
	
	static Long[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		arr = new Long[101];
		for (int i=1;i<7;i++) {
			arr[i] = (long)i;
		}
		
		System.out.println(def(N));		
	}

	
	private static long def(int n) {
		
		if (arr[n]!=null) return arr[n];
		else {
			long max= Math.max(def(n-3)*2, def(n-4)*3);
			max = Math.max(max,def(n-5)*4);
			
			return arr[n] = max;
		}
		


	}

}