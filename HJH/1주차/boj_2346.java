/**
 * https://www.acmicpc.net/problem/2346
 */

import java.io.*;
import java.util.*;

public class Main {
    static class Balloon {
        int number;
        int paper;

        Balloon(int number, int paper) {
            this.number = number;
            this.paper = paper;
        }
    }

    private static int N;
    private static ArrayDeque<Balloon> circle = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        init();

        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        var sb = new StringBuilder();

        while (!circle.isEmpty()) {
            var curBal = circle.pollLast(); // 꺼내오고 삭제

            sb.append(curBal.number);
            sb.append(" ");

            int paper = curBal.paper;

            if (paper > 0) {
                int moveCnt = paper - 1;
                Balloon tmpBal;
                while (moveCnt-- > 0 && (tmpBal = circle.pollLast()) != null) {
                    circle.addFirst(tmpBal);
                }
            } else {
                int moveCnt = Math.abs(paper);
                Balloon tmpBal;
                while (moveCnt-- > 0 && (tmpBal = circle.pollFirst()) != null) {
                    circle.addLast(tmpBal);
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        var st = new StringTokenizer(br.readLine(), " ");

        int balNum = 1;
        while (st.hasMoreTokens()) {
            circle.addFirst(
                    new Balloon(
                            balNum++,
                            Integer.parseInt(st.nextToken())
                    )
            );
        }

        br.close();
    }

}
/**
 * 소요시간: 0.18초
 * 시간복잡도: O(N)
 * 공간복잡도:
 */
