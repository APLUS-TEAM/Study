package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class baek17478{
	static int inputNum = 0;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		inputNum = Integer.parseInt(bf.readLine());
		bw.write("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		talk(inputNum);
		bw.close();
	}
	
	public static String talk(int k) throws IOException {
		line(k);
		bw.write("\"재귀함수가 뭔가요?\"\n");
		if(k==0) {
			line(k);
			bw.write("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			for(int i=0; i<=inputNum; i++) {
				line(k+i);
				bw.write("라고 답변하였지.\n");
			}
			return "";
		}
		line(k);
		bw.write("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		line(k);
		bw.write("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		line(k);
		bw.write("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		
		return talk(k-1);
	}
	
	public static void line(int k) throws IOException {
		for(int i=0; i<inputNum-k; i++) {
			bw.write("____");
		}
	}
}