package data_structure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 절댓값힙_이태영 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();//정답 출력을 위해

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((num1, num2)->{
            int abs1 = Math.abs(num1);
            int abs2 = Math.abs(num2);
            if(abs1==abs2) {
                if (num1 > num2)
                    return 1;
                else
                    return -1;
            }
            return abs1 - abs2;

        });




        for(int i=0; i<N; i++){
            int x = Integer.parseInt(br.readLine());
            if(x==0){
                if(pq.isEmpty()) {
                    sb.append("0").append("\n");
                }
                else{
                    sb.append(pq.poll()).append('\n');
                }

            }
            else{
                pq.add(x);

            }
        }

        System.out.println(sb.toString());
    }
}


/*
우선순위 큐(Priority Queue)
- Priority Queue는 먼저 들어온 순서대로 데이터가 나가는 것이 아닌
우선순위를 먼저 결정하고 그 우선순위가 높은 엘리먼트가 먼저 나가는 자료 구조

- 우선순위 큐는 힙을 이용하여 구현하는 것이 일반적.
- 데이터를 삽입할 때 우선순위를 기준으로 최대힙 혹은 최호 힙을 구성하고 데이터를 꺼낼 때 루트 노드를 얻어낸 뒤 루트 노드를 삭제할 때는 빈 루트 노드 위치에
맨 마지막 노드를 삽입하ㅏㄴ 후 아래로 내려가면서 적절한 자리를 찾아서 옮기는 방식으로 진행된다.


우선순위 큐의 특징
1. 높은 우선순위의 요소를 먼저 꺼내서 처리하는 구조(큐에 들어가는 원소는 비교가 가능한 기준이 있어야함)
2. 내부 요소는 힙으로 구성되어 이진트리 구조로 이루어져 있음
3. 내부구조가 힙으로 구성되어 있기에 시간 복잡도는 O(NLongN)
4. 응급실과 같이 우선순위를 중요시해야 하는 상황에서 쓰임.
5. 우선순위가 가장 높은 값을 참조하고 싶다면 peek()라는 메서드를 사용하면 된다.

priorityQueue.add(1);     // priorityQueue 값 1 추가
priorityQueue.add(2);     // priorityQueue 값 2 추가
priorityQueue.offer(3);   // priorityQueue 값 3 추가


priorityQueue.poll();       // priorityQueue에 첫번째 값을 반환하고 제거 비어있다면 null
priorityQueue.remove();     // priorityQueue에 첫번째 값 제거
priorityQueue.clear();      // priorityQueue에 초기화



https://algorithmstudy-mju.tistory.com/65
PS를 위한 람다식

이러한 것이 익숙해졌다면 실제 문제를 푸는 상황에서는 두가지에 대한 우선순위를 부여하기 보다는 한가지에 대한
우선순위로 큐를 정렬하는 경우가 많다, 매번 compare를 오버라이딩 하기보다는 lambda식을 통해서 한줄에 처리하자

PriorityQueue<node> Pq = new PriorityQueue<>( (node n1, node n2) -> n1.edge > n2.edge ? 1: -1);


이렇게도 선언할 수 있다.

풀어서 생각해본다면,

우리는 node n1,n2를 오름차순으로 비교 할 것인데 Target은 n2 이다.

n1 과 n2의 edge값을 비교하는데 n2가 숫자가 작으면 n2에게 우선순위를 더 주어야겠다



정도로 풀어서 생각할 수 있다. 처음에는 익숙하지 않지만 관련 문제를 풀어보자.
 */