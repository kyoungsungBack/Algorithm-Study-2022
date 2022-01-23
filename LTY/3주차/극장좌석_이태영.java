package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class 극장좌석_이태영 {
    public static void main(String[] args) throws IOException {


        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        int N = Integer.parseInt(br.readLine()); //좌석의 개수
        int M = Integer.parseInt(br.readLine()); //고정석의 개수

        int[] count =new int[41]; //각 배열 요소에는 좌석의 개수만큼 나열할 수 있는 경우의 수를 담는다.
        //피보나치 형태를 띄므로
        //count[0] = 1; //
        count[1] =1;
        count[2] = 2;


        //모든 경우의수를 구해서 배열에 저장
        for(int i=3; i<=N; i++){
            count[i] = count[i - 1] + count[i - 2];
        }


        int result = 1;

        //vip 좌석을 제외한 나머지 구간의 경우의 수를 서로 곱한다.
        int seat = 0;
        for(int i=0; i<M; i++){
            int temp = Integer.parseInt(br.readLine()); //VIP 좌석 번호를 받는다.
            result = result * count[temp - seat - 1]; //구간의 경우의 수를 result에 곱해서 값 저장
            seat = temp; //seat값 갱신후 반복문을 다시 돌린다.
        }


        //마지막 구간의 경우의 수를 곱해준다.
        result = result * count[N - seat];

        System.out.println(result);
    }
}
/*
* Dynamic Programming
- 큰 문제를 작은 문제로 나누어 푸는 문제를 일컫는 말.
- Divide and Conquer(분할정복)과 다른점 : 작은 문제가 중복이 일어나는지 안일어나는지가 차이.
- 분할 정복은 문제를 분할했을 때 겹치는 문제가 발생하지 않지만, DP는 겹치는 문제가 발생하기 때문에 메모이제이션 등이 필요.

* Dynamic Programming의 조건
- 작은 문제가 반복이 일어나는 경우
- 같은 문제는 구할 때마다 정답이 같다.


* 탑다운(Top-down)
- 가장 먼저 호출하는 문제는 가장 큰 문제이기 때문에 이런 명칭이 붙는다.
- 탑다운 방식의 장점은 가독성이 좋고, 본래 점화식을 이해하기 쉽다.
*


* 바텀업(반복문 사용)
- 가장 작은 문제들로부터 차례차례 답을 쌓아 올려가기 때문에 이런 명칭이 붙음
- 바텀업 방식의 장점은 함수를 별개로 부르지 않아 시간과 메모리를 소량 더 절약할 수 있다는 점이 있다.

*문제 풀이 방식
- VIP 좌석을 제외한 좌석들의 경우의 수를 서로 곱한다.
- 몇가지 케이스에 대해 귀납적으로 풀어보면 규칙을 찾을 수 있음.
* https://blog.naver.com/occidere/220854811310
- n의 값 증가에 따라 1,2,3,5,8 이런식으로 피보나치 수열.
 */