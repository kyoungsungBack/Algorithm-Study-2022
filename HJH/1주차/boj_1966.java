/******************************************************************************

문제출처 : https://www.acmicpc.net/problem/1966
풀이시간 : 20분
시간복잡도 : O(N^2) : 큐에서 하나씩 뺄 때마다 큐에 모든 원소와 비교해야함
공간복잡도 : O(n)

*******************************************************************************/

import java.io.*;
import java.util.*;

public class Main {
    private static int tcNum, originNumOfTarget;
    private static Queue<Integer> Q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        tcNum = Integer.parseInt(br.readLine());

        while (tcNum-- > 0) {
            var st = new StringTokenizer(br.readLine());
            int paperNum = Integer.parseInt(st.nextToken());
            int indexOfTarget = Integer.parseInt(st.nextToken());

            int curIdx = 0;

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                if (curIdx++ == indexOfTarget) {
                    Q.add(-1);
                    originNumOfTarget = Integer.parseInt(st.nextToken());
                    continue;
                }

                Q.add(Integer.parseInt(st.nextToken()));
            }

            solve();
            Q.clear();
        }
    }

    private static void solve() {
        boolean isTarget;

        int outCnt = 0;
        while (!Q.isEmpty()) {
            int front = Q.peek();
            if (front == -1) {
                front = originNumOfTarget;
                isTarget = true;
            } else {
                isTarget = false;
            }

            if (hasGreaterThan(front)) {
                Q.add(Q.poll());
            } else {
                outCnt++;

                if (isTarget) {
                    System.out.println(outCnt);
                    return;
                } else {
                    Q.poll();
                }
            }
        }
    }

    private static boolean hasGreaterThan(int num) {
        for (var val : Q) {
            val = val == -1 ? originNumOfTarget : val;
            if (num < val) return true;
        }

        return false;
    }

}
