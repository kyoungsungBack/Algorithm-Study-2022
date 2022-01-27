/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/2470
 풀이시간 : 1h
 시간복잡도 : O(n) 
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n;
    private static int[] inputs;
    private static int pick1, pick2;

    public static void main(String[] args) throws IOException {
        init();

        solve();

        System.out.print(pick1 + " " + pick2);
    }

    private static void solve() {
        int low = 0;
        int high = n - 1;
        int minSum = Integer.MAX_VALUE;

        while (low < high) {
            int sum = inputs[low] + inputs[high];

            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);

                pick1 = inputs[low];
                pick2 = inputs[high];
            }

            if (sum > 0) {
                high--;
            } else {
                low++;
            }
        }
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        inputs = new int[n];

        var st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputs);

        br.close();
    }

}
