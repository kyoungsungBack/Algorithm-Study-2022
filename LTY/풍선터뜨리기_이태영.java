package Data_structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 풍선터뜨리기_이태영 {
    public static void main(String[] args) throws IOException {
        int N;

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();//정답 출력을 위해

        N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        int ballons[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<int[]> q = new ArrayDeque<>();

        for(int i=0; i<N; i++){
            ballons[i] = Integer.parseInt(st.nextToken()); // ballons배열에 정수 변환해서 넣어주기.
        } // 종이에 번
        sb.append(1).append(' ');


        int val = ballons[0]; //첫번째 ballon의 종이 val 값을 a에

        for(int i=1; i<N; i++){
            q.add(new int[] {(i+1), ballons[i]}); //{풍선 idx, 내용}
        }

        while(!q.isEmpty()){
            //양수인 케이스
            if(val >0){
                //순서 뒤로 돌리기
                for(int i=1; i<val; i++){
                    q.add(q.poll()); //
                }

                int[] nxt = q.poll();
                val = nxt[1];
                sb.append(nxt[0]+ " ");
            }

            //음수인 경우
            else{
                for(int i=1; i<-val; i++){
                    q.addFirst(q.pollLast());
                }

                int[] nxt = q.pollLast();
                val = nxt[1];
                sb.append(nxt[0]+ " ");
            }
        }
        System.out.println(sb.toString());

        // 큐사용?
        //[3, 2, 1, -3, -1]
        // 원형 배치
        // 1번 풍선부터 터진다.
        // 양수는 오른쪽으로
        // 음수는 왼쪽으로
        // N은 풍선의 개수
        // 풍선 안에는 -N부터 N까지의 수가 적혀있다.





        // 배운점

        //1)
        // bufferreader
        // 데이터 한 곳에 다른 한 곳으로 전송하는 동안 일시적으로 그 데이터를 보관하는 임시 메모리 영역
        // 입출력 속도 향상을 위해 버퍼 사용
        // 엔터만 경계로 인식하고 받은 데이터가 String으로 고정
        // scanner를 사용하는 것보다 빠르다.
        // 배열 크기를 지정하지 않고 읽을 수 있다.
        // 버퍼 입력 - BufferedReader
        // 버퍼 출력 - BufferedWriter


        //2) throws IOException
        // 입출력 명령 사용시에 붙여주 필수적인 코드
        // 자바 입력고 출력으로부 일어날 수 있는 예외처리에 대해 까다로운 문법 적용.

        //3) StringTokenizer
        // BufferedReader는 잘라 배열과 같이 인덱스를 사용하여 접근하여 사용
        // StringTokenizer는 뒤에 공백이 있다면 뒤에 문자열이 공백 자리를 땡겨 채우도록 한다.
        // 문자열을 자르게 위해 split을 사용할땐, split은 정규식을 기반으로 자르는 로직으로서 내부는 복잡하다. 그에 비해 StringTokenizer의 nextToken()메소드는 단순히 공백 자리를 땡겨 채우는 것이다. 그렇기 때문에 속도 차이가 날 수 밖에 없다.
        // 정규식이나 인덱스 접근과 같은 처리가 필요없다면 StringTokenizer를 사용하는 것이 효율적이다.

        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in);
        StringTokenizer st = new StringTokenizer(br.readLine());

        // AB CDD EFFF GH 입력

        st.nextToken() // AB
        st.nextToken() // CDD
        st.nextToken() // EFFF
        st.nextToken() // GH
         */


        //4) 자료구조 deque
        //상황에 따라 앞부분, 뒷부분 모두 요소를 추가하고 삭제하고 싶을때 매우 편리하게 사용.

        //5) 원형이라는 것을 가정했을 때 양수, 음수의 다른 조건을 고려해
        //코드를 작성.
    }
}
