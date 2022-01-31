package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_1976 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());//도시의 수
        int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시들의 수

        parent = new int[N + 1];

        //노드 번호가 1번부터 시작(0번은 x)
        for (int i = 1; i < N + 1; i++) {
            parent[i] = i; // 일단 union을 하기전// 부모노드 갖기전
        }

        for (int i = 1; i < N + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                int temp = Integer.parseInt(st.nextToken());

                //연결된 경우(temp가 1인경우) union 연산
                if (temp == 1)
                    union(i, j);
            }

        }

        StringTokenizer st2 = new StringTokenizer(br.readLine()); //여행 계획
        int root = find(Integer.parseInt(st2.nextToken()));// 처음 시작 도시는 꼭 포함되어 있어야한다.
        for(int i=1; i<M; i++){ //시작도시를 제외한 나머지 도시
            int city = Integer.parseInt(st2.nextToken());
            //처음 출발 도시와 연결되어 있지않으면 여행 일정이 불가능.
            //즉 부모 노드가 같아야한다.
            if(root !=find(city)){
                System.out.println("NO");
                return;
            }

        }
        System.out.println("YES");


    }

    //x의 부모를 찾는 연산
    public static int find(int x){
        if(x ==parent[x]){ //자기 자신인경우, 즉 부모 노드가 없는 경우
            return x;
        }
        return parent[x] = find(parent[x]); //재귀를 통해 부모노드 찾는 연산
   }



   // 작은 수로 union 연산. 즉 x가 작을때 y의 부모를 x의 부모로 변환.
    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x !=y){
            if(x<y)
                parent[y] = x;
            else
                parent[x] = y;
        }
    }



}
/*
https://brenden.tistory.com/33
유니온 파인드
- 대표적 그래프 알고리즘으로 '합집합 찾기'라는 의미
- 상호 배타적 집합(Disjoint-set)이라고도 함.
- 여러 노드가 존재할 때, 두 개의 노드를 선택해서, 현재 두 노드가 서로 같은 그래프에 속하는지 판별하는 알고리즘

*Find : x가 어떤 집합에 포함되어 있는지 찾는 연산
*Union : x와 y가 포함되어 있는 집합을 합치는 연산

i : 노드 번호
p[i] : 부모 노드 번호
즉, 자신이 어떤 부모에 포함되어 있는지를 의미.
정리하면 parent[i] = i로 표현할 수 있다.(연결된게 없을때?)

일반적으로 부모를 합칠 때는 더 작은 값 쪽으로 합친다. 이것을 합침(union) 과정이라고 할 수 있다.


1, 2, 3 이 연결될 때는 1과 3이 연결되었는지 파악하기 힘들다. 이때 '재귀함수'가 사용된다.
3의 부모인 2를 먼저 찾고, 2의 부모인 1을 찾아, 결과적으로 3의 부모는 1이 되는 것을 파악한다.



<문제 풀이>
1. 행렬에서 1인 요소가 있으면, union(a,b)연산을 한다.
2. 여행 계획에서 맨 처음 도시를 find 연산하여 루트 노드를 찾는다.
3. 여행 계획 상의 2~N 도시에 대하여 find 연산을 한 루트 노드와 2에서 수행한 루트 노드를 비교한다.
3-1 : 만약 서로 루트 노드가 다를 경우 "NO"를 출력.
3-2 : 만약, 모든 여행 계획 상의 도시의 루트 노드가 같을 경우 "YES"를 출력.
 */