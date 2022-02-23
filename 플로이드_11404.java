package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 플로이드_11404 {

    public static int cityCount;
    public static int[][] distance;
    public static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        //최단경로
        //2차원 배열로 존재하면서 기준점 k를 두고 i에서 j까지 가는 거리와 i에서 k 까지 갔다가 k에서
        //j까지 가는 거리 두가지를 비교하여 최소 값을 최단 거리로 구하기.


        //NxN의 2차원 배열로 존재하고 각 도시의 도달할 수 있는 최소 비용 저장
        //비용을 저장하는 배열에는 무한대의 값으로 초기화(A->B 도시로 가는 버스 정보를 받지 못할 경우 대비)
        //A->A 도시로 가는 비용은 0

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        cityCount = Integer.parseInt(br.readLine()); //도시의 개수

        int busCount = Integer.parseInt(br.readLine());//버스의 개수

        distance = new int[cityCount + 1][cityCount + 1]; //2차원 배열

        //배열값 초기화
        for (int i = 1; i <= cityCount; i++) {
            for (int j = 1; j <= cityCount; j++) {
                if (i == j) continue;
                distance[i][j] = INF; //일단 무한대의 값으로 초기화
            }
        }

        //버스 정보 입력받기
        while (busCount-- > 0) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()); //출발 도시
            int end = Integer.parseInt(st.nextToken()); //도착 도시
            int time = Integer.parseInt(st.nextToken());  //비용

            distance[start][end] = Math.min(distance[start][end], time);
        }

        floydWarshall();
        print();




    }

    //플로이드 와샬 최소 비용 구하기
    //플로이드 와샬에서 중요한 것은 거쳐가는 노드
    //모든 노드들을 한번씩 거쳐가는 기준점 K로 잡고 시작점 A에서 B로 간다고 했을때
    //A에서 B로 가는 기존의 비용과 A에서 K로 갔다가 K에서 B로 가는 비용을 비교하여 더작은 값을
    //A에서 B로 가는 최소 비용으로 변경해주면 된다.
    public static void floydWarshall() {
        //기준이 되는 거쳐가는 노드 K
        for (int k = 1; k <= cityCount; k++) {
            //출발하는 노드 i
            for (int i = 1; i <= cityCount; i++) {
                //도착하는 노드 j
                for (int j = 1; j <= cityCount; j++) {
                    //i에서 k를 거쳤다가 k에서 j까지 가는 거리와 i에서 j까지 가는 거리를 비교해서 작은 값이 최소거리.
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }

    }

    //각 도시들을 전부 거쳐가는 노드로 기준을 잡고 1번 도시부터 N도시까지의 최소 비용을 구하기.
    //이렇게 하더라도 끝까지 접근할 수 없는 도시가 존재할 경우 0을 출력하게 한다.
    public static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= cityCount; i++) {
            for(int j=1; j<=cityCount; j++){
                if (distance[i][j] >= INF) sb.append("0 ");
                else sb.append(distance[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }




}
