package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PS11055_MinBeom {
    static int[] arr;
    static int[] sum;
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int target = 0;
        arr = new int[size];
        sum = new int[size];
        for(int i=0; i<size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        target = num(size-1);
        System.out.println(target);
    }
    
    private static int num(int count) {
        sum[0] = arr[0];
        if(arr[0] >= arr[1]) {
            sum[1] = arr[1];
        }
        else {
            sum[1] = sum[0] + arr[1];
        }
        for(int i=2; i<=count; i++) {
        	sum[i] += arr[i];
            if(arr[i-1] >= arr[i]) {
            	for(int k=i-1; k>=0; k--) {
            		if(arr[k] < arr[i]) {
            			sum[i] += sum[k];
            			break;
            		}
            	}
            }
            else {
            	sum[i] += sum[i-1];
            	
            }
        }
        Arrays.sort(sum);
        return sum[count];
    }
}