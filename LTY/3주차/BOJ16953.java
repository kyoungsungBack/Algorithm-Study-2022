package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16953 {

    static int count = 1;
    public static void main(String[] args) throws IOException {
        long A;
        long B;
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        while(B!=A){
            String temp = String.valueOf(B);

            // A->B로 만들수 없는 케이스
            if((B<A) || (temp.charAt(temp.length()-1)!='1'&& B%2!=0)){ //A가 B보다 크거나      / 마지막수가 1이
                count =-1;
                break;
            }

            //나누는게 먼저 계산되어야 최소횟수로 연산가능
            if(B%2==0){
                B= B/2;

            }else{ //끝자리가 1인경우
                StringBuilder temp2 = new StringBuilder(temp); //deleteCharAt 메소드 사용을 위해
                temp2.deleteCharAt(temp2.length() - 1);
                temp = temp2.toString();
                B = Long.parseLong(temp);
            }
            count++;
        }
        System.out.println(count);
    }
}



/*
B에서 A로 만드는 것으로 로직
- B가 2로 나누어 떨어지지 않거나, 맨 끝자리가 1이 아니라면 A로 만드는 것이 불가능.
- B가 2로 나누어 떨어지면, B를 2로 나눈다.
- B의 맨 끝자리가 1이라면, 1을 없앤다.


마지막 문자의 index는 '문자열 길이 -1
 */