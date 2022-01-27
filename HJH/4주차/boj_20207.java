/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/20207
 풀이시간 : 30m
 시간복잡도 : O(365) 
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] schedules = new int[366 + 1];

    public static void main(String[] args) throws IOException {
        init();

        int result = 0;
        int start = 0;
        int high = 0;
        for (int day = 1; day <= 365 + 1; day++) {
            if (high < schedules[day]) high = schedules[day];

            if (schedules[day] != 0 && start == 0) {
                start = day;

                continue;
            }

            if (schedules[day] == 0 && start != 0) {
                result += (day - start) * high;
                start = 0;
                high = 0;
            }
        }

        System.out.print(result);
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            var st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            for (int f = from; f <= to; f++) {
                schedules[f]++;
            }
        }

        br.close();
    }

}
