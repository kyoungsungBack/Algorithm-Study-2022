package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
//풍선터뜨리기_성공
public class Baekjoon_2346 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = Integer.parseInt(br.readLine());
		Deque<int[]> deque = new ArrayDeque<int[]>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int order = 0;// 이동횟수
		
		// 생성 deque.add(new int[] {index, 정수값})
		sb.append("1").append(" "); // 시작값은 이동횟수만 필요하므로
		order = Integer.parseInt(st.nextToken());

		for (int i = 2; st.hasMoreTokens(); i++) {
			deque.add(new int[] { i, Integer.parseInt(st.nextToken()) });
		}

		while (!deque.isEmpty()) {

			if (order > 0) {
				for (int j = 1; j < order; j++) {
					deque.addLast(deque.pollFirst());
				}

				int[] current = deque.pollFirst();
				sb.append(String.valueOf(current[0])).append(" ");
				order = current[1];

			} else {
				for (int j = 1; j < Math.abs(order); j++) {
					deque.addFirst(deque.pollLast());
				}

				int[] current = deque.pollLast();
				sb.append(String.valueOf(current[0])).append(" ");
				order = current[1];
			}

		}
		br.close();
		System.out.println(sb);
	}
}
