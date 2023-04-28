import java.io.*;
import java.util.*;
public class Baekjoon1082 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] price = new int[N]; // 숫자별 가격 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = -1; // 가장 싼 숫자 기록할 변수
		int min = Integer.MAX_VALUE; // 가장 싼 숫자의 가격 기록할 변수
		for (int i = 0; i < N; ++i) {
			price[i] = Integer.parseInt(st.nextToken());
			if (price[i] <= min) {
				min = price[i];
				num = i;
			}
		}
		int money = Integer.parseInt(br.readLine()); // 현재 가진 돈
		int idx = 0; // 방번호가 몇자리인지 기록할 인덱스
		int[] digit = new int[50]; // 방번호 기록 배열
		while (money - min >= 0) { // 가장 싼 숫자로 방번호를 full로 채움
			digit[idx++] = num;
			money -= min;
		}
		// 앞 쪽이 0이면 방번호 0으로 시작할 수 없기 때문에 어디까지 0인지 기록
		int zeroIdx = 0;
		for (int i = 0; i < idx; ++i) { // 방번호 돌면서
			for (int j = N-1; j >= 0; --j) { // 숫자는 뒤에서부터 (큰 수부터)
				if (price[j] <= money+min) { // 가장 싼 거 하나 팔고 지금 큰 수 살 수 있음?
					money += min; // 가장 싼 숫자 팔고
					money -= price[j]; // 지금 숫자 삼
					digit[i] = j; // 그럼 해당하는 위치의 방번호 교체
					break;
				}
			}
			if (digit[zeroIdx] == 0) { // 지금 맨앞쪽 0이면
				money += price[0]; // 숫자 0 팔고 돈 돌려받고
				++zeroIdx; // 다음 자리로
			}
		}
		if (idx == zeroIdx) System.out.println(0); // 방번호가 전부 0이면 0 출력
		else { // 아니면
			StringBuilder sb = new StringBuilder();
			// 0이 아닌 위치부터 방번호 출력
			for (int i = zeroIdx; i < idx; ++i) sb.append(digit[i]);
			System.out.println(sb);
		}
	}
}