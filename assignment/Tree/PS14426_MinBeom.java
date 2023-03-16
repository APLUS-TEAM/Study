package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class PS14426_MinBeom {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int testStr = Integer.parseInt(st.nextToken());
		int containStr = Integer.parseInt(st.nextToken());
		int count = 0;
		TrieClass trie = new TrieClass();
		for(int i=0; i<testStr; i++) {
			trie.insert(bf.readLine());
		}
		for(int i=0; i<containStr; i++) {
			if(trie.find(bf.readLine())) {
				count++;
			}
		}
		System.out.println(count);
	}
	
	static class TrieClass {
		Node root;
		static final int ALPHABET_SIZE = 26;
		public TrieClass() {
			this.root = new Node();
			this.root.val = ' ';
		}
		
		static class Node {
			Node[] child = new Node[ALPHABET_SIZE];
			boolean isTerMinel = false;
			int childNum = 0;
			char val;
		}
		private int charToInt(char c) {
			return c - 'a';
		}
		
		public void insert(String str) {
			int length = str.length();
			Node current = this.root;
			for(int i=0; i<length; i++) {
				char c = str.charAt(i);
				int num = this.charToInt(c);
				if(current.child[num] == null) {
					current.child[num] = new Node();
					current.child[num].val = c;
					current.childNum++;
				}
				current = current.child[num];
			}
			current.isTerMinel = true;
		}
		
		public boolean find(String str) {
			int length = str.length();
			Node current = this.root;
			for(int i=0; i<length; i++) {
				char c = str.charAt(i);
				int num = this.charToInt(c);
				if(current.child[num] == null) {
					return false;
				}
				current = current.child[num];
			}
			return current != null;
		}
	}
}
