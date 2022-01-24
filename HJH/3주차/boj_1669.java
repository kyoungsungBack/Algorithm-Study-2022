/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/1669
 풀이시간 : 30m
 시간복잡도 : O(1) 
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.util.*;
import java.io.*;

public class Main {
    private static int x, y;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        int answer = solve();

        System.out.print(answer);
    }

    private static int solve() {
        if (x == y) {
            return 0;
        }

        int diff = y - x;

        int realPart = (int) Math.sqrt(diff);

        if (realPart * realPart == diff) {
            return realPart * 2 - 1;
        }

        int tmp = diff - realPart * realPart;

        if (tmp <= realPart) {
            return realPart * 2;
        }
        return realPart * 2 + 1;
    }
}
