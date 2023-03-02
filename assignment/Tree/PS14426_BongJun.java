package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class PS14426_BongJun {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int count = 0;
		Trie trie = new Trie();
		while (N-- > 0) { // 단어 append가 곧 trie 추가하는 식
			trie.append(br.readLine().toCharArray());
		}
		while (M-- > 0) { // 접두사 발견시 1 반환, 아니면 0 반환
			count += trie.find(br.readLine().toCharArray());
		}
		System.out.println(count);
	}
	
	static class Trie {
		Node[] firstCha = new Node[26];
		
		void append(char[] word) {
			Node[] next = firstCha;
			int size = word.length;
			for (int i = 0; i < size - 1; i++) {
				int index = word[i] - 'a';
				if (next[index] == null) { // 해당 노드가 null = 새로운 분기 추가
					next[index] = new Node(word[i]);
				} else { // 이전 단어 등록에서 isItEnd가 true 되어있을 수 있으므로 갱신
					next[index].isItEnd = false;
				}
				next = next[index].next;
			}
			int index = word[size - 1] - 'a'; // 마지막 글자는 end 처리해줘야함으로 따로 뺌
			if (next[index] == null) {
				next[index] = new Node(word[size - 1]);
				next[index].isItEnd = true;
			}
		}
		
		int find(char[] word) {
			Node[] next = firstCha;
			int size = word.length;
			for (int i = 0; i < size; i++) {
				int index = word[i] - 'a';
				if (next[index] == null) { // 노드가 없음 = 문자열이 끊김
					return 0;
				}
				next = next[index].next;
			}
			return 1;
		}
		
	}
	
	static class Node {
		char cha;
		boolean isItEnd;
		Node[] next = new Node[26];
		Node(char cha) {
			this.cha = cha;
		}
	}
}