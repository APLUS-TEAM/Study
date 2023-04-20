import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static Node[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int root = -1; // 부모 주어지지 않을 경우 있나 해서 -1 초기화했는데 그런 경우는 없었음
		tree = new Node[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new Node(i);
		}
		for (int i = 0; i < N; i++) {
			int parentNode= Integer.parseInt(st.nextToken()); // i번째 노드의 부모가 parentNode이다.
			if (parentNode == -1) {
				root = i;
				continue;
			}
			tree[parentNode].add(tree[i]);
		}
		st = new StringTokenizer(br.readLine());
		int target = Integer.parseInt(st.nextToken());
		if (target == root) { // 제거 대상이 루트노드일 경우
			System.out.println(0);
			System.exit(0);
		}
		cutNode(tree[root], target);
		System.out.println(leafCheck(tree[root]));
	}
	
	static void cutNode(Node node, int target) {
		int size = node.children.size();
		if (size == 0) { // 자식이 없으면 재귀 종료
			return ;
		}
		for (int i = 0; i < size; i++) {
			if (node.children.get(i).index == target) {
				node.children.remove(i);
				return ;
			}
		}
		for (Node child : node.children) {
			cutNode(child, target);
		}
	}
	
	static int leafCheck(Node node) {
		int size = node.children.size();
		if (size == 0) { // 자식이 없다 == 리프노드다
			return 1;
		}
		int sum = 0;
		for (Node child : node.children) {
			sum += leafCheck(child);
		}
		return sum;
	}
}

class Node {
	int index;
	ArrayList<Node> children = new ArrayList<>();
	Node(int index) {
		this.index = index;
	}
	void add(Node child) {
		children.add(child);
	}
}
