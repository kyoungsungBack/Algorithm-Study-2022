package Baekjoon_3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1669 {
	static int X, Y;
	static int days;
	static int addFirst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		days = 0;
		addFirst = 0;

		while (true) {
			// 1. 원숭이와 강아지 키가 같을 때
			if (X == Y) {
				break;
			} // 2. 원숭이의 키가 강아지 키보다 클 때
			else if (X < Y) {
				int square = addFirst * addFirst;
				// 키차이가 제곱수보다 클 때
				if (Y - X < square) {
					days += 1;
					X += --addFirst;
				} // 키차이가 제곱수보다 작을 때
				else if (Y - X > square) {
					days += 1;
					X += ++addFirst;
				} // 키차이가 제곱수보다 같을 때
				else {
					days += 1;
					X += addFirst;
				}
			}
		}

		System.out.println(days);
	}

}
