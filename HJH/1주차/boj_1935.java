/**
 * https://www.acmicpc.net/problem/1935
 */

import java.io.*;
import java.util.*;

public class Main {
    private static Set<Character> operator = new HashSet<>();
    private static char[] inputs;
    private static Map<String, Integer> variables = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();

        Stack<String> stack = new Stack<>();

        for (var input : inputs) {
            if (!operator.contains(input)) {
                stack.push(Character.toString(input));
                continue;
            }

            var v2 = stack.pop();
            var v1 = stack.pop();

            double calculateResult = calculate(v1, v2, input);

            stack.push(Double.toString(calculateResult));
        }

        System.out.printf("%.2f", Double.parseDouble(stack.pop()));
    }

    private static double calculate(String v1, String v2, char operator) {
        double operand1 = variables.containsKey(v1) ? variables.get(v1) : Double.parseDouble(v1);
        double operand2 = variables.containsKey(v2) ? variables.get(v2) : Double.parseDouble(v2);

        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
        }

        throw new RuntimeException("예외");
    }

    private static void init() throws IOException {
        operator.add('+');
        operator.add('-');
        operator.add('*');
        operator.add('/');

        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        inputs = br.readLine().toCharArray();

        char curKey = 'A';
        while (n-- > 0) {
            variables.put(Character.toString(curKey++), Integer.parseInt(br.readLine()));
        }

        br.close();
    }

}
/**
 * 소요시간: 20분
 * 시간복잡도: O(N) : 입력 길이만큼 돌고 각 입력마다 특별히 N을 넘는 연산은 하지 않음
 * 공간복잡도: O(N)
 */
