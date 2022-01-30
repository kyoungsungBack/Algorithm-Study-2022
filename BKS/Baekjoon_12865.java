package Baekjoon_4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * @author kyoungsungBack
 * BAEKJOON_12865: 평범한 배낭
 * DP(동적계획법)을 냅색(Knapsack) 알고리즘을 이용하여 풀이하는 문제이다.
 *
 */
public class Baekjoon_12865 {
	static class Item {
		int weight;
		int value;

		public Item(int weight, int value) {
			super();
			this.weight = weight;
			this.value = value;
		}
	}

	static int N, K;
	static int[][] DP;
	static Item[] Items;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 물건의 수
		K = Integer.parseInt(st.nextToken()); // 준서가 버틸 수 있는 무게

		DP = new int[N + 1][K + 1]; // 무게에 따른 최대 무게를 저장할 배열 [물건정보][무게]
		Items = new Item[N + 1]; // 물건을 담아둘 아이템 배열

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			Items[i] = new Item(weight, value);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				// 현재 무게가 item의 무게보다 크거나 같을 때
				if (Items[i].weight <= j) {
					DP[i][j] = Math.max(DP[i - 1][j], Items[i].value + DP[i-1][j - Items[i].weight]);

				} // 현재 무게가 item의 무게보다 작을 때
				else {
					DP[i][j] = DP[i - 1][j];
				}
			}
		}
		System.out.println(DP[N][K]);
	}
}
