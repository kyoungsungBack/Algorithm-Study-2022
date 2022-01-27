/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/1976
 풀이시간 : 1h
 시간복잡도 : O(n^2 * m)
 공간복잡도 : O(n)

 *******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {

    private static int n, m;
    private static int[][] map;
    private static int[] path;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i < m; i++) {
            if (path[i - 1] == path[i]) continue;

            if (!bfs(path[i - 1], path[i])) {
                System.out.print("NO");
                return;
            }
        }

        System.out.print("YES");
    }

    private static boolean bfs(int from, int to) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> Q = new LinkedList<>();
        Q.add(from);
        visited[from] = true;

        while (!Q.isEmpty()) {
            int cur = Q.poll();

            for (int adj = 1; adj <= n; adj++) {
                if (map[cur][adj] == 0 || visited[adj]) continue;

                if (adj == to) return true;

                Q.add(adj);
                visited[adj] = true;
            }
        }

        return false;
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            var st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        path = new int[m];

        var st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            path[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }

}
