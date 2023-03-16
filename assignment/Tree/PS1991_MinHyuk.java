package boj;

import java.io.*;
public class PS1991_MinHyuk {
	static int N;
	static Node[] arr;
	static StringBuilder sb = new StringBuilder();
	static class Node {
		char val;
		Node left;
		Node right;
		public Node(char val) {this.val = val;}
	}
	static void preOrder(Node node) {
		if (node != null) {
			sb.append(node.val);
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	static void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			sb.append(node.val);
			inOrder(node.right);
		}
	}
	static void postOrder(Node node) {
		if (node != null) {
			postOrder(node.left);
			postOrder(node.right);
			sb.append(node.val);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new Node[N];
		for (int i = 0; i < N; i++) arr[i] = new Node((char)(i + 'A'));
		while(N-->0) {
			char[] str = br.readLine().toCharArray();
			char i = str[0];
			char j = str[2];
			char k = str[4];
			if (j != '.') arr[i-'A'].left = arr[j-'A'];
			if (k != '.') arr[i-'A'].right = arr[k-'A'];
		}
		preOrder(arr[0]);
		sb.append('\n');
		inOrder(arr[0]);
		sb.append('\n');
		postOrder(arr[0]);
		sb.append('\n');
		System.out.println(sb);
	}
}
