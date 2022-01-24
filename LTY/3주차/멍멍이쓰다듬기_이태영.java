package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 멍멍이쓰다듬기_이태영 {
    public static void main(String[] args) throws IOException {

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int key_diff = Y-X; // 키 차이
        int n=0;

        if(key_diff==0){
            System.out.println(0);
        }

        while(true){
            if(n*n>=key_diff)
                break;
            else
                n++;
        }

        if(n*n>key_diff)
            n = n-1;

        int result = 2*n-1;
        key_diff = key_diff - (n * n);



        while(key_diff>0){
            key_diff = key_diff - Math.min(n, key_diff);
            result++;
        }

        System.out.println(result);
    }
}
//        while(true){
//            if(key_diff==0)
//                break;
//            else{
//                key_diff = key_diff - Math.min(n, key_diff); //n값을 넘지 않고 key_diff값을 넘지 않는 조건에서 key_diff를 갱신
//                result++;
//            }
//
//        }


/*
대칭의 형태
1 2 3 4 5 6 .. N .. 5 4 3 2 1 이런식으로 키가 커야한다.
이 수열의 합은 가장 큰 가운데 수가 N이라고 할 때 N^2
- 둘의 키 차이가 N^2이라면, 둘을 같게 하기 위해서 2*N-1일만큼 필요

키 차이가 N^2인 경우는 쉽게 풀수 있지만 그렇지 않은 경우도 존재.
- 이 경우 키의 차이에서 가장 큰 제곱수를 빼고
1~ N 중에서 하나씩 빼야 한다.
예를 들어, 키 차이가 6cm라면, 6보다 작은 최대 제곱수인 4cm가 만드는 수열 [1, 2, 1]에 2를 추가해 [1, 2, (2), 1]로 만들 수 있다.
예를 들어, 키 차이가 10cm라면, 10보다 작은 최대 제곱수인 9cm가 만드는 수열 [1, 2, 3, 2, 1]에 1을 추가해 [1, 2, 3, 2, (1), 1]로 만들 수 있다.


위에서 설명한 추가해야되는 수는 다음과 같이 구할 수 있다.
위 그림에서, 7cm(파란색)의 키 차이를 **동일하게 하려면, 7보다 작은 최대 제곱수인 **4cm(빨간색)를 이용해야 한다는 것을 알 수 있다. 그 둘의 차이는 3cm가 되고, 따라서 이틀동안 2cm와 1cm를 각각 추가해 주어야 한다.
4cm가 만드는 수열은 [1, 2, 1]로, 수열 내 최대 값은
√4=2 가 되고, 남은 3cm만큼의 차이를 좁히기 위해서는 2이하의 수만 이용할 수 있다. 하루에 사용할 수 있는 가장 큰 값 순서대로 남은 키 차이를 좁혀갈 수 있다.
따라서, 남은 3cm를 수열 내 사용할 수 있는 가장 큰 수 2로 채우고, 그 후에 남은 1cm를 수열 내 사용할 수 있는 1로 채우면 키 차이가 0이 되며 종료된다.
 */