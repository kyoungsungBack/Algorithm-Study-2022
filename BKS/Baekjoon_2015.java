package Baekjoon_6주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
// 수들의 합4(map)_성공
public class Baekjoon_2015 {
	static int N, K;
	static int[] nums;
	static int[] psum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		nums = new int[N + 1];
		psum = new int[N + 1];
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			psum[i] = psum[i - 1] + nums[i]; // 누적합들을 저장
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		long count = 0;
		
		for (int i = 1; i <= N; i++) {
			// psum[i]에 대해서 psum[i] = K 인 경우
			if(psum[i] == K) {
				count++;
			}
			// psum[i]에 대해서 psum[i] - psum[j] = K 이므로
			// psum[i]일 때 K를 성립하는 누적합 psum[j]의 개수를 구하는 것
			count += map.getOrDefault(psum[i] - K, 0); 
			// 누적합 psum[i]가 뒤의 수열에서도 psum[j]의 값으로 쓰이므로 
			// Key값을 psum[j] 빈도수를 Value로 넣어준다
			map.put(psum[i], map.getOrDefault(psum[i], 0) + 1);
		}
		System.out.println(count);
	}
}
