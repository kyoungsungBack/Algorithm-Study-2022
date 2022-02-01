package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달력_20207 {

    static int list[] = new int[366]; //1일부터 365일까지 표기된 달력이므로
    static int result;
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);


        int N = Integer.parseInt(br.readLine()); //일정의 개수



        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); //시작일자
            int end = Integer.parseInt(st.nextToken()); //종료날짜

            for(int j= start; j<=end; j++)
                list[j] = list[j]+1; //일자의 등장횟수를 1씩 증가시킨다.

        }


        int y = 0; //높이
        int x = 0; //길이
        for(int i=1; i<=365; i++){
            if(list[i]>0){
                x++;
                y = Math.max(y, list[i]); //y길이 갱신/

            }else{//list[i]=0인 경우로 일정이 끊긴 경우
                result = result+(x*y);
                x=0; //x값 초기화
                y=0; //y값 초기화

            }
        }

        result += x*y;
        System.out.println(result);
    }
}
