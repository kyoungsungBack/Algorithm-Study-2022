package Baekjoon_7주차;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 플로이드워셜_성공
// 플로이드워셜을 이용한 최단거리는 인접행렬을 이용해서 
// 모든 경로에 대한 최단거리를 한번에 구하는 방식이다.
public class Baekjoon_11404 {
	static int N, M;
	static int[][] Map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		Map = new int[N + 1][N + 1];

		int a, b, c;

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			// 입력값에 겹치는 거리가 생기므로 더 최단거리의 값으로 저장
			if (Map[a][b] == 0 || Map[a][b] > c) {
				Map[a][b] = c;
			}
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					// 중간 정점을 거치지 않는 경우
					// 1. 본인이 시작과 끝일 때
					// 2. 중간점이 본인일 때 (i != k && k != j)
					if (i != j && Map[i][k] != 0 && Map[k][j] != 0) {
						// 각 정점들에 대한 거리가 갱신되는 경우
						// 1. 중간 정점이 없으면 갈 수 없었던 경우
						// 2. 중간 정점을 거쳤을 때가 더 최단거리인 경우
						if (Map[i][j] == 0 || Map[i][j] > Map[i][k] + Map[k][j]) {
							Map[i][j] = Map[i][k] + Map[k][j];
						}
					}
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bw.append(Map[i][j] + " ");
			}
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}

}
