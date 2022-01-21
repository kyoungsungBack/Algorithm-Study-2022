/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/2302
 풀이시간 : 1h
 시간복잡도 : O(50 * 50)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.util.*;
import java.io.*;

public class Main {
    private static int n, m;
    private static List<Integer> vips;

    public static void main(String[] args) throws Exception {
        init();

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) { // O(50)
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int prevStartArea = 0;
        int areaSize = 0;
        int result = 1;

        for (var vip : vips) { // O(50)
            areaSize = vip - prevStartArea - 1;
            prevStartArea = vip;

            result = result * dp[areaSize];
        }

        result = result * dp[n - prevStartArea];

        System.out.print(result);
    }

    private static void init() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        vips = new ArrayList<>(m);

        for (int i = 0; i < m; i++) {  // 정렬된 순으로 입력됨
            vips.add(Integer.parseInt(br.readLine()));
        }

        br.close();
    }


}
