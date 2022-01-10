package test;

import java.util.Scanner;
import java.util.Stack;


public class Test1 {
    public static void main(String[] args) {
       Scanner sc =new Scanner(System.in);
       String str =new String();
       int n = sc.nextInt();
       sc.nextLine();
       str = sc.nextLine();
       int[] arrnum = new int[n];

       for(int i=0;i<n;i++){
           arrnum[i] = sc.nextInt();
       }
       //변수 개수 입력 , 후위표기식 입력 , 변수와 대응되는 수입력



       double result = run(str,n,arrnum);
        System.out.printf("%.2f",result);

    }
    static double run(String str, int n,int[] arrnum){
        Stack<Double> st =new Stack<>();
        //후위 표기식 계산을 위한 스택
        for (int i = 0; i < str.length(); i++) {
            //후위표기식 처음부터 끝까지 접근

            //현재 들어오는게 알파벳이면 알파벳을 숫자로 변환후 스텍에 저장
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                //대응 되는 숫자 를 찾아서 스텍에 저장
                st.push((double) arrnum[str.charAt(i) - 'A']);
            }
            //알파벳이 아니면 연산자이므로 연산자에 맞는 계산후 스텍에 삽입
            else {
                //스텍에서 2개를 뺸후 연산자에 맞게 계산하는 calcul 함수를 써서 계산된값을 스텍에 다시 삽입
                st.push(calcul(st.pop(), st.pop(), str.charAt(i)));
            }
        }
        //마지막에 스텍에 남은 값이 최종 결과값
        return st.pop();
    }

    //연산자에 맞게 계산하는 함수
    static double calcul(double b, double a, char op) {
        //b에는 스텍에서 뽑은 첫번째 a에는 두번쨰 값이 들어오므로
        // 실제로 계산되는건 두번째 뽑힌값이 앞에잇는 형태가 되야하므로 아래와 같이 계산
        if (op == '+') return a + b;
        else if (op == '-') return a - b;
        else if (op == '*') return a * b;
        else return a / b;
    }

}
