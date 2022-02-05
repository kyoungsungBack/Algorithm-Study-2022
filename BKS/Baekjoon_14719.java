package Baekjoon_5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 빗물_성공
public class Baekjoon_14719 {
	static int H, W;
	static int[] Input;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		Input = new int[W];
		result = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			Input[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < W - 1; i++) {
			int current = Input[i]; // 현재 벽의 높이
			int leftMax = current; // 왼쪽 벽의 최대높이
			int rightMax = current; // 오른쪽 벽의 최대높이
			
			// 왼쪽 최대벽 높이 탐색
			// 현재 위치의 한칸 왼쪽부터 왼쪽 끝까지 탐색
			for (int j = i - 1; j >= 0; j--) { 
				if (Input[j] > current) {
					leftMax = Math.max(leftMax, Input[j]);
				}
			}
			
			// 오른쪽 최대벽 높이 탐색
			for (int j = i + 1; j < W; j++) { 
				if (Input[j] > current) {
					rightMax = Math.max(rightMax, Input[j]);
				}
			}
			
			// 현재 벽보다 높은 벽이 양쪽에 있는 경우
			if (Math.min(leftMax, rightMax) > current) { 
				result += (Math.min(leftMax, rightMax) - Input[i]);
			}
		}
		System.out.println(result);
	}
}
