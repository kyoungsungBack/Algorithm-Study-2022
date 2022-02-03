/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/1753
 풀이시간 : 30m
 시간복잡도 : O(elogv) = O(300,000log20,000)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int v, cost;

    Node(int v, int cost) {
        this.v = v;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }

}

public class Main {

    private static int vNum, eNum, start;
    private static List<Node>[] adjList;
    private static int[] dist;
    private static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        solve();

        for (int i = 1; i <= vNum; i++) {
            System.out.println(dist[i] == MAX ? "INF" : dist[i]);
        }
    }

    private static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        boolean[] visited = new boolean[vNum + 1];
        dist[start] = 0;

        while (!pq.isEmpty()) {
            var cur = pq.poll();

            visited[cur.v] = true;

            for (var adj : adjList[cur.v]) {
                if (visited[adj.v]) continue;

                if (dist[adj.v] > dist[cur.v] + adj.cost) {
                    dist[adj.v] = dist[cur.v] + adj.cost;
                    pq.add(new Node(adj.v, dist[adj.v]));
                }
            }
        }

    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        vNum = Integer.parseInt(st.nextToken());
        eNum = Integer.parseInt(st.nextToken());

        adjList = new List[vNum + 1];

        for (int i = 1; i <= vNum; i++) {
            adjList[i] = new LinkedList<Node>();
        }

        start = Integer.parseInt(br.readLine());

        for (int i = 1; i <= eNum; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[u].add(new Node(v, w));
        }

        dist = new int[vNum + 1];
        Arrays.fill(dist, MAX);

        br.close();
    }


}
