package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
//스택수열_성공
public class Baekjoon_1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		
		int count = Integer.parseInt(br.readLine());
		int numOrder;
		int num = 1; // 오름차순

		for (int i = 1; i <= count; i++) {
			numOrder = Integer.parseInt(br.readLine());
			stack.add(numOrder);
		}

		while (!stack.isEmpty()) {
			stack2.add(stack.pop());
		}

		// stack 비웠고
		// stack2 역순으로 들어와있음 12578634

		while (!stack2.isEmpty()) {
			int temp = stack2.peek();
			if (stack.isEmpty()) {
				stack.add(num++);
				sb.append("+").append("\n");
				continue;
			}

			if (temp == stack.peek()) {
				stack2.pop();
				stack.pop();// 남아밌을 때 비어버리면 충돌남
				sb.append("-").append("\n");
			} else {
				if (stack2.peek() < stack.peek()) {
					break; // 수열을 만들 수 없는 경우
				} else {
					stack.add(num++);
					sb.append("+").append("\n");
				}
			}
		}

		if (stack2.isEmpty()) {
			System.out.print(sb.toString());
		} else {
			System.out.print("NO");
		}
	}
}
