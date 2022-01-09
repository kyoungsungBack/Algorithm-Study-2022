package data_strcuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class 후위표기식2_이태영 {

    public static void main(String[] args) throws IOException {
        /*
        후위표기식 : 연산자를 피연산자 뒤에 놓는 방법.
        스택을 활용해서 다음과 같은 과정을 거쳐 변환
        1. 피연산자는 그대로 출력
        2. 연산자는 스택이 비어있으면 자신을 바로 추가
        3. stack의 top이 자신보다 우선순위가 낮은 연산자를 만날 때까지 pop하고 자신을 담는다.
        4. 여는 괄호는 닫는 괄호가 아니면 pop하지 않는다.
        5. 닫는 괄호가 나오면 여는 괄호가 나올 때까지 꺼내서 출력한다.
        6. 마지막에 도착하면 스택에서 차례로 꺼내서 출련한다.
        https://woongsios.tistory.com/288
         */


        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();

        //소수점 둘째 자리까지
        Stack<Double> stack = new Stack<>();


        //피연산자 개수
        int N = Integer.parseInt((br.readLine()));

        double[] num = new double[27]; // 27개의 공간만 만들면 되므로
        String li = br.readLine();

        for(int i=0; i<N; i++){
            int operand = Integer.parseInt(br.readLine());
            num[i] = (double)operand;
        }

        for(int i=0; i<li.length(); i++){
            int s = (int) li.charAt(i);

            //아스키코드 문자 A~z
            if(s >= 65 && s <=90){
                stack.push(num[s - 65]);
            }
            else{
                double num1 = stack.pop();
                double num2 = stack.pop();
                double result = 0;

                switch(s){
                    case 42:
                        result = num2*num1;

                        break;

                    case 43:
                        result = num2+num1;
                        break;

                    case 47:
                        result = num2/num1;
                        break;
                    case 45:
                        result =  num2 - num1;
                        break;
                }

                stack.push(result);
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}
