package week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class z_1074 {

    static int N;
    static int r; //r행
    static int c; //c열
    static int result = 0;


    public static void main(String[] args) throws IOException {
        //재귀로 구현
        //재귀를 호출할 때마다 현재 r,c,의 위치에 따라 몇번의 방문을 했는지 더한다.
        //count는 0부터
        //0행부터
        //사분면에서의 rc의 상대위치를 넘겨주는 것이 중요!


        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int s = (int) Math.pow(2, N); // 한변의 사이즈

        solution(s, r, c);
        System.out.println(result);
    }

    public static void solution(int size, int r, int c){
        if(size==1)
            return;

        int temp = size/2;

        //1사분면인 경우
        if(r<temp && c<temp){
            solution(temp, r, c);
        }

        //2사분면인 경우
        else if(r<temp && c>=temp){
            result = result+(size*size)/4; //1사분면을 포함하므로
            solution(size / 2, r, c - size / 2);//상대위치 반환

        }

        //3사분면인 경우
        else if(r>=temp && c<temp){
            result = result+(size*size/4)*2;
            solution(size / 2, r - size / 2, c);
        }

        //4사분면인 경우
        else{
            result = result + (size * size / 4) * 3;
            solution(size / 2, r - size / 2, c - size / 2);
        }
    }
}
