package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//후위표기식2_성공
public class Baekjoon_1935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Double> stack = new Stack<Double>();

		int count = Integer.parseInt(br.readLine());
		double result = 0;
		String str = br.readLine();
		double[] numArr = new double[count];

		for (int i = 0; i < count; i++) {
			numArr[i] = Integer.parseInt(br.readLine());
		}
		// 피연산자의 값 입력

		// ABC*+DE/-
		// count: 5 -> 12345

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				stack.add(numArr[str.charAt(i)-'A']); // 배열의 값과 피연산자 순서의 값이 같으면 자료에 추가
			} else {
				double num1 = stack.pop(); //여기서 두 수를 빼고
				double num2 = stack.pop(); //여기서 두 수를 빼고
				
				switch (str.charAt(i)) {
					case '+':
						result = num2 + num1;
						break;
					case '-':
						result = num2 - num1;
						break;
					case '*':
						result = num2 * num1;
						break;
					case '/':
						result = num2 / num1;
						break;
				}
				stack.add(result);
			}
		}
		System.out.printf("%.2f",result);
		br.close();
	}
}
