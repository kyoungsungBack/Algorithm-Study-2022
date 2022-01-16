package data_structure2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class 생태학_이태영 {

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);





        double n =0;//전체개수
        HashMap<String, Double> map = new HashMap<String, Double>();// 키는 String, 값을 double



        String temp = br.readLine();
        while(true){

            if(temp==null||temp.length()==0){
                break;
            }

            if(map.get(temp)!=null){ //기존에 이미 있다면
                double temp2 = map.get(temp);//값 갱신
                temp2+=1.0;
                map.put(temp, temp2); //해쉬맵은 키값이 중복되지 않는다. 다시 put할 경우 이전꺼를 없앤다.
            }
            else{ //새롭게 넣는 거라면
                    map.put(temp, 1.0);
            }

            n++;
            temp = br.readLine();






        }



        Object[] mapkey = map.keySet().toArray(); //사전순 정렬렬
        Arrays.sort(mapkey);
        for(int i=0; i<map.size(); i++){
            double count = map.get(mapkey[i]);
            double percent = (double)count/n*100.0;

            System.out.printf(mapkey[i]+" "+"%.4f\n", percent);
        }


    }
}


/*
HashMap 키(key) 정렬
HashMap을 정렬하기 위해서는 Arrays.sort메서드를 사용한다.

 */