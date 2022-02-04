/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/16562
 풀이시간 : 1h
 시간복잡도 : O(nlogn) = O(10,000log10,000)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n, m, money;
    private static int[] moneys;
    private static int[] cycleMap;
    private static boolean[][] adjList;
    private static int minCostSum;

    public static void main(String[] args) throws IOException {
        init();

        solve();

        System.out.print(minCostSum > money ? "Oh no" : minCostSum);
    }

    private static void solve() {
        for (int cur = 1; cur <= n; cur++) { // 루트와 연결
            for (int adj = 1; adj <= n; adj++) {
                if (cur == adj || !adjList[cur][adj]) continue;

                union(cur, adj);
            }
        }

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            int parent = cycleMap[i];

            if (parent == i)
                sum += moneys[parent];
            //moneys[parent] = 0;
        }

        minCostSum = sum;
    }

    private static void union(int v1, int v2) {
        int parentV1 = find(v1);
        int parentV2 = find(v2);

        //친구비가 더 작은 애가 parent가 될 수 있도록 한다.
        if (moneys[parentV1] < moneys[parentV2]) cycleMap[parentV2] = parentV1;
        else cycleMap[parentV1] = parentV2;
    }

    private static int find(int v) {
        if (cycleMap[v] == v) return v;

        return find(cycleMap[v]);
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());

        moneys = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            moneys[i] = Integer.parseInt(st.nextToken());
        }

        adjList = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            adjList[v1][v2] = true;
            //adjList[v2][v1] = true;
        }

        cycleMap = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cycleMap[i] = i;
        }

        br.close();
    }

}
