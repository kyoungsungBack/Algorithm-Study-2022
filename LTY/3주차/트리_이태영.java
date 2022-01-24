import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리_이태영 {

    static int N;
   // static ArrayList<Integer>[] tree;
    static int result; //정답값
    static int delete; //지울 노드
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        N = Integer.parseInt(br.readLine());
        parent = new int[N]; //N개 요소를 가진 배열 만들어주기

        int root = 0;

        StringTokenizer st = new StringTokenizer(br.readLine()); // 두번째줄 입력받기(노드의 부모에 관한 정보)



       // tree = new ArrayList[N];

//        for (int i = 0; i < N; i++) {
//            tree[i] = new ArrayList<Integer>();
//        }

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            parent[i] = temp; //parent배열에 노드의 부모정보를 넣어준다.
            if(parent[i]==-1)
                root = i; //-1인경우 루트 노드를 의미하므로. root변수에 노드값을 저장.
        }


        //지울 노드 입력받기
        delete = Integer.parseInt(br.readLine());

        deleteNode(delete);

        result = 0;
        visited = new boolean[N];
        countLeaf(root);

        System.out.println(result);

    }

    static void deleteNode(int del){
        parent[del] = -3;// 삭제된 노드 -3으로 표시
        for(int i=0;i<N;i++){
            if(parent[i]==del){ //parent[i]는 각 자식노드의 부모 노드를 의미
                deleteNode(i); //재귀를 통해 노드 삭제 진행
            }
        }
    }




    static void countLeaf(int r){ //루트 노드로부터 쭉 count
        boolean isLeaf = true;
        visited[r] = true; //방문했다는 표시
        if(parent[r]!= -3){ //처음에는 루트 노드를 삭제하는 경우가 아닌 경우.
            for(int i=0; i<N; i++){
                if(parent[i] ==r && visited[i] == false){ //부모 노드가 루트노드이고
                    countLeaf(i); //현재 노드를 부모로 가지는 노드가 하나라도 존재한다면 자식 노드를 연쇄적으로 탐색하도록
                    isLeaf = false;
                }
            }
            if(isLeaf)
                result++;
        }
    }




}

// 0번만 루트 노드가 된다고 착학하면 안됨.
// 지운 노드가 root인 경우 0을 출력해야 함.



/*
ArrayList란?
- List 인터페이스를 상속받은 클래스로 크기가 가변적으로 변하는 선형리스트.
- 일반적인 배열과 같은 순차리스트이며 인덱스로 내부의 객체를 관리한다는 점 등이 유사하지만 한번 생성되면 크기가 변하지 않는 배열과는 달리 ArrayList 객체들이 추가되어 저장용량을 초과한다면 자동으로 부족한 크기만큼 저장 용량이 늘어남.


* ArrayList 값 추가
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(3); //값 추가
list.add(null); //null값도 add가능
list.add(1,10); //index 1에 10 삽입


ArrayList<Student> members = new ArrayList<Student>();
Student student = new Student(name,age);
members.add(student);
members.add(new Member("홍길동",15));



* ArrayList 값 삭제
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));
list.remove(1);  //index 1 제거
list.clear();  //모든 값 제거



* 리프 노드를 카운트 할 때는 깊이 우선 탐색을 활용하여 현재 노드를 부모르 가지는 노드가 하나라도 존재한다면 자식 노드를 연쇄적으로 탐색하도록.
재귀 함수 속에서 자식 노드가 하나라도 존재하는지를 기록해주기 위해 boolean타입의 변수를 하나 생성. 자식 노드가 없다면 리프토드의 카운터를 증가.
 */