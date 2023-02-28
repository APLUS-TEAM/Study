package codeTest.baekjoon;

import java.util.Scanner;

public class Q1991 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int treeNum = sc.nextInt(); // 노드의 개수
		sc.nextLine();

		Tree tree = new Tree(); // 트리 생성

		for (int i = 0; i < treeNum; i++) {
			
			String[] line = sc.nextLine().split(" "); // 3개의 변수가 공백을 기준으로 주어짐

			tree.createNode(line[0], line[1], line[2]); // 공백으로 분리한 문자열을 입력
			
		}
		
		tree.preOrder(tree.root);
		System.out.println();
		tree.inOrder(tree.root);
		System.out.println();
		tree.postOrder(tree.root);
		System.out.println();

	}
}

class Node {

	String data;
	Node left, right;

	Node(String data) {
		this.data = data;
	}

}

class Tree {

	Node root = new Node("."); // 초기 노드 생성

	// 초기 노드 입력
	void createNode(String mid, String left, String right) {
		if (root.data.equals(".")) { // 루트의 데이터가 . == 노드가 아무것도 없는 초기 노드 상태
			if (!mid.equals("."))
				root = new Node(mid);
			if (!left.equals("."))
				root.left = new Node(left);
			if (!right.equals("."))
				root.right = new Node(right);
		} else { // 초기 노드가 존재한다면 자식 노드를 설정한다
			find(root, mid, left, right);
		}
	}

	// 자식 노드 설정
	void find(Node node, String mid, String left, String right) {

		if (node == null) // 비어있다면 리턴
			return;

		else if (mid.equals(node.data)) { // 노드와 mid가 같다면 해당 노드의 왼쪽, 오른쪽 자식 노드 설정
			if (!left.equals(".")) // .이면 자식 노드가 없다
				node.left = new Node(left);
			if (!right.equals(".")) // .이면 자식 노드가 없다
				node.right = new Node(right);
		}

		// 노드와 mid가 다르다면 왼쪽, 오른쪽 자식 노드와 비교한다
		find(node.left, mid, left, right);
		find(node.right, mid, left, right);

	}

	// 전위 순회
	// 루트 노드 > 왼쪽 노드 > 오른쪽 노드 순서
	void preOrder(Node node) {
		System.out.print(node.data); // 루트 노드 출력

		if (node.left != null) // 왼쪽 자식 노드가 있따면
			preOrder(node.left); // 왼쪽 자식 노드로 순회

		if (node.right != null) // 오른쪽 자식 노드가 있다면
			preOrder(node.right); // 오른쪽 자식 노드로 순회
	}

	// 중위 순회
	// 왼쪽 노드 > 루트 노드 > 오른쪽 노드
	void inOrder(Node node) {
		if (node.left != null) // 왼쪽 자식 노드가 있다면
			inOrder(node.left); // 왼쪽 먼저 순회

		System.out.print(node.data); // 루트 노드 순회

		if (node.right != null) // 오른쪽 자식 노드가 있다면
			inOrder(node.right); // 오른쪽 자식 노드로 순회
	}

	// 후위 순회
	// 하위 트리를 모두 방문 후 루트 노드 순회
	// 왼쪽 서브 트리를 순회 > 오른쪽 서브 트리를 순회 > 루트 노드
	void postOrder(Node node) {
		if (node.left != null)
			postOrder(node.left);

		if (node.right != null)
			postOrder(node.right);

		System.out.print(node.data);
	}

}

