package data_strcuture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 프린터큐_이태영 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(br.readLine()); //테스트케이스 개수

        for(int j=0; j<testcase; j++){

            StringTokenizer st = new StringTokenizer(br.readLine());


            int N = Integer.parseInt(st.nextToken()); //인쇄 개수
            int M = Integer.parseInt(st.nextToken());//몇번째로 인쇄되었는지 궁금한 문서가 현재 que에 위치한 상태, 맨왼쪽을 0으로 생각


            LinkedList<int[]> que = new LinkedList<>(); //큐로 활용할 연결리스트
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<N; i++){
                //{초기 위치, 중요도}
                que.offer(new int[]{i, Integer.parseInt(st.nextToken())});
            }

            int count = 0; //출력횟수

            while(!que.isEmpty()){

                int[] front = que.poll(); //첫번째 수
                boolean isMax = true; //front원소가 가장 큰 원소인지 판단하는 변수

                //큐에 남아있는 원소들과 중요도 비교
                for(int i=0; i<que.size(); i++){

                    // 처음 뽑은 원소보다 큐에 있는 i번째 원소가 클 때
                    if(front[1] < que.get(i)[1]){

                        //뽑은 원소 및 i 이전의 원소들을 뒤로 보낸다.
                        que.offer(front);
                        for(int k=0; k<i; k++){
                            que.offer(que.poll());
                        }


                        //front 원소가 가장 큰 원소가 아닐경우 false를 하고 탐색을 마친다.
                        isMax = false;
                        break;

                    }
                }


                // front 원소가 가장 큰 원소가 아니므로 다음 반복문으로 넘어간다.
                if(isMax == false){
                    continue;
                }


                //front 원소가 가장 큰 원소인경우
                count++;
                if(front[0]==M){ //테스트케이스 종료
                    break;
                }
            }

            sb.append(count).append('\n');
        }
        System.out.println(sb);

    }
}
