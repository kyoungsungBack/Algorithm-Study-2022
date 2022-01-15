package Baekjoon_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

//절댓값 힙_성공

// 양수이면 que1에 저장
// 음수이면 que2에 저장
// 0이면 map의 최솟값 출력
// 	이때, 0이 최소면 입력값 출력
public class Baekjoon_11286 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> que1 = new PriorityQueue<>();// 양수 저장 큰
		PriorityQueue<Integer> que2 = new PriorityQueue<>(Collections.reverseOrder());// 음수 저장

		for (int i = 0; i < count; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num > 0) { // 양수일 때
				que1.add(num);
			} else if (num < 0) {// 절댓값이 가장 작은 값을 출력
				que2.add(num); // 음수일 때
			} else { // 0일 때
				if (!que1.isEmpty() && !que2.isEmpty()) {
					if (que1.peek() >= Math.abs(que2.peek())) {
						sb.append(que2.poll()).append("\n");
					} else {
						sb.append(que1.poll()).append("\n");
					}
				} else if (que1.isEmpty() && !que2.isEmpty()) {
					sb.append(que2.poll()).append("\n");
				} else if (!que1.isEmpty() && que2.isEmpty()) {
					sb.append(que1.poll()).append("\n");
				} else {
					sb.append(num).append("\n");
				}
			}
		}
		br.close();
		System.out.print(sb);
	}
}
