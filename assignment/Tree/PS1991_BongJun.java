package Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PS1991_BongJun {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		Tree tree = new Tree();
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			tree.addNode(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
		}
		tree.preorderTraversal(tree.root);
		tree.inorderTraversal(tree.root);
		tree.postorderTraversal(tree.root);
		sb.append(tree.preSB + "\n").append(tree.inSB + "\n").append(tree.postSB);
		System.out.println(sb);
	}
}

class Tree {
	Node root;
	StringBuilder preSB = new StringBuilder();
	StringBuilder inSB = new StringBuilder();
	StringBuilder postSB = new StringBuilder();

	void addNode(char root, char left, char right) {
		if (this.root == null) {
			Node newRoot = new Node(root);
			newRoot.left = new Node(left);
			newRoot.right = new Node(right);
			this.root = newRoot;
			return;
		}
		Node addPlace = searchNode(this.root, root);
		addPlace.left = new Node(left);
		addPlace.right = new Node(right);
	}

	Node searchNode(Node start, char value) {
		if (start == null) {
			return null;
		}
		if (start.value == value) {
			return start;
		} else if (start.value == '.') {
			return null;
		}
		Node leftSearch = searchNode(start.left, value);
		if (leftSearch != null) {
			return leftSearch;
		}
		Node rightSearch = searchNode(start.right, value);
		if (rightSearch != null) {
			return rightSearch;
		}
		return null;
	}

	void preorderTraversal(Node start) {
		if (start == null) {
			return;
		}
		if (start.value != '.') {
			preSB.append(start.value);
		}
		preorderTraversal(start.left);
		preorderTraversal(start.right);
	}

	void inorderTraversal(Node start) {
		if (start == null) {
			return;
		}
		inorderTraversal(start.left);
		if (start.value != '.') {
			inSB.append(start.value);
		}
		inorderTraversal(start.right);
	}

	void postorderTraversal(Node start) {
		if (start == null) {
			return;
		}
		postorderTraversal(start.left);
		postorderTraversal(start.right);
		if (start.value != '.') {
			postSB.append(start.value);
		}
	}

	void printPre() {
		System.out.println(preSB);
	}

	void printIn() {
		System.out.println(inSB);
	}

	void printPost() {
		System.out.println(postSB);
	}
}

class Node {
	char value;
	Node left;
	Node right;

	Node(char value) {
		this.value = value;
	}
}