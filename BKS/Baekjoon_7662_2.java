package Baekjoon_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

//이중 우선순위 큐_성공

// I n(정수) -> 정수 삽입
// D 1 -> 최댓값 삭제
// D -1 -> 최솟값 삭제
// 삭제하는 연산에서 최댓값(최솟값)이 둘 이상인 경우, 하나만 삭제됨
// 최종 출력에 비었으면 EMPTY 출력
// 값이 있다면 최댓값과 최솟값 출력

public class Baekjoon_7662_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); // key와 중복의 개수

		int testCase = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < testCase; i++) {
			int orderCount = Integer.parseInt(br.readLine());

			for (int j = 0; j < orderCount; j++) {
				st = new StringTokenizer(br.readLine());
				String order = st.nextToken();
				int num = Integer.parseInt(st.nextToken());

				if (order.equals("D") && num > 0) { // 최댓값 삭제
					if (!map.isEmpty()) {
						int key = map.lastKey(); // 해당 키
						int value = map.get(key); // 중복 개수

						if (value > 1) { // 그 외면 value 1감소
							map.replace(key, value - 1);
						} else { // 1개면 제거
							map.remove(key);
						}
					}
				} else if (order.equals("D") && num < 0) { // 최솟값 샂제
					if (!map.isEmpty()) {
						int key = map.firstKey();
						int value = map.get(key);

						if (value > 1) { // 그 외면 value 1감소
							map.replace(key, value - 1);
						} else { // 1개면 제거
							map.remove(key);
						}
					}
				} else if (order.equals("I")) { // 삽입
					if (map.containsKey(num)) {// 이미 있을때
						map.replace(num, map.get(num) + 1);
					} else {
						map.put(num, 1);
					}
				}
			}

			if (map.isEmpty()) {
				// EMPTY 출력
				sb.append("EMPTY").append("\n");
				map.clear();
			} else {
				// 최대최소 출력
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
				map.clear();
			}
		}

		br.close();
		System.out.print(sb);
	}
}
