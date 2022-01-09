package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

//프린터큐_성공 
public class Baekjoon_1966 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int testCase = Integer.parseInt(br.readLine()); // 테스트케이스 수

		for (int i = 0; i < testCase; i++) {
			st = new StringTokenizer(br.readLine());
			int cardNum  = Integer.parseInt(st.nextToken()); // 문서의 개수
			int cardIndex  = Integer.parseInt(st.nextToken()); // 궁금한 문서의 index
			int count = 0;
			
			LinkedList<int[]> que = new LinkedList<>();
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < cardNum ; j++) {
				que.add(new int[] { j, Integer.parseInt(st.nextToken())});
			}

			
			
			while (!que.isEmpty()) {

				int[] front = que.poll();
				boolean isMax = true; // 중요도 

				for (int j = 0; j < que.size(); j++) {

					if (front[1] < que.get(j)[1]) {

						que.add(front);
						for (int z = 0; z < j; z++) {
							que.add(que.poll());
						}

						isMax = false;
						break;
					}
				}

				if (isMax == false) {
					continue;
				}

				count++;
				if (front[0] == cardIndex ) {
					break;
				}
			}
			sb.append(count).append('\n');
		}
		br.close();
		System.out.println(sb);
		
	}
}