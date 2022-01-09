package data_strcuture;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class 쇠막대기_이태영 {

    //다시 풀어보기기
   public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = br.readLine();

        int open = 0;
        int ans = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') { // 열린 괄호면 open에 1을 더해 줌.
                open++;
            } else { // 닫힌 괄호일 경우,
                open--; // 일단 open에 1을 빼 줌.

                if (input.charAt(i - 1) == '(') { // 그 전 괄호가 열린 괄호면 레이저를 의미.
                    ans += open; // open의 값만큼 더해 줌.
                } else { // 그 전 괄호가 닫힌 괄호면 레이저가 아님.
                    ans++; // 단순히 1을 더해 줌.
                }
            }
        }

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
