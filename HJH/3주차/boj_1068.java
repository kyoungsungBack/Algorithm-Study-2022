/******************************************************************************

 문제출처 : https://www.acmicpc.net/problem/1068
 풀이시간 : 30m
 시간복잡도 : O(n^2)
 공간복잡도 : O(n)
 
 *******************************************************************************/

import java.util.*;
import java.io.*;

public class Main {
    private static int n, root, removeNode;
    private static LinkedList<Integer>[] adjList;
    private static int result;

    public static void main(String[] args) throws Exception {
        init();

        if (removeNode == root) {
            System.out.print(0);
            return;
        }

        boolean[] visited = new boolean[n];
        visited[root] = true;
        dfs(root, visited);

        System.out.print(result);
    }

    private static void dfs(int curNode, boolean[] visited) {
        if (adjList[curNode].size() == 0) {
            if (removeNode != curNode) result++;

            return;
        }

        for (var adj : adjList[curNode]) {
            if (visited[adj]) continue;

            visited[adj] = true;
            dfs(adj, visited);
        }
    }

    private static void init() throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        adjList = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new LinkedList<Integer>();
        }

        var st = new StringTokenizer(br.readLine());

        for (int curNode = 0; curNode < n; curNode++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) {
                root = curNode;
                continue;
            }

            adjList[parent].add(curNode);
        }

        removeNode = Integer.parseInt(br.readLine());

        for (var parent = 0; parent < n; parent++) {
            for (var child : adjList[parent]) {
                if (child != removeNode) continue;

                adjList[parent].remove(new Integer(removeNode));
                adjList[removeNode].clear();

                br.close();
                return;
            }
        }

        br.close();
    }

}
