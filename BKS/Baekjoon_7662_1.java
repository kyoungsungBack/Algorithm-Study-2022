package Baekjoon_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 이중 우선순위 큐_실패(시간초과)
public class Baekjoon_7662_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> que1 = new PriorityQueue<Integer>();// 오름차순
		PriorityQueue<Integer> que2 = new PriorityQueue<Integer>(Collections.reverseOrder());// 내림차순

		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int noMean = 0;
		// I n(정수) -> 정수 삽입
		// D 1 -> 최댓값 삭제
		// D -1 -> 최솟값 삭제
		// 삭제하는 연산에서 최댓값(최솟값)이 둘 이상인 경우, 하나만 삭제됨
		// 최종 출력에 비었으면 EMPTY 출력
		// 값이 있다면 최댓값과 최솟값 출력
 
		for (int i = 0; i < testCase; i++) {

			int orderCount = Integer.parseInt(br.readLine());

			for (int j = 0; j < orderCount; j++) {

				st = new StringTokenizer(br.readLine());
				String order = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				if (order.equals("D") && que1.isEmpty()) {
					noMean += 1;
				}

				if (order.equals("I")) { // 삽입
					que1.add(num);
					que2.add(num);

				} else if (order.equals("D") && !que1.isEmpty()) { // 삭제
					if (num < 0) { // 최솟값 삭제
						int maxNum = que1.remove();
						que2.remove(maxNum);
					} else if (num > 0) { // 최댓값 삭제
						int minNum = que2.remove();
						que1.remove(minNum);
					}
				}
			}

			if (que1.isEmpty()) {
				// EMPTY 출력
				sb.append("EMPTY").append("\n");
				que1.clear();
				que2.clear();
			} else {
				// 최대최소 출력
				sb.append(que2.peek()).append(" ").append(que1.peek()).append("\n");
				que1.clear();
				que2.clear();
			}
		}
		System.out.print(sb);
	}
}
