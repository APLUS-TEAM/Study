package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class PS2346_MinBeom {
    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(bf.readLine());
        Deque<int[]> deq = new ArrayDeque<>();
        int[] arr = new int[testCase];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0; i<testCase; i++) {
            arr[i]= Integer.parseInt(st.nextToken());
        }
        int num = arr[0];
        for(int i=1; i<testCase; i++) {
        	deq.add(new int[] {(i+1), arr[i]});
        }
        sb.append(1).append(" ");
        while(!deq.isEmpty()) {
            if(num>=1) {
                for(int k=1; k<num; k++) {
                    deq.addLast(deq.pollFirst());
                }
                int[] arr2 = deq.poll();
                num = arr2[1];
                sb.append(arr2[0]).append(" ");
            }
            else {
                for(int k=1; k<-num; k++) {
                    deq.addFirst(deq.pollLast());
                }
                int[] arr2 = deq.pollLast();
                num = arr2[1];
                sb.append(arr2[0]).append(" ");
            }
        }
        System.out.println(sb);
    }
}