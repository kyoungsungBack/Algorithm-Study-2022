/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/16953
 풀이시간 : 10m
 시간복잡도 : O(logB) = O(log10^9) = O(9)
 공간복잡도 : O(n)

 *******************************************************************************/

import java.util.*;
import java.io.*;

public class Main {
    private static long A, B;

    public static void main(String[] args) throws Exception {
        init();

        solve();
    }

    private static void solve() {
        int cnt = 0;

        while (B >= A) {
            if (B % 2 == 0) { // 짝수일 경우
                B = B / 2;
            } else if (B % 10 == 1) { // 끝이 1 일 경우
                B = B / 10;
            } else {
                System.out.println(-1);
                return;
            }

            cnt++;

            if (B == A) {
                System.out.println(cnt + 1);
                return;
            }
        }

        System.out.println(-1);
    }

    private static void init() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        br.close();
    }

}
