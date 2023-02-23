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
        	
        	// ���� ������δ� �ð� �ʰ� ���� ListIterator ���� 
        	// ListIterator(�ݺ���)
        	// �ܼ��� linkedlist ���� �ε��������� ������ ���� �ð��� �ɷ��� 
        	// �ݺ��� ��� or
        	
            ListIterator<Character> list = lnklist.listIterator();
            
            String st = br.readLine();
            
            // Ǯ�� ����� linkedlist�� ���� �����ϴ�.
            
            for(int j = 0; j < st.length(); j++) {
                char c = st.charAt(j);
                switch(c) {
                    case '<' :
                        if(list.hasPrevious()) {
                        	// Ŀ�� ������ �̵�
                            list.previous();
                        }
                        break;
                    case '>' :
                        if(list.hasNext()) {
                        	// Ŀ�� ������ �̵� 
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