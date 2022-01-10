package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
//쇠막대기_성공
public class Baekjoon_10799 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<String> que = new LinkedList<String>();
		String str = br.readLine();
		int pipe = 0;
		int pipeTotal = 0;
		boolean isOpend = true;

		for (int i = 0; i < str.length(); i++) {
			que.add(String.valueOf(str.charAt(i)));
		}

		while (!que.isEmpty()) {
			if (que.peek().equals("(")) {
				pipe += 1;
				que.poll();
				isOpend = true;
			} else if (que.peek().equals(")") && isOpend) { // 아직 막대기의 끝이 아닐 때
				pipe -= 1;
				pipeTotal += pipe;
				que.poll();
				isOpend = false;
			} else if (que.peek().equals(")") && !isOpend) { // 막대기의 끝일 때
				pipe -= 1;
				pipeTotal += 1;
				que.poll();
				isOpend = false;
			}
		}
		
		br.close();
		System.out.println(pipeTotal);
	}
}
