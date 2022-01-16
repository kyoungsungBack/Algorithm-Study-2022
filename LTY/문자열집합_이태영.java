package data_structure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 문자열집합_이태영 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);


        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //집합 S에 같은 문자열이 여러번 주어지는 경우가 없으므로 입력받는 문자열을 해쉬맵의 키값으로 사용
        HashMap<String, Integer> map = new HashMap<>();// 키는 String, 값을 integer


        int count = 0; //정답 출력을 위해
        for(int i=0; i<N; i++){
            map.put(br.readLine(), i);
        }


        for(int i=0; i<M; i++){
            String temp = br.readLine();
            if(map.get(temp)!=null){
                count++;
            }
        }

        System.out.println(count);
    }
}



/*
HashMap 이란?
HashMap은 Map 인터페이스를 구현한 대표적인 Map 컬렉션. Map 인터페이스를 상속하고 있기에 Map의 성질을 그대로 갖는다.
Map은 키와 값으로 구성된 Entry 객체를 저장하는 구조를 가지고 있는 자료구조이다.
여기서 키와 값은 모두 객체이다.
값은 중복 저장될 수 있지만 키는 중복 저장될 수 없다.
만약 기존에 저장된 키와 동일한 키로 값을 저장하면 기존의 값은 없어지고 새로운 값으로 대치된다.
HashMap은 이름 그대로 해싱(Hashing)을 사용하기 때문에 많은 양의 데이터를 검색하는 데 있어서 뛰어난 성능을 보인다.
 */