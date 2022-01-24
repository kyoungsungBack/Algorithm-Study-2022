package Baekjoon_3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1669_2 {
	static int X, Y;
	static long days;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		int diff = Y - X; // 원숭이와 강아지의 키차이
		long num = 0; // 최소제곱의 n값
		long square = 0; // 최소제곱 값

		// 1. 키차이가 없는 경우
		if (diff == 0) {
			System.out.println(0);
			return;
		}

		// 2. 최소제곱 값을 구한다.
		while (square < diff) {
			num++;
			square = num * num;
		}

		if (diff < square) {
			num--;
		}

		// 3. 최소제곱 수를 구한 뒤 최소 일 수를 저장한다.
		days = num * 2 - 1;
		diff -= num * num;

		// 4. 추가로 발생할 일 수를 구한다.
		while (diff > 0) {
			if (diff >= num) {
				diff -= num;
				days++;
			} else {
				diff -= diff;
				days++;
			}
		}

		System.out.println(days);
	}
}
