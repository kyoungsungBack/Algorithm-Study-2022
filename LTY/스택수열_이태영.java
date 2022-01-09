package data_strcuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 스택수열_이태영 {

    //다시 풀어보기
    public static void main(String[] args) throws IOException {
        int n;

        /*
        스택에 수를 넣을때 반드시 오름차순

         */
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();//정답 출력을 위해

        n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack();

        int start = 0;


        //n번 반복
        for(int i=0; i<n; i++){
            int value = Integer.parseInt(br.readLine());

            if(value > start){
                //start + 1부터 입력받은 value까지 push
                for(int j = start+1; j<=value; j++){
                    stack.push(j);
                    sb.append('+').append('\n');

                }
                start = value; // 다음 push할 때의 오름차순을 유지하기 위한 변수 초기화
            }

            //top에 있는 원소가 입력받은 값과 같지 않은 경우
            else if(stack.peek() != value){
                System.out.println("NO");
                System.exit(0);
            }

            stack.pop();
            sb.append('-').append('\n');
        }

        System.out.println(sb);

    }
}
