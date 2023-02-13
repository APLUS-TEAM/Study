package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PS2447_duhyun {
	
	static char[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		arr = new char[N][N];
		for (int i=0; i<N;i++) {
			for (int j=0;j<N;j++) {
				arr[i][j]=' ';
			}
		}
		def(0,0,N);
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				sb.append(arr[i][j]);
			}

				sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	// x��ǥ, y ��ǥ�� �־�������, N*N�� ���� ������ ���� ��� �Լ�
	// 
	public static void def(int x, int y, int N) {
		
		if (N==1) {
			arr[x][y]='*';
		} else if (N==3) {
			int cnt1=0;
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					cnt1++;
					if (cnt1==5) arr[x+i][y+j]=' ';
					else arr[x+i][y+j]='*';
				}
			}
		} else {
			
			int cnt2=0;
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					cnt2++;
					// 5��° ĭ�� �������� ��������.
					if (cnt2==5) {
						for (int k=x+(N/3)*i;k<x+(N/3)*2;k++) {
							for (int l=x+(N/3)*i;l<x+(N/3)*2;l++) {
								arr[k][l]=' ';
							}
						}
					}
					else def(x+(N/3)*i,y+(N/3)*j,N/3);
				}
			}
		}

	}

}