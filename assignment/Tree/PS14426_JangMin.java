package codeTest.baekjoon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Q14426 {
	
	public static void main(String[] args) {
		
		Trie trie = new Trie();
		
		Scanner sc = new Scanner(System.in);
		
		// 집합에 포함된 문자열의 갯수
		int sentence = sc.nextInt();
		
		// 검사할 문자열의 갯수
		int check = sc.nextInt();
		sc.nextLine(); // 개행문자삭제
		
		// 문자열을 트라이에 미리 입력
		for(int i = 0; i < sentence; i++) {
			trie.insert(sc.nextLine());
		}
		
		// 접두사의 수 체크할 변수
		int num = 0;
		
		// 검사합시다
		for(int i = 0; i < check; i++) {
			if(trie.search(sc.nextLine())) num++;
		}
		
		// 출력
		System.out.println(num);
		
	}

}

class tNode{
	// 자식 노드
	Map<Character, tNode> childNode = new HashMap<>();
	// 단어의 끝을 체크
	boolean end;
	
}

class Trie{
	
	// rootNode를 생성해준다
	tNode rootNode = new tNode();
	
	// Trie에 문자열을 저장하는 메소드
	void insert(String str) {
		
		// 항상 rootNode부터 시작
		tNode node = this.rootNode;
		
		// 문자열의 각 단어마다 가져와서 자식노드 중에 있는지 체크하고,
		// 없으면 자식노드를 새로 생성한다
		for(int i = 0; i < str.length(); i++) {
			node = node.childNode.computeIfAbsent(str.charAt(i), key -> new tNode());
			// node에 str.charAt(i)가 없다면 그 key로 새로운 node를 생성한다
		}
		
		// 저장할 문자열의 마지막 철자의 노드에 단어의 끝임을 알린다
		node.end = true;
		
	}
	
	boolean search(String str) {
		
		// 루트노드부터 시작
		tNode node = this.rootNode;
		
		// 문자열의 각 단어마다 노드가 존재하는지 체크한다
		for(int i = 0; i < str.length(); i++) {
			
			// 문자열의 각 철자와 트라이의 노드가 일치하지 않으면 null이 되고, false를 반환한다
			node = node.childNode.getOrDefault(str.charAt(i), null);
			if(node == null) {
				// node가 null이면 현재 Trie에 해당 문자열이 없다
				return false;
			}
			
		}
		
		// 문자열의 마지막 단어까지 매핑된 노드가 존재한다해서 무조건 문자열이 존재하지 않는다
		// busy를 Trie에 저장했으면, bus의 마지막 s단어에 매핑 된 노드는 존재하지만 Trie에 저장된건 아님
		// 그러므로 현재 노드가 단어의 끝인지 아닌지 체크하는 변수로 리턴
		// ??? 뭐라는거야 << 문제에는 이게 안맞는디? 뭐야
		// 위의 주석은 접두사가 아닌 완전히 일치하는 문자열을 검사할 때 해당하는 주석이네요
		
//		return node.end; << 완전히 일치하는 문자열만을 체크하고 싶을때는 end를 리턴하면 된다
		return true; // 우리는 접두사만 체크하고 있으니 체크하면서 false를 리턴하지 않았다면 접두사에 해당한다
		
	}
	
	
}












