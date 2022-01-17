package data_structure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 이중우선순위큐_이태영 {

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();//정답 출력을 위해

        int T = Integer.parseInt(br.readLine()); // 테스트케이스





        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());//연산의 개수
            TreeMap<Integer,Integer> map1 = new TreeMap<Integer,Integer>(); //트리맵 선언 - 최댓값 최솟값에 접근하기 쉬움.

            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cal = st.nextToken();
                int M = Integer.parseInt(st.nextToken());


                //동일한 정수가 삽입될수 있는데 키값에 넣어도 되는건지;
                // -> 어차피 값만 갱신해주면 되니까 상관없다.

                if(cal.equals("I")){
                    if(map1.containsKey(M)) { //키가 중복되는 경우 값을 +1해준다.
                        int temp = map1.get(M)+1;
                        map1.put(M, temp);
                    }
                    else
                        map1.put(M, 1); //처음 넣는 값일 경우


                }else{ //'D'인 경우



                        if (M == 1) { //D 1 인경우 큐에서 최댓값 삭제

                            if(!map1.isEmpty()){
                                int temp2 = map1.get(map1.lastKey()) - 1;
                                if (temp2 == 0)  //최댓값이 중복없이 한개만 있는경우 큐에서 제거시켜줘도된다.
                                    map1.remove(map1.lastKey());
                                else
                                    map1.put(map1.lastKey(), temp2); //값 갱신해주기
                            }


                        } else { //D -1 인경우 큐에서 최솟값 삭제
                            if(!map1.isEmpty()){
                                int temp2 = map1.get(map1.firstKey()) - 1;
                                if (temp2 == 0)  //최소값이 중복없이 한개만 있는경우 큐에서 제거시켜줘도된다.
                                    map1.remove(map1.firstKey());
                                else
                                    map1.put(map1.firstKey(), temp2); //값 갱신해주기
                            }

                        }

                    }





                }
                if(map1.isEmpty()) {
                    sb.append("EMPTY\n");
                }
                else{
                    sb.append(map1.lastKey()+" "+map1.firstKey()).append("\n");

                }



        }
        System.out.println(sb.toString());

    }
}


/*
TreeMap이란?
트리맵은 이진트리를 기반으로 한 Map 컬렉션이다.
같은 트리 구조로 이루어진 TreeSet과의 차이점은 TreeSet은 그냥 값만 저장한다면 TreeMap은 키와 같이 값이 정된 Map, Entry를 저장한다는 점.
TreeMap에 객체를 저장하면 자동으로 정렬되는데, 키는 저장과 동시에 자동 오름차순으로 정렬되고
숫자 타입일 경우에는 값으로, 문자열 타입일 경우에는 유니코드로 정렬한다.
정렬 순서는 기본적으로 부모 키 값과 비교해서 키 값이 낮은 것은 왼쪽 자식노드에, 키값이 높은 것은 오른쪽 자식노드에 Map.Entry객체를 저장한다.
트리맵은 일반적으로 Map으로서의 성능이 HashMap보다 떨어진다. TreeMap은 데이터를 저장할 때 즉시 정렬하기에
추가나 삭제가 HashMap보다 오래걸린다.
하지만 정렬된 상태로 Map을 유지해야 하거나 정렬된 데이터를 조회해야하는 범위 검색이 필요한 경우 TreeMap을 사용하는 것이 효율성 면에서 좋다.


<트리맵 사용법>
- 트리맵 선언
TreeMap<Integer,String> map1 = new TreeMap<Integer,String>();//TreeMap생성
TreeMap<Integer,String> map2 = new TreeMap<>();//new에서 타입 파라미터 생략가능
TreeMap<Integer,String> map3 = new TreeMap<>(map1);//map1의 모든 값을 가진 TreeMap생성
TreeMap<Integer,String> map6 = new TreeMap<Integer,String>(){{//초기값 설정
    put(1,"a");


-트리맵 값 추가
TreeMap<Integer,String> map = new TreeMap<Integer,String>();//TreeMap생성
map.put(1, "사과");//값 추가
map.put(2, "복숭아");
map.put(3, "수박");



- 트리맵 값 삭제
TreeMap<Integer, String> map = new TreeMap<Integer,String>(){{//초기값 설정
    put(1, "사과");//값 추가
    put(2, "복숭아");
    put(3, "수박");
}};
map.remove(1); //key값 1 제거
map.clear(); //모든 값 제거

- 트리맵 단일값 출력
TreeMap<Integer,String> map = new TreeMap<Integer,String>(){{//초기값 설정
    put(1, "사과");//값 추가
    put(2, "복숭아");
    put(3, "수박");
}};

System.out.println(map); //전체 출력 : {1=사과, 2=복숭아, 3=수박}
System.out.println(map.get(1));//key값 1의 value얻기 : 사과
System.out.println(map.firstEntry());//최소 Entry 출력 : 1=사과
System.out.println(map.firstKey());//최소 Key 출력 : 1
System.out.println(map.lastEntry());//최대 Entry 출력: 3=수박
System.out.println(map.lastKey());//최대 Key 출력 : 3

 *///