package baek;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS1991_MinBeom {
	static Node node = new Node('A', null, null);
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		int testCase = Integer.parseInt(bf.readLine());
		for(int i = 0; i<testCase; i++) {
			st = new StringTokenizer(bf.readLine());
			char a = st.nextToken().charAt(0);	// 루트 노드(메인 or sub)
			char b = st.nextToken().charAt(0);
			char c = st.nextToken().charAt(0);
			addNode(node, a, b, c);
		}
		preOrder(node);
		sb.append('\n');
		inOrder(node);
		sb.append('\n');
		postOrder(node);
		System.out.println(sb);
	}


	static class Node{
		char data;
		Node left;
		Node right;
		Node(char data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	static void addNode(Node temp, char root, char left, char right) {
		if(temp.data==root) {
			if(left == '.') temp.left = null;
			else temp.left = new Node(left, null, null);
			if(right == '.') temp.right = null;
			else temp.right = new Node(right, null, null);
		}
		
		/*
         * 둘다 노드가 생성되어있는 경우
         * 다시 노드의 좌우를 탐색하러 들어감
         */
		else {
			if(temp.left != null) addNode(temp.left, root, left, right);
			if(temp.right != null) addNode(temp.right, root, left, right);
		}
	}
	
	 /*
     * 전위순회 root -> left -> right
     * level 순회와 헷갈리지 않게 조심
     * level 순회는 level에 따라 left에서 right로 순회한다
     */
	
	/*
     *  전위 순회는 출력문이 leftNode 조건문
     *  위에 선언된다
     */
	public static void preOrder(Node node) {
		if(node == null) return;
		sb.append(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	/*
     *   중위 순회는 출력문이 leftNode 조건문아래,
     *   rightNode 조건문 위에 선언된다
     */
	public static void inOrder(Node node) {
		if(node == null) return;
		inOrder(node.left);
		sb.append(node.data);
		inOrder(node.right);
	}
	
	/*
     *   후위 순회는 출력문이 모든 조건문 아래에 선언된다
     */
	public static void postOrder(Node node) {
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.data);
	}
}