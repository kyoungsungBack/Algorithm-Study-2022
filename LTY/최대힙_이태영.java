package data_structure2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class 최대힙_이태영 {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();//정답 출력을 위해


        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0;i<N;i++){
            int x = Integer.parseInt(br.readLine());
            if(x==0){
                if(pq.isEmpty()){
                    sb.append('0').append('\n');
                }else{
                    sb.append(pq.poll()).append('\n');
                }
            }else{
                pq.add(x);
            }
        }
        System.out.println(sb.toString());
    }
}


/*
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); 우선순위 큰수부터

 */
