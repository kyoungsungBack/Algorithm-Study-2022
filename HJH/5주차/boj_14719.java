/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/14719
 풀이시간 : 1h
 시간복잡도 : O(n^2) ~ O(500 * 500) ~ O(250,000)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int ySize, xSize;
    private static int[] wall;

    public static void main(String[] args) throws IOException {
        init();

        int answer = 0;

        for (int i = 1; i < xSize; i++) {
            int leftMax = findLeftMax(i);
            int rightMax = findRightMax(i);

            if (leftMax == -1 || rightMax == -1) continue;

            answer += (Math.min(leftMax, rightMax) - wall[i]);
        }

        System.out.print(answer);
    }

    private static int findLeftMax(int idx) {
        int pivot = wall[idx];

        for (int i = idx - 1; i >= 0; i--) {
            if (wall[i] <= pivot) continue;
            pivot = wall[i];
        }

        return pivot == wall[idx] ? -1 : pivot;
    }

    private static int findRightMax(int idx) {
        int pivot = wall[idx];

        for (int i = idx + 1; i < xSize; i++) {
            if (wall[i] <= pivot) continue;
            pivot = wall[i];
        }

        return pivot == wall[idx] ? -1 : pivot;
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        ySize = Integer.parseInt(st.nextToken());
        xSize = Integer.parseInt(st.nextToken());

        wall = new int[xSize];

        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < xSize; i++) {
            int val = Integer.parseInt(st.nextToken());
            wall[i] = val;
        }

        br.close();
    }

}
