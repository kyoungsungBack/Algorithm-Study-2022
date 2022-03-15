package Baekjoon_8주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pro_소수구하기 {
	static int N, M;
	static String[] list;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 10진수 값
		M = Integer.parseInt(br.readLine()); // 변환하려는 진수
		String ans = "";
		// 나머지의 값이 변환된 수
		// 몫을 변환하려는 진수로 계속 나눠준다.
		while (N > 0) {
			ans = (N % M) + ans;
			N /= M;
		}
		System.out.println(ans);
		count = 0;

		while (ans.contains("00")) {
			ans = ans.replace("00", "0");
		}

		list = ans.split("0");
		
		// case1. 1일 경우 -> 소수가 아님
		for (String str : list) {
			if (str.equals("1")) {
				continue;
			}

			if (isPrime(Long.parseLong(str))) {
				count++;
			}
		}

		System.out.println(count);
	}

	static boolean isPrime(long num) {
		boolean ans = true;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				ans = false;
				break;
			}
		}
		return ans;
	}
}
