package Baekjoon_2주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;

//최대힙

// 0이 아니면 set에 저장
// 0이면 set의 최대값 출력
// 	이때, 0이 최대면 입력값 출력
public class Baekjoon_11279 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());// 1이상의 수 저장

		for (int i = 0; i < count; i++) {
			int num = Integer.parseInt(br.readLine());

			if (num != 0) {
				que.add(num);
			} else {
				if(que.isEmpty()) {
					sb.append(num).append("\n");
				}else {
					sb.append(que.poll()).append("\n");
				}
			}
		}
		br.close();
		System.out.print(sb);
	}
}
