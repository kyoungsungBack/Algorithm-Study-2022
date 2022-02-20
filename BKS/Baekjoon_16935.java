package Baekjoon_7주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배열돌리기3_성공
// 1. 배열을 상하 반전시키는 연산
// 2. 배열을 좌우 반전시키는 연산
// 3. 배열값들은 90도 회전시키는 연산
// 4. 왼쪽으로 90도 회전시키는 연산 
// 5. 
public class Baekjoon_16935 {
	static int[][] Map;
	static int[][] MapCopy;
	
	static int N, M, R;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		Map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				Map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			int cal = Integer.parseInt(st.nextToken());
			
			switch(cal) {
			case 1:
				cal1();
				break;
			case 2:
				cal2();
				break;
			case 3:
				cal3();
				break;
			case 4:
				cal4();
				break;
			case 5:
				cal5();
				break;
			case 6:
				cal6();
				break;
					
			}
		}

		print();
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(MapCopy[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void cal1() {
		MapCopy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				MapCopy[N - i - 1][j] = Map[i][j];
			}
		}
		
		Map = MapCopy;
	}

	static void cal2() {
		MapCopy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				MapCopy[i][M - j - 1] = Map[i][j];
			}
		}
		
		Map = MapCopy;
	}

	static void cal3() {
		MapCopy = new int[M][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				MapCopy[j][N - 1 - i] = Map[i][j];
			}
		}

		int temp = N;
		N = M;
		M = temp;
		
		Map = MapCopy;

	}

	static void cal4() {
		MapCopy = new int[M][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				MapCopy[M - j - 1][i] = Map[i][j];
			}
		}
		
		int temp = N;
		N = M;
		M = temp;
		
		Map = MapCopy;

	}

	static void cal5() {
		MapCopy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < N / 2) {
					if (j < M / 2) {
						MapCopy[i][(M / 2) + j] = Map[i][j];
					} else {
						MapCopy[(N / 2) + i][j] = Map[i][j];
					}
				} else {
					if (j < M / 2) {
						MapCopy[i - (N / 2)][j] = Map[i][j];
					} else {
						MapCopy[i][j - (M / 2)] = Map[i][j];
					}
				}
			}
		}
		
		Map = MapCopy;
	}

	static void cal6() {
		MapCopy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i < N / 2) {
					if (j < M / 2) {
						MapCopy[(N / 2) + i][j] = Map[i][j];
					} else {
						MapCopy[i][j - (M / 2)] = Map[i][j];
					}
				} else {
					if (j < M / 2) {
						MapCopy[i][(M / 2) + j] = Map[i][j];
					} else {
						MapCopy[i - (N / 2)][j] = Map[i][j];
					}
				}
			}
		}
		
		Map = MapCopy;
	}
}
