package Baekjoon_8주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Z_성공
// 입력 크기에 대해 사분면으로 나누고 재귀를 통해 해당 행과 열에 대한 값을 찾는다.
public class Baekjoon_1074 {
	static int N, R, C;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		N = (int) Math.pow(2, N);

		divide(R, C, N);
		System.out.println(ans);
	}

	static void divide(int r, int c, int n) {		
		// 재귀호출 종료조건
		if (n == 1) {
			return;
		}

		// n * n / 4: 입력 값에 대한 사분면의 첫번째 값
		// n * n / 4 * ?: ?는 현재 사분면에서 현재 위치의 값
		if (r < n / 2 && c < n / 2) {
			divide(r, c, n / 2);
		} else if (r < n / 2 && c >= n / 2) {
			ans += n * n / 4;
			divide(r, c - n / 2, n / 2);
		} else if (r >= n / 2 && c < n / 2) {
			ans += (n * n / 4) * 2;
			divide(r - n / 2, c, n / 2);
		} else {
			ans += (n * n / 4) * 3;
			divide(r - n / 2, c - n / 2, n / 2);
		}
	}
}
