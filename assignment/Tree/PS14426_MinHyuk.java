package boj;

import java.io.*;
import java.util.*;
public class PS14426_MinHyuk {
    static class Node {
        int[] child;
		public Node() {
        	child = new int[26];
            for(int i = 0; i < 26; i++) 
                child[i] = -1;
        }
    }
    static List<Node> list = new ArrayList<>();
    static class Trie {
        int init() {
            list.add(new Node());
            return list.size()-1;
        }
		void add(int idx, String str, int depth) {
            if(str.length() == depth) return;
            int ch = str.charAt(depth) - 'a';
            if(list.get(idx).child[ch] == -1) 
                list.get(idx).child[ch] = init();
            add(list.get(idx).child[ch], str, depth+1);
        }
        boolean search(int idx, String str, int depth) {
            if(idx == -1) return false;
            if(str.length() == depth) return true;
            return search(list.get(idx).child[str.charAt(depth) - 'a'], str, depth+1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Trie trie = new Trie();
        trie.init();
        int cnt = 0;
        while(N--> 0)
            trie.add(0, br.readLine(), 0);
        while(M--> 0)
            if(trie.search(0, br.readLine(), 0)) cnt++;
        System.out.println(cnt);
    }
}
