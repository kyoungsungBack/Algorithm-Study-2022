/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/1922
 풀이시간 : 20m
 시간복잡도 :
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int v1, v2, cost;

    Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost; // 오름차순
    }
}

public class Main {

    private static int n, m;
    private static PriorityQueue<Edge> pq;
    private static int[] cycleTable;

    public static void main(String[] args) throws IOException {
        init();

        int result = solve();

        System.out.print(result);
    }

    private static int solve() {
        int costSum = 0;
        int selectedEdgeNum = 0;

        while (!pq.isEmpty()) {
            var cur = pq.poll();

            if (find(cur.v1) == find(cur.v2)) continue;
            union(cur.v1, cur.v2);

            costSum += cur.cost;
            selectedEdgeNum++;

            if (selectedEdgeNum == n - 1) break;

        }

        return costSum;
    }

    private static void union(int v1, int v2) {
        int parentv1 = find(v1);
        int parentv2 = find(v2);

        cycleTable[parentv1] = parentv2;
    }

    private static int find(int v) {
        if (cycleTable[v] == v) return v;

        return find(cycleTable[v]);
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(m);

        for (int i = 0; i < m; i++) {
            var st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Edge(v1, v2, cost));
        }

        br.close();

        cycleTable = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            cycleTable[i] = i;
        }

    }


}
