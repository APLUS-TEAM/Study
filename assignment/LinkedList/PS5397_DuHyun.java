package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main5397 {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int TC = Integer.parseInt(br.readLine());
        for(int i = 0; i < TC; i++) {
        	LinkedList<Character> lnklist = new LinkedList<>();
        	
        	// 기존 방식으로는 시간 초과 떠서 ListIterator 생성 
        	// ListIterator(반복자)
        	// 단순히 linkedlist 사용시 인덱스까지의 접근이 오랜 시간이 걸려서 
        	// 반복자 사용 or
        	
            ListIterator<Character> list = lnklist.listIterator();
            
            String st = br.readLine();
            
            // 풀이 방식은 linkedlist와 거의 유사하다.
            
            for(int j = 0; j < st.length(); j++) {
                char c = st.charAt(j);
                switch(c) {
                    case '<' :
                        if(list.hasPrevious()) {
                        	// 커서 전으로 이동
                            list.previous();
                        }
                        break;
                    case '>' :
                        if(list.hasNext()) {
                        	// 커서 앞으로 이동 
                            list.next();
                        }
                        break;
                    case '-' :
                        if(list.hasPrevious()) {
                            list.previous();
                            list.remove();
                        }
                        break;
                    default : 
                        list.add(c);
                }
            }
            
            StringBuilder sb = new StringBuilder();
            for(char s : lnklist) {
                sb.append(s);
            }
            System.out.println(sb.toString());
        }
    }
}